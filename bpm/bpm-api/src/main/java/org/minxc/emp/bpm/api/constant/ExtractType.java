package org.minxc.emp.bpm.api.constant;

/**
 * 人员抽取类型。
 * 
 * 人员抽取类型。
 * 
 */
public enum ExtractType {
    /**
     * 不抽取
     */
    EXACT_NOEXACT("no", "不抽取"),
    /**
     * 抽取用户
     */
    EXACT_EXACT_USER("extract", "抽取用户");

    // 键
    private String key = "";
    // 值
    private String value = "";

    // 构造方法
    private ExtractType(String key, String value) {
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
    public static ExtractType fromKey(String key) {
        for (ExtractType c : ExtractType.values()) {
            if (c.getKey().equalsIgnoreCase(key))
                return c;
        }
        throw new IllegalArgumentException(key);
    }
}
