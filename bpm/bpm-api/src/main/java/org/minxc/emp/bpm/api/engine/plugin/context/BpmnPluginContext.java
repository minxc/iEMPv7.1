package org.minxc.emp.bpm.api.engine.plugin.context;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnPluginDef;

import java.util.List;

/**
 * 插件上下文接口。
 */
public interface BpmnPluginContext<T extends BpmnPluginDef> extends PluginContext, PluginParse<T> ,Comparable<BpmnPluginContext>{
    /**
     * 返回该插件关联的事件集合
     *
     * @return List<EventType>
     * @throws
     * @since 1.0.0
     */
    public List<EventType> getEventTypes();
    
    
    Integer getSn();
}
