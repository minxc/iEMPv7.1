package org.minxc.emp.bpm.api.constant;

/**
 * 审批状态
 */
public enum OpinionStatus {
    /**
     * 提交
     */
    START("start", "提交"),

    /**
     * 结束
     */
    END("end", "结束"),

    /**
     * 待审批
     */
    AWAITING_CHECK("awaiting_check", "待审批"),
    /**
     * 同意
     */
    AGREE("agree", "同意"),
    /**
     * 反对
     */
    OPPOSE("oppose", "反对"),
    /**
     * 弃权
     */
    ABANDON("abandon", "弃权"),
    /**
     * 驳回
     */
    REJECT("reject", "驳回"),
    /**
     * 驳回到发起人
     */
    REJECT_TO_START("rejectToStart", "驳回到发起人"),
    /**
     * 撤销
     */
    REVOKER("revoker", "撤回"),
    /**
     * 撤销到发起人
     */
    REVOKER_TO_START("revoker_to_start", "撤回到发起人"),
    /**
     * 会签通过
     */
    SIGN_PASSED("signPass", "会签通过"),
    /**
     * 会签不通过
     */
    SIGN_NOT_PASSED("signNotPass", "会签不通过"),
    /**
     * 跳过执行
     */
    SKIP("skip", "跳过执行"),
    /**
     * 人工终止
     */
    MANUAL_END("manual_end", "人工终止");
    // 键
    private String key = "";
    // 值
    private String value = "";

    // 构造方法
    private OpinionStatus(String key, String value) {
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
    public static OpinionStatus fromKey(String key) {
        for (OpinionStatus c : OpinionStatus.values()) {
            if (c.getKey().equalsIgnoreCase(key))
                return c;
        }
        throw new IllegalArgumentException(key);
    }
}
