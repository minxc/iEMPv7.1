package org.minxc.emp.bpm.api.model.task;

import org.minxc.emp.bpm.api.constant.TaskType;

public interface IBpmTask {
    public static final short STATUS_SUSPEND = 1;
    public static final short STATUS_NO_SUSPEND = 0;

    /**
     * 返回 任务ID
     *
     * @return
     */
    public String getId();

    /**
     * 返回 任务名称
     *
     * @return
     */
    public String getName();

    /**
     * 返回 待办事项标题
     *
     * @return
     */
    public String getSubject();

    /**
     * 返回 关联的任务ID
     *
     * @return
     */
    public String getTaskId();

    /**
     * 返回 关联 - 节点执行ID
     *
     * @return
     */
    public String getActExecutionIdId();

    /**
     * 返回 关联 - 任务节点ID
     *
     * @return
     */
    public String getNodeId();

    /**
     * 返回 关联 - 流程实例ID
     *
     * @return
     */
    public String getInstId();


    /**
     * 返回 关联 - 流程定义ID
     *
     * @return
     */
    public String getDefId();

    /**
     * 返回 任务执行人ID
     *
     * @return
     */
    public String getAssigneeId();

    /**
     * 返回 任务状态。
     *
     * @return
     * @see TaskType
     */
    public String getStatus();

    /**
     * 返回 任务优先级
     *
     * @return
     */
    public Integer getPriority();

    /**
     * 返回 任务创建时间
     *
     * @return
     */
    public java.util.Date getCreateTime();

    /**
     * 返回 任务到期时间
     *
     * @return
     */
    public java.util.Date getDueTime();

    /**
     * 任务类型
     *
     * @return
     */
    public String getTaskType();

    /**
     * 增加任务的父ID
     *
     * @return String
     * @throws
     * @since 1.0.0
     */
    public String getParentId();

    /**
     * Act流程实例ID。
     *
     * @return String
     */
    public String getActInstId();

	public void setAssigneeId(String id);

	public void setAssigneeNames(String name);

}