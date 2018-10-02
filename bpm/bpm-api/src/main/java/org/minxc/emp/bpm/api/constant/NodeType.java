package org.minxc.emp.bpm.api.constant;

/**
 * 流程节点类型
 */
public enum NodeType {

    START("StartNoneEvent", "开始节点"),
    END("EndNoneEvent", "结束节点"),
    USERTASK("UserTask", "用户任务节点"),
    SIGNTASK("SignTask", "会签任务节点"),
    SUBPROCESS("SubProcess", "子流程"),
    CALLACTIVITY("CallActivity", "外部子流程"),
    EXCLUSIVEGATEWAY("ExclusiveGateway", "分支网关"),
    PARALLELGATEWAY("ParallelGateway", "同步网关"),
    INCLUSIVEGATEWAY("InclusiveGateway", "条件网关"),
    SUBSTARTGATEWAY("SubStartGateway", "内嵌子流程开始网关"),
    SUBENDGATEWAY("SubEndGateway", "内嵌子流程结束网关"),
    SUBMULTISTARTGATEWAY("SubMultiStartGateway", "多实例内嵌子流程开始网关"),

    SERVICETASK("ServiceTask", "服务任务节点");

    // 键
    private String key = "";
    // 值
    private String value = "";

    // 构造方法
    private NodeType(String key, String value) {
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
    public static NodeType fromKey(String key) {
        for (NodeType c : NodeType.values()) {
            if (c.getKey().equalsIgnoreCase(key))
                return c;
        }
        throw new IllegalArgumentException(key);
    }

}
