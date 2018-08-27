package org.minxc.emp.bpm.api.constant;

/**
 * 任务状态。
 */
public enum TaskType {
    NORMAL("NORMAL", "普通任务"),
    SIGN("SIGN", "会签任务"),
    SUBFLOW("SUBFLOW", "子流程任务"),
    AGENT("AGENT", "代理任务"),
    DELIVERTO("DELIVERTO", "转办任务"),
    TRANSFORMING("TRANSFORMING", "事项任务");

    // 键
    private String key = "";
    // 值
    private String value = "";

    // 构造方法
    private TaskType(String key, String value) {
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
    public static TaskType fromKey(String key) {
        for (TaskType c : TaskType.values()) {
            if (c.getKey().equalsIgnoreCase(key))
                return c;
        }
        throw new IllegalArgumentException(key);
    }
}
