package org.minxc.emp.bpm.api.model.nodedef.impl;

import java.util.List;

import org.minxc.emp.bpm.api.engine.plugin.context.BpmPluginContext;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;

/**
 * 网关节点定义。
 * <pre>
 * 描述：TODO
 * </pre>
 */
public class GateWayBpmNodeDef extends BaseBpmNodeDef {

    @Override
    public List<BpmPluginContext> getBpmPluginContexts() {
        throw new RuntimeException("GateWayBpmNodeDef not support getBpmPluginContexts method");
    }

    /**
     * 获得内部子流程的流程定义。
     *
     * @return Map<String   ,   BpmNodeDef> key：nodeId，value：BpmNodeDef
     * @throws
     * @since 1.0.0
     */
    public BpmProcessDef getChildBpmProcessDef() {
        throw new RuntimeException("GateWayBpmNodeDef not support getChildBpmProcessDef method");
    }


}
