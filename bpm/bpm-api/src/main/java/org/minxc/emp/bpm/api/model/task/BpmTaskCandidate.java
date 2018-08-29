package org.minxc.emp.bpm.api.model.task;


public interface BpmTaskCandidate {


    /**
     * 返回 主键
     *
     * @return
     */
    public String getId();

    /**
     * 返回 任务ID
     *
     * @return
     */
    public String getTaskId();

    /**
     * 返回 候选人类型
     *
     * @return
     */
    public String getType();

    /**
     * 返回 执行人ID
     *
     * @return
     */
    public String getExecutor();

    /**
     * 返回 流程实例ID
     *
     * @return
     */
    public String getProcInstId();

}