package org.minxc.emp.bpm.service;

import java.util.Set;
import javax.annotation.Resource;

import org.minxc.emp.biz.api.constant.BusinessPermissionObjectType;
import org.minxc.emp.biz.api.model.BusinessPermission;
import org.minxc.emp.biz.api.service.BusinessPermissionService;
import org.minxc.emp.bpm.api.engine.data.result.BpmnFlowData;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.form.BpmForm;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.api.service.BpmnRightsFormService;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.form.api.model.FormCategory;
import org.minxc.emp.form.api.model.FormType;
import org.springframework.stereotype.Component;

@Component(value = "defaultBpmFormService")
public class DefaultBpmnRightsFormService implements BpmnRightsFormService {
	
	@Resource
    BpmnProcessDefinitionService a;
	@Resource
	BusinessPermissionService businessPermissionService;

	public BusinessPermission getInstanceFormPermission(BpmnFlowData flowData, String nodeId, FormType formType,
                                                        boolean isReadOnly) {
		BusinessPermission permision = null;
		BpmForm form = null;
		boolean isMobile = FormType.MOBILE == formType;
		DefaultBpmnProcessDefinition processDef = (DefaultBpmnProcessDefinition) this.a.getBpmProcessDef(flowData.getDefId());
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
					BpmnStatusCode.FLOW_FORM_LOSE);
		}
		if (FormCategory.INNER.equals((Object) form.getType())) {
			permision = this.businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjectType.FLOW.getKey(),
					processDef.getDefKey() + "-" + nodeId, processDef.getDataModelKeys(), true);
			flowData.setPermission(permision.getPermission(isReadOnly));
			flowData.setTablePermission(permision.getTablePermission(isReadOnly));
		}
		flowData.setForm(form);
		return permision;
	}

	public BusinessPermission getNodeSavePermission(String defKey, String nodeId, Set<String> bocodes) {
		String boCodes = null;
		if (BeanUtils.isNotEmpty(bocodes)) {
			boCodes = StringUtil.convertCollectionAsString(bocodes);
		}
		return this.businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjectType.FLOW.getKey(),
				defKey + "-" + nodeId, boCodes, true);
	}
}