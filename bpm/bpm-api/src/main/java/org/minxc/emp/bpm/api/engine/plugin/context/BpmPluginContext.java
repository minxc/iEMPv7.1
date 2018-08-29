package org.minxc.emp.bpm.api.engine.plugin.context;

import java.util.List;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmPluginDef;

/**
 * 插件上下文接口。
 */
public interface BpmPluginContext<T extends BpmPluginDef> extends PluginContext, PluginParse<T> ,Comparable<BpmPluginContext>{
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
