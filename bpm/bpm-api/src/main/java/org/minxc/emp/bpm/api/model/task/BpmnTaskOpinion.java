package org.minxc.emp.bpm.api.model.task;

/**
 * 流程审批意见
 *
 * @author min.xianchang
 */
public interface BpmnTaskOpinion {
    /**
     * 返回 意见ID
     *
     * @return
     */
    public String getId();

    /**
     * 返回 流程实例ID
     *
     * @return
     */
    public String getInstId();

    /**
     * 返回 父流程实例ID
     *
     * @return
     */
    public String getSupInstId();

    /**
     * 返回 任务ID
     *
     * @return
     */
    public String getTaskId();

    /**
     * 返回 任务定义Key
     *
     * @return
     */
    public String getTaskKey();

    /**
     * 返回 任务名称
     *
     * @return
     */
    public String getTaskName();

    /**
     * 返回 任务令牌
     *
     * @return
     */
    public String getToken();

    /**
     * 返回 任务分配的用户
     *
     * @return
     */
    public String getAssignInfo();

    /**
     * 返回 审批人
     *
     * @return
     */
    public String getApprover();

    /**
     * 返回 审批人名字
     *
     * @return
     */
    public String getApproverName();

    /**
     * 返回 审批意见
     *
     * @return
     */
    public String getOpinion();

    /**
     * 返回 审批状态。start=发起流程；awaiting_check=待审批；agree=同意；against=反对；return=驳回；abandon=弃权；retrieve=追回
     *
     * @return
     */
    public String getStatus();

    /**
     * 返回 表单定义ID
     *
     * @return
     */
    public String getFormId();

    /**
     * 返回 create_by_
     *
     * @return
     */
    public String getCreateBy();

    /**
     * 返回 执行开始时间
     *
     * @return
     */
    public java.util.Date getCreateTime();

    public java.util.Date getApproveTime();


}