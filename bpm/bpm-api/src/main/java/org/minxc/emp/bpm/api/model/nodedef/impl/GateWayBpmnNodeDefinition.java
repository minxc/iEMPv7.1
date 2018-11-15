package org.minxc.emp.bpm.api.model.nodedef.impl;

import java.util.List;

import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;

/**
 * 网关节点定义。
 * 
 * TODO
 * 
 */
public class GateWayBpmnNodeDefinition extends BaseBpmnNodeDefinition {

    @Override
    public List<BpmnPluginContext> getBpmnPluginContexts() {
        throw new RuntimeException("GateWayBpmnNodeDefinition not support getBpmnPluginContexts method");
    }

    /**
     * 获得内部子流程的流程定义。
     *
     * @return Map<String   ,   BpmNodeDef> key：nodeId，value：BpmNodeDef
     * @throws
     * @since 1.0.0
     */
    public BpmProcessDef getChildBpmProcessDef() {
        throw new RuntimeException("GateWayBpmnNodeDefinition not support getChildBpmProcessDef method");
    }


}
