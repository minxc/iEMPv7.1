package org.minxc.emp.bpm.service;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.constant.IStatusCode;
import org.minxc.emp.core.api.exception.BusinessException;
import com.minxc.emp.core.util.BeanUtils;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bus.api.constant.BusinessPermissionObjType;
import com.dstz.bus.api.model.IBusinessPermission;
import com.dstz.bus.api.service.IBusinessPermissionService;
import com.dstz.form.api.model.FormCategory;
import com.dstz.form.api.model.FormType;
import java.util.Set;
import javax.annotation.Resource;

import org.minxc.emp.bpm.api.engine.data.result.BpmFlowData;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;
import org.minxc.emp.bpm.api.model.form.BpmForm;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.api.service.BpmRightsFormService;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.springframework.stereotype.Component;

@Component(value = "defaultBpmFormService")
public class DefaultBpmRightsFormService implements BpmRightsFormService {
	@Resource
	BpmProcessDefService a;
	@Resource
	IBusinessPermissionService businessPermissionService;

	public IBusinessPermission getInstanceFormPermission(BpmFlowData flowData, String nodeId, FormType formType,
			boolean isReadOnly) {
		IBusinessPermission permision = null;
		BpmForm form = null;
		boolean isMobile = FormType.MOBILE == formType;
		DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(flowData.getDefId());
		if (StringUtil.isEmpty((String) nodeId)) {
			form = isMobile ? processDef.getInstMobileForm() : processDef.getInstForm();
			nodeId = "instance";
		} else {
			BpmNodeDef nodeDef = this.a.getBpmNodeDef(flowData.getDefId(), nodeId);
			BpmForm bpmForm = form = isMobile ? nodeDef.getMobileForm() : nodeDef.getForm();
		}
		if (form == null || form.isFormEmpty()) {
			form = isMobile ? processDef.getGlobalMobileForm() : processDef.getGlobalForm();
			nodeId = "global";
		}
		if (form == null || form.isFormEmpty()) {
			throw new BusinessException(String.format("请配置%s[%s]的表单", processDef.getDefKey(), nodeId),
					(IStatusCode) BpmStatusCode.FLOW_FORM_LOSE);
		}
		if (FormCategory.INNER.equals((Object) form.getType())) {
			permision = this.businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjType.FLOW.getKey(),
					processDef.getDefKey() + "-" + nodeId, processDef.getDataModelKeys(), true);
			flowData.setPermission(permision.getPermission(isReadOnly));
			flowData.setTablePermission(permision.getTablePermission(isReadOnly));
		}
		flowData.setForm(form);
		return permision;
	}

	public IBusinessPermission getNodeSavePermission(String defKey, String nodeId, Set<String> bocodes) {
		String boCodes = null;
		if (BeanUtils.isNotEmpty(bocodes)) {
			boCodes = StringUtil.convertCollectionAsString(bocodes);
		}
		return this.businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjType.FLOW.getKey(),
				defKey + "-" + nodeId, boCodes, true);
	}
}