package org.minxc.emp.bpm.api.model.def;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmnDef;
import org.minxc.emp.bpm.api.model.nodedef.BpmnNodeDef;

import java.util.List;


/**
 * <pre>
 * 描述：BPM流程定义接口
 * </pre>
 */
public interface BpmnProcessDef extends BpmnDef {

    /**
     * 获取流程定义KEY。
     *
     * @return String
     */
    String getDefKey();
    
    /**
     * 流程名称
     *
     * @return String
     */
    String getName();

    /**
     * 流程定义ID
     *
     * @return String
     */
    String getProcessDefinitionId();

    /**
     * 流程节点定义
     *
     * @return List&lt;BpmNodeDef>
     */
    List<BpmnNodeDef> getBpmnNodeDefs();


    /**
     * 取得上级流程定义。
     */
    BpmnProcessDef getParentProcessDef();


    /**
     * 获取发起事件。
     *
     * @return BpmNodeDef
     */
    BpmnNodeDef getStartEvent();

    /**
     * 获取第一个节点。
     * 第一个节点的定义是指，开始事件后的第一个节点。
     *
     * @return List&lt;BpmNodeDef>
     */
    List<BpmnNodeDef> getStartNodes();

    /**
     * 获取流程的结束节点。
     *
     * @param processDefId
     * @return List&lt;BpmNodeDef>
     */
    List<BpmnNodeDef> getEndEvents();

}
