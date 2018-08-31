package org.minxc.emp.bpm.engine.data.handle;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;

import org.minxc.emp.biz.api.model.IBusinessData;
import org.minxc.emp.biz.api.model.IBusinessObject;
import org.minxc.emp.biz.api.model.IBusinessPermission;
import org.minxc.emp.biz.api.service.IBusinessDataService;
import org.minxc.emp.biz.api.service.IBusinessObjectService;
import org.minxc.emp.biz.api.service.IBusinessPermissionService;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmDataModel;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.api.service.BpmRightsFormService;
import org.minxc.emp.bpm.core.manager.BpmBusLinkManager;
import org.minxc.emp.bpm.core.manager.BpmInstanceManager;
import org.minxc.emp.bpm.core.model.BpmBusLink;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.minxc.emp.common.db.dboper.DbOperator;
import org.minxc.emp.common.db.dboper.DbOperatorFactory;
import org.minxc.emp.core.api.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minxc.emp.core.util.BeanUtils;
import com.minxc.emp.core.util.StringUtil;

@Component
public class BpmBusDataHandle {
	@Resource
	private BpmInstanceManager f;
	@Resource
	private BpmProcessDefService a;
	@Resource
	private BpmBusLinkManager aG;
	@Resource
	private BpmRightsFormService aI;
	@Resource
	private IBusinessDataService au;
	@Autowired
	private IBusinessObjectService businessObjectService;
	@Autowired
	private IBusinessPermissionService businessPermissionService;
	private static int aJ = -1;
	private static Set<String> aK = Collections.synchronizedSet(new HashSet());
	private static final String tableName = "BPM_BUS_LINK";

	public Map<String, IBusinessData> a(IBusinessPermission businessPermision, BpmInstance instance) {
		IBusinessData busData;
		IBusinessObject businessObject;
		HashMap<String, IBusinessData> dataMap = new HashMap<String, IBusinessData>();
		BpmInstance topInstance = this.f.getTopInstance(instance);
		if (topInstance != null) {
			List<BpmBusLink> topInstanceBusLinks = this.aG.getByInstanceId(topInstance.getId());
			for (BpmBusLink busLink : topInstanceBusLinks) {
				businessObject = this.businessObjectService.getFilledByKey(busLink.getBizCode());
				businessObject.setPermission(businessPermision.getBusObj(busLink.getBizCode()));
				busData = this.au.loadData(businessObject, busLink.getBizId());
				if (busData == null) {
					throw new SystemException(
							String.format("bizCode[%s] bizId[%s]", busLink.getBizCode(), busLink.getBizId()),
							BpmStatusCode.FLOW_BUS_DATA_LOSE);
				}
				dataMap.put(busLink.getBizCode(), busData);
			}
		}
		List<BpmBusLink> busLinks = this.aG.getByInstanceId(instance.getId());
		for (BpmBusLink busLink : busLinks) {
			businessObject = this.businessObjectService.getFilledByKey(busLink.getBizCode());
			businessObject.setPermission(businessPermision.getBusObj(busLink.getBizCode()));
			busData = this.au.loadData((IBusinessObject) businessObject, (Object) busLink.getBizId());
			if (busData == null) {
				throw new SystemException(
						String.format("bizCode[%s] bizId[%s]", busLink.getBizCode(), busLink.getBizId()),
						BpmStatusCode.FLOW_BUS_DATA_LOSE);
			}
			dataMap.put(busLink.getBizCode(), busData);
		}
		DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(instance.getDefId());
		List<BpmDataModel> listDataModel = processDef.getDataModelList();
		for (BpmDataModel model : listDataModel) {
			String code = model.getCode();
			if (dataMap.containsKey(code))
				continue;
			IBusinessObject businessObject2 = this.businessObjectService.getFilledByKey(code);
			businessObject2.setPermission(businessPermision.getBusObj(code));
			IBusinessData busData2 = this.au.loadData(businessObject2, null);
			dataMap.put(code, busData2);
		}
		return dataMap;
	}

	public Map<String, IBusinessData> a(IBusinessPermission businessPermision, String defId) {
		HashMap<String, IBusinessData> dataMap = new HashMap<String, IBusinessData>();
		DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(defId);
		List<BpmDataModel> listDataModel = processDef.getDataModelList();
		for (BpmDataModel model : listDataModel) {
			String code = model.getCode();
			IBusinessObject businessObject = this.businessObjectService.getFilledByKey(code);
			businessObject.setPermission(businessPermision.getBusObj(code));
			IBusinessData busData = this.au.loadData(businessObject, null);
			dataMap.put(code, busData);
		}
		return dataMap;
	}

	public void n(BaseActionCmd actionCmd) {
		String modelCode;
		IBusinessData businessData;
		Map boDataMap = actionCmd.getBizDataMap();
		if (BeanUtils.isEmpty((Object) boDataMap)) {
			return;
		}
		BpmInstance instance = (BpmInstance) actionCmd.getBpmInstance();
		BpmNodeDef startNode = this.a.getStartEvent(instance.getDefId());
		IBusinessPermission businessPermision = this.aI.getNodeSavePermission(instance.getDefKey(),
				startNode.getNodeId(), boDataMap.keySet());
		BpmInstance topInstance = this.f.getTopInstance(instance);
		HashSet<String> topModelCodes = new HashSet<String>();
		if (topInstance != null) {
			DefaultBpmProcessDef topDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(topInstance.getDefId());
			List topBusLinks = this.aG.getByInstanceId(topInstance.getId());
			for (BpmDataModel topModel : topDef.getDataModelList()) {
				modelCode = topModel.getCode();
				if (!boDataMap.containsKey(modelCode))
					continue;
				topModelCodes.add(modelCode);
				businessData = (IBusinessData) boDataMap.get(modelCode);
				businessData.getBusTableRel().getBusObj().setPermission(businessPermision.getBusObj(modelCode));
				this.au.saveData(businessData);
				this.a(businessData, modelCode, topInstance, topBusLinks);
			}
		}
		List busLinkList = this.aG.getByInstanceId(instance.getId());
		DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(instance.getDefId());
		for (BpmDataModel dataModel : bpmProcessDef.getDataModelList()) {
			modelCode = dataModel.getCode();
			if (!boDataMap.containsKey(modelCode) || topModelCodes.contains(modelCode))
				continue;
			businessData = (IBusinessData) boDataMap.get(modelCode);
			businessData.getBusTableRel().getBusObj().setPermission(businessPermision.getBusObj(modelCode));
			this.au.saveData(businessData);
			this.a(businessData, modelCode, instance, busLinkList);
		}
	}

	private void a(IBusinessData iBusinessData, String modelCode, BpmInstance instance, List<BpmBusLink> busLinks) {
		for (BpmBusLink link : busLinks) {
			if (!link.getBizId().equals(iBusinessData.getPk()))
				continue;
			return;
		}
		BpmBusLink busLink = new BpmBusLink();
		busLink.setBizCode(modelCode);
		busLink.setBizId(String.valueOf(iBusinessData.getPk()));
		busLink.setInstId(instance.getId());
		busLink.setDefId(instance.getDefId());
		this.b(modelCode);
		this.aG.create(busLink);
	}

	private void b(String partName) {
		DbOperator dbOperator = DbOperatorFactory.getLocal();
		if (StringUtil.isEmpty((String) partName)) {
			return;
		}
		if (aJ == -1) {
			boolean isSupport = dbOperator.supportPartition(tableName);
			int n = aJ = isSupport ? 1 : 0;
		}
		if (aJ == 0) {
			return;
		}
		if (aK.contains(partName)) {
			return;
		}
		boolean isPartExist = dbOperator.isExsitPartition(tableName, partName);
		if (isPartExist) {
			aK.add(partName);
			return;
		}
		dbOperator.createPartition(tableName, partName);
		aK.add(partName);
	}
}