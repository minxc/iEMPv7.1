package org.minxc.emp.bpm.api.model.def;

import java.io.Serializable;

/**
 * 节点的其他属性。
 */
public class NodeProperties implements Serializable {
    /**
     * 驳回后按流程图执行
     */
    public static final String BACK_MODEL_NORMAL = "normal";
    /**
     * 驳回后直接返回原节点
     */
    public static final String BACK_MODEL_BACK = "back";
    /**
     * 驳回后使用历史处理人
     */
    public static final String BACK_USER_MODEL_HISTORY = "history";
    /**
     * 驳回后使用节点配置人员
     */
    public static final String BACK_USER_MODEL_NORMAL = "normal";

    private static final long serialVersionUID = -3157546646728816168L;
    //节点ID
    private String nodeId = "";
    //跳转类型(common,free,select)
    private String jumpType = "";
    //允许执行人空
    private boolean allowExecutorEmpty = true;
    //返回模式(direct:驳回后返回,normal:流程图)
    private String backMode = BACK_MODEL_NORMAL;
    //返回节点，驳回默认节点，不设置为上一节点
    private String backNode = "";
    //驳回处理人模式： 历史处理人：history, normal：正常节点上配置的人员
    private String backUserMode = BACK_USER_MODEL_HISTORY;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    public boolean isAllowExecutorEmpty() {
        return allowExecutorEmpty;
    }

    public void setAllowExecutorEmpty(boolean allowExecutorEmpty) {
        this.allowExecutorEmpty = allowExecutorEmpty;
    }

    public String getBackMode() {
        return backMode;
    }

    public void setBackMode(String backMode) {
        this.backMode = backMode;
    }

    public String getBackNode() {
        return backNode;
    }

    public void setBackNode(String backNode) {
        this.backNode = backNode;
    }

    public String getBackUserMode() {
        return backUserMode;
    }

    public void setBackUserMode(String backUserMode) {
        this.backUserMode = backUserMode;
    }
}
