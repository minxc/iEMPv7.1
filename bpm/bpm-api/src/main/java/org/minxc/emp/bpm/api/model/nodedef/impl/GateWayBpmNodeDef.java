package org.minxc.emp.bpm.api.model.nodedef.impl;

import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.api.model.def.BpmnProcessDef;

import java.util.List;

/**
 * 网关节点定义。
 * <pre>
 * 描述：TODO
 * </pre>
 */
public class GateWayBpmNodeDef extends BaseBpmnNodeDef {

    @Override
    public List<BpmnPluginContext> getBpmPluginContexts() {
        throw new RuntimeException("GateWayBpmNodeDef not support getBpmPluginContexts method");
    }

    /**
     * 获得内部子流程的流程定义。
     *
     * @return Map<String   ,   BpmNodeDef> key：nodeId，value：BpmNodeDef
     * @throws
     * @since 1.0.0
     */
    public BpmnProcessDef getChildBpmProcessDef() {
        throw new RuntimeException("GateWayBpmNodeDef not support getChildBpmProcessDef method");
    }


}
