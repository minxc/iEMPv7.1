package org.minxc.emp.bpm.api.model.inst;

public interface BpmnInstance {
	public static final Short INSTANCE_FORBIDDEN = 1;
	public static final Short INSTANCE_NO_FORBIDDEN = 0;
    /**
     * 返回 流程实例ID
     *
     * @return
     */
    public abstract String getId();

    /**
     * 返回 流程实例标题
     *
     * @return
     */
    public abstract String getSubject();

    /**
     * 返回 流程定义ID
     *
     * @return
     */
    public abstract String getDefId();

    /**
     * 返回 BPMN流程定义ID
     *
     * @return
     */
    public abstract String getActDefId();

    /**
     * 返回 流程定义Key
     *
     * @return
     */
    public abstract String getDefKey();

    /**
     * 返回 流程名称
     *
     * @return
     */
    public abstract String getDefName();

    /**
     * 返回 关联数据业务主键
     *
     * @return
     */
    public abstract String getBizKey();

    /**
     * 返回 实例状态
     *
     * @return
     */
    public abstract String getStatus();

    /**
     * 返回 实例结束时间
     *
     * @return
     */
    public abstract java.util.Date getEndTime();

    /**
     * 返回 持续时间(ms)
     *
     * @return
     */
    public abstract Long getDuration();

    /**
     * 返回 所属分类ID
     *
     * @return
     */
    public abstract String getTypeId();

    /**
     * 返回 BPMN流程实例ID
     *
     * @return
     */
    public abstract String getActInstId();

    /**
     * 返回 创建人ID
     *
     * @return
     */
    public abstract String getCreateBy();

    /**
     * 返回 创建人
     *
     * @return
     */
    public abstract String getCreator();

    /**
     * 返回 创建时间
     *
     * @return
     */
    public abstract java.util.Date getCreateTime();

    /**
     * 返回 创建者所属组织ID
     *
     * @return
     */
    public abstract String getCreateOrgId();

    /**
     * 返回 更新人ID
     *
     * @return
     */
    public abstract String getUpdateBy();

    /**
     * 返回 更新时间
     *
     * @return
     */
    public abstract java.util.Date getUpdateTime();

    /**
     * 返回 是否正式数据
     *
     * @return
     */
    public abstract String getIsFormmal();

    /**
     * 返回 父实例Id
     *
     * @return
     */
    public abstract String getParentInstId();

    /**
     * 返回 禁止
     *
     * @return
     */
    public abstract Short getIsForbidden();

    /**
     * 返回 data_mode_
     *
     * @return
     */
    public abstract String getDataMode();

    /**
     * 返回 support_mobile_
     *
     * @return
     */
    public abstract Integer getSupportMobile();

    /**
     * 返回 父流程定义节点ID
     *
     * @return
     */
    public abstract String getSuperNodeId();

    public abstract Boolean hasCreate();

    void setHasCreate(Boolean hasCreate);

}