package org.minxc.emp.bpm.engine.data.handle;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;

import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.biz.api.model.BusinessObject;
import org.minxc.emp.biz.api.model.BusinessPermission;
import org.minxc.emp.biz.api.service.BusinessDataService;
import org.minxc.emp.biz.api.service.BusinessObjectService;
import org.minxc.emp.biz.api.service.BusinessPermissionService;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmDataModel;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.api.service.BpmnRightsFormService;
import org.minxc.emp.bpm.core.manager.BpmnBusinessLinkManager;
import org.minxc.emp.bpm.core.manager.BpmnInstanceManager;
import org.minxc.emp.bpm.core.model.BpmnBusinessLink;
import org.minxc.emp.bpm.core.model.BpmnInstanceImpl;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.minxc.emp.common.db.dboper.DbOperator;
import org.minxc.emp.common.db.dboper.DbOperatorFactory;
import org.minxc.emp.core.api.exception.SystemException;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BpmnBusinessDataHandle {

	@Resource
	private BpmnInstanceManager f;
	@Resource
	private BpmnProcessDefinitionService a;
	@Resource
	private BpmnBusinessLinkManager aG;
	@Resource
	private BpmnRightsFormService aI;
	@Resource
	private BusinessDataService au;
	@Autowired
	private BusinessObjectService businessObjectService;
	@Autowired
	private BusinessPermissionService businessPermissionService;
	private static int aJ = -1;
	private static Set<String> aK = Collections.synchronizedSet(new HashSet());
	private static final String tableName = "BPM_BUS_LINK";

	public Map<String, BusinessData> a(BusinessPermission businessPermision, BpmnInstanceImpl instance) {
		BusinessData busData;
		BusinessObject businessObject;
		HashMap<String, BusinessData> dataMap = new HashMap<String, BusinessData>();
		BpmnInstanceImpl topInstance = this.f.getTopInstance(instance);
		if (topInstance != null) {
			List<BpmnBusinessLink> topInstanceBusLinks = this.aG.getByInstanceId(topInstance.getId());
			for (BpmnBusinessLink busLink : topInstanceBusLinks) {
				businessObject = this.businessObjectService.getFilledByKey(busLink.getBizCode());
				businessObject.setPermission(businessPermision.getBusObj(busLink.getBizCode()));
				busData = this.au.loadData(businessObject, busLink.getBizId());
				if (busData == null) {
					throw new SystemException(
							String.format("bizCode[%s] bizId[%s]", busLink.getBizCode(), busLink.getBizId()),
							BpmnStatusCode.FLOW_BUS_DATA_LOSE);
				}
				dataMap.put(busLink.getBizCode(), busData);
			}
		}
		List<BpmnBusinessLink> busLinks = this.aG.getByInstanceId(instance.getId());
		for (BpmnBusinessLink busLink : busLinks) {
			businessObject = this.businessObjectService.getFilledByKey(busLink.getBizCode());
			businessObject.setPermission(businessPermision.getBusObj(busLink.getBizCode()));
			busData = this.au.loadData((BusinessObject) businessObject, (Object) busLink.getBizId());
			if (busData == null) {
				throw new SystemException(
						String.format("bizCode[%s] bizId[%s]", busLink.getBizCode(), busLink.getBizId()),
						BpmnStatusCode.FLOW_BUS_DATA_LOSE);
			}
			dataMap.put(busLink.getBizCode(), busData);
		}
		DefaultBpmnProcessDefinition processDef = (DefaultBpmnProcessDefinition) this.a.getBpmProcessDef(instance.getDefId());
		List<BpmDataModel> listDataModel = processDef.getDataModelList();
		for (BpmDataModel model : listDataModel) {
			String code = model.getCode();
			if (dataMap.containsKey(code))
				continue;
			BusinessObject businessObject2 = this.businessObjectService.getFilledByKey(code);
			businessObject2.setPermission(businessPermision.getBusObj(code));
			BusinessData busData2 = this.au.loadData(businessObject2, null);
			dataMap.put(code, busData2);
		}
		return dataMap;
	}

	public Map<String, BusinessData> a(BusinessPermission businessPermision, String defId) {
		HashMap<String, BusinessData> dataMap = new HashMap<String, BusinessData>();
		DefaultBpmnProcessDefinition processDef = (DefaultBpmnProcessDefinition) this.a.getBpmProcessDef(defId);
		List<BpmDataModel> listDataModel = processDef.getDataModelList();
		for (BpmDataModel model : listDataModel) {
			String code = model.getCode();
			BusinessObject businessObject = this.businessObjectService.getFilledByKey(code);
			businessObject.setPermission(businessPermision.getBusObj(code));
			BusinessData busData = this.au.loadData(businessObject, null);
			dataMap.put(code, busData);
		}
		return dataMap;
	}

	public void n(BaseActionCmd actionCmd) {
		String modelCode;
		BusinessData businessData;
		Map boDataMap = actionCmd.getBizDataMap();
		if (BeanUtils.isEmpty((Object) boDataMap)) {
			return;
		}
		BpmnInstanceImpl instance = (BpmnInstanceImpl) actionCmd.getBpmInstance();
		BpmNodeDef startNode = this.a.getStartEvent(instance.getDefId());
		BusinessPermission businessPermision = this.aI.getNodeSavePermission(instance.getDefKey(),
				startNode.getNodeId(), boDataMap.keySet());
		BpmnInstanceImpl topInstance = this.f.getTopInstance(instance);
		HashSet<String> topModelCodes = new HashSet<String>();
		if (topInstance != null) {
			DefaultBpmnProcessDefinition topDef = (DefaultBpmnProcessDefinition) this.a.getBpmProcessDef(topInstance.getDefId());
			List topBusLinks = this.aG.getByInstanceId(topInstance.getId());
			for (BpmDataModel topModel : topDef.getDataModelList()) {
				modelCode = topModel.getCode();
				if (!boDataMap.containsKey(modelCode))
					continue;
				topModelCodes.add(modelCode);
				businessData = (BusinessData) boDataMap.get(modelCode);
				businessData.getBusTableRel().getBusObj().setPermission(businessPermision.getBusObj(modelCode));
				this.au.saveData(businessData);
				this.a(businessData, modelCode, topInstance, topBusLinks);
			}
		}
		List busLinkList = this.aG.getByInstanceId(instance.getId());
		DefaultBpmnProcessDefinition bpmProcessDef = (DefaultBpmnProcessDefinition) this.a.getBpmProcessDef(instance.getDefId());
		for (BpmDataModel dataModel : bpmProcessDef.getDataModelList()) {
			modelCode = dataModel.getCode();
			if (!boDataMap.containsKey(modelCode) || topModelCodes.contains(modelCode))
				continue;
			businessData = (BusinessData) boDataMap.get(modelCode);
			businessData.getBusTableRel().getBusObj().setPermission(businessPermision.getBusObj(modelCode));
			this.au.saveData(businessData);
			this.a(businessData, modelCode, instance, busLinkList);
		}
	}

	private void a(BusinessData iBusinessData, String modelCode, BpmnInstanceImpl instance, List<BpmnBusinessLink> busLinks) {
		for (BpmnBusinessLink link : busLinks) {
			if (!link.getBizId().equals(iBusinessData.getPk()))
				continue;
			return;
		}
		BpmnBusinessLink busLink = new BpmnBusinessLink();
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