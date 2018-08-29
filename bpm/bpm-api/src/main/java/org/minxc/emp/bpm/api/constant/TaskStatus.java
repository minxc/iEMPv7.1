package org.minxc.emp.bpm.api.constant;

/**
 * 任务状态
 */
public enum TaskStatus {
    NORMAL("NORMAL", "普通", "普通订单"),
    SUSPEND("SUSPEND", "挂起", "超管挂起任务"),
    LOCK("LOCK", "锁定", "个人将任务锁定至个人名下"),
    AGENCY("AGENCY", "代理", "代理其他人的任务"),
    BACK("BACK", "驳回", "被驳回的任务"),
    DESIGNATE("DESIGNATE", "指派", "个人将任务指派到某个人名下"),
    DRAG("DRAG", "捞单", "从捞单池中获取的订单");

    // 键
    private String key = "";
    // 值
    private String value = "";

    private String desc = "";

    // 构造方法
    private TaskStatus(String key, String value, String desc) {
        this.key = key;
        this.value = value;
    }

    // =====getting and setting=====
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
    public static TaskStatus fromKey(String key) {
        for (TaskStatus c : TaskStatus.values()) {
            if (c.getKey().equalsIgnoreCase(key))
                return c;
        }
        throw new IllegalArgumentException(key);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
