package org.minxc.emp.bpm.api.engine.data;

import org.minxc.emp.bpm.api.engine.data.result.BpmnFlowData;
import org.minxc.emp.bpm.api.engine.data.result.BpmnFlowInstanceData;
import org.minxc.emp.form.api.model.FormType;



/**
 * 流程数据访问器
 *
 * @author xianchang.min
 */
public interface BpmnFlowDataAccessor {

    /**
     * 获取流程实例数据<br>
     * 默认使用全局实例表单
     *
     * @param instanceId
     * @param formType
     * @param nodeId     审批节点为空则为全局实例表单
     * @return
     */
    BpmnFlowInstanceData getInstanceData(String instanceId, FormType formType, String nodeId);

    /**
     * 流程启动节点获取表单数据等
     *
     * @param defId      草稿启动时可为空
     * @param instanceId 草稿启动时不为空
     * @param formType
     * @param readonly 
     * @return
     */
    BpmnFlowData getStartFlowData(String defId, String instanceId, FormType formType, Boolean readonly);

    /**
     * 获取任务数据
     *
     * @param defId      草稿启动时可为空
     * @param instanceId 草稿启动时不为空
     * @param formType
     * @return
     */
    BpmnFlowData getFlowTaskData(String taskId, FormType formType);

}
