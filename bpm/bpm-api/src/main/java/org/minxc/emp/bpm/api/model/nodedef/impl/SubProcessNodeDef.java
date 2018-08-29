package org.minxc.emp.bpm.api.model.nodedef.impl;

import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;

/**
 * 内部子流程节点定义扩展。
 */
public class SubProcessNodeDef extends BaseBpmNodeDef {
 
    private static final long serialVersionUID = -1165886168391484970L;

    public SubProcessNodeDef() {
        setType(NodeType.SUBPROCESS);
    }

    private BpmProcessDef bpmChildProcessDef;

    /**
     * 获得内部子流程的流程定义。
     *
     * @return Map<String   ,   BpmNodeDef> key：nodeId，value：BpmNodeDef
     * @throws
     * @since 1.0.0
     */
    public BpmProcessDef getChildBpmProcessDef() {
        return bpmChildProcessDef;
    }

    public void setChildBpmProcessDef(BpmProcessDef bpmChildProcessDef) {
        this.bpmChildProcessDef = bpmChildProcessDef;
    }
 

}
