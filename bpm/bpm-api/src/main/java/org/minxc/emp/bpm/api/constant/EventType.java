package org.minxc.emp.bpm.api.constant;

/**
 * 描述：事件类型,如节点创建事件,节点结束事件,任务创建事件,任务结束事件等
 * 现在一般供插件开发使用，在各个Bpmn Listener的实现中设置。
 */
public enum EventType {
    /**
     * 流程启动（流程实例已经生成，但辅助操作未做）
     */
    START_EVENT("startEvent", "流程启动事件"),
    /**
     * 流程启动辅助操作完成后
     */
    START_POST_EVENT("postStartEvent", "流程启动后置事件"),
    /**
     * 流程结束之前
     */
    END_EVENT("endEvent", "流程结束事件"),
    MANUALEND("manualEnd", "流程人工终止事件"),
    /**
     * 流程结束之后
     */
    END_POST_EVENT("postEndEvent", "流程结束后置事件"),

    TASK_PRE_COMPLETE_EVENT("preTaskComplete", "任务完成前置事件"),
    /**
     * 任务创建（任务已生成，但未分配人员，可处理分配人员等前期事务）
     */
    TASK_CREATE_EVENT("taskCreate", "任务创建事件"),
    /**
     * 任务创建之后（已分配人员）
     */
    TASK_POST_CREATE_EVENT("postTaskCreate", "任务创建后置事件"),

    /**
     * 任务完成
     */
    TASK_COMPLETE_EVENT("taskComplete", "任务完成事件"),
    /**
     * 任务完成之后（执行完工作流本身辅助操作）
     */
    TASK_POST_COMPLETE_EVENT("postTaskComplete", "任务完成后置事件"),
    /**
     * 会签任务创建
     */
    TASK_SIGN_CREATE_EVENT("taskSignCreate", "会签任务创建"),
    /**
     * 会签任务创建之后（已分配人员）
     */
    TASK_SIGN_POST_CREATE_EVENT("postTaskSignCreate", "会签任务创建后置事件");

    // 键
    private String key = "";
    // 值
    private String value = "";

    // 构造方法
    private EventType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }

    /**
     * 通过key获取对象
     *
     * @param key
     * @return
     */
    public static EventType fromKey(String key) {
        for (EventType c : EventType.values()) {
            if (c.getKey().equalsIgnoreCase(key))
                return c;
        }
        throw new IllegalArgumentException(key);
    }

}
