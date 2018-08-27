package org.minxc.emp.bpm.api.engine.action.cmd;

import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.model.task.BpmnTask;


/**
 * 任务处理命令接口
 */
public interface TaskActionCmd extends ActionCmd {


    /**
     * 动作类型。
     *
     * @return ActionType
     */
    ActionType getActionType();


    /**
     * 获取任务ID
     *
     * @return
     */
    String getTaskId();

    /**
     * 获取节点ID
     *
     * @return
     */
    String getNodeId();


    BpmnTask getBpmTask();


    void setBpmTask(BpmnTask task);

    /**
     * 目标节点，在一般情况下不需要指定，流程会按照流程图进行运行。
     * 在需要指定节点跳转的情况下，才会起作用。
     */
    String getDestination();

    void setDestination(String destination);


    String getOpinion();
}
