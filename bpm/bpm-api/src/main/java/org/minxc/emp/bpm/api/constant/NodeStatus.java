package org.minxc.emp.bpm.api.constant;

/**
 * 流程节点状态。
 */
public enum NodeStatus {

    SUBMIT("submit", "提交"),
    RE_SUBMIT("resubmit", "重新提交"),
    AGREE("agree", "同意"),
    PENDING("pending", "待审批"),
    OPPOSE("oppose", "反对"),
    BACK("back", "驳回"),
    BACK_TO_START("backToStart", "驳回到发起人"),
    COMPLETE("complete", "完成"),
    RECOVER("recover", "撤回"),
    RECOVER_TO_START("recoverToStart", "撤回到发起人"),
    SIGN_PASS("sign_pass", "会签通过"),
    SIGN_NO_PASS("sign_no_pass", "会签不通过"),
    MANUAL_END("manual_end", "人工终止"),
    ABANDON("abandon", "弃权"),
    SUSPEND("suspend", "挂起");

    // 键
    private String key = "";
    // 值
    private String value = "";

    // 构造方法
    private NodeStatus(String key, String value) {
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
    public static NodeStatus fromKey(String key) {
        for (NodeStatus c : NodeStatus.values()) {
            if (c.getKey().equalsIgnoreCase(key))
                return c;
        }
        throw new IllegalArgumentException(key);
    }

}
