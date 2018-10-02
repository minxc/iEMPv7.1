package org.minxc.emp.bpm.api.engine.constant;

/**
 * 逻辑计算类型。
 */
public enum LogicType {

    AND("and", "与"),
    OR("or", "或"),
    EXCLUDE("exclude", "非");

    // 键
    private String key = "";
    // 值
    private String value = "";

    // 构造方法
    private LogicType(String key, String value) {
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
    public static LogicType fromKey(String key) {
        for (LogicType c : LogicType.values()) {
            if (c.getKey().equalsIgnoreCase(key))
                return c;
        }
        throw new IllegalArgumentException(key);
    }
}
