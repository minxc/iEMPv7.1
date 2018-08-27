package org.minxc.emp.bpm.api.constant;


/**
 * 脚本类型
 */
public enum ScriptType {

    START("start", "开始脚本"),
    END("end", "结束脚本"),
    CREATE("create", "创建脚本"),
    COMPLETE("complete", "结束脚本"),
    MANUALEND("manualEnd", "人工终止");

    // 键
    private String key = "";
    // 值
    private String value = "";

    // 构造方法
    private ScriptType(String key, String value) {
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
    public static ScriptType fromKey(String key) {
        for (ScriptType c : ScriptType.values()) {
            if (c.getKey().equalsIgnoreCase(key))
                return c;
        }
        throw new IllegalArgumentException(key);
    }

}
