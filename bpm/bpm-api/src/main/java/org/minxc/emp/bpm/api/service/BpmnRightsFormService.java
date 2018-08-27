package org.minxc.emp.bpm.api.service;

import java.util.Set;

import org.minxc.emp.bpm.api.engine.data.result.BpmnFlowData;
import org.minxc.emp.business.api.model.BusinessPermission;
import org.minxc.emp.form.api.model.FormType;


/**
 * 流程权限、表单获取接口。
 */
public interface BpmnRightsFormService {

	/**
	 * <pre>
	 * 获取页面表单以及权限。
	 * 如果节点未配置权限，则获取全局表单，和全局表单的权限（表单往往与权限一致展示处理）
	 * </pre>
	 * @param flowData
	 * @param nodeId
	 * @param formType
	 * @param isReadOnly
	 * @return
	 */
	BusinessPermission getInstanceFormPermission(BpmnFlowData flowData, String nodeId, FormType formType, boolean isReadOnly);
	
	/**
	 * 节点保存权限，若节点未配置。则不管了。也不获取全局权限。直接使用默认编辑权限即可
	 * @param defKey
	 * @param nodeId
	 * @param boCodes
	 * @return
	 */
	BusinessPermission getNodeSavePermission(String defKey, String nodeId, Set<String> boCodes);

}
