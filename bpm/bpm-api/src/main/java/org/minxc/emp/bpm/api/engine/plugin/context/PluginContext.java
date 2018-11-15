package org.minxc.emp.bpm.api.engine.plugin.context;

import java.io.Serializable;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmnPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;

public interface PluginContext extends Serializable{


    public final static String PLUGINCONTEXT = "PluginContext";

    /**
     * 插件运行时的Class
     *
     * @return String
     * @throws
     * @since 1.0.0
     */
    Class<? extends RunTimePlugin> getPluginClass();

    /**
     * 返回流程插件定义
     *
     * @return BpmnPluginDef
     * @throws
     * @since 1.0.0
     */
    BpmnPluginDef getBpmPluginDef();

    /**
     * 获取插件标题。
     *
     * @return String
     */
    String getTitle();
    
}
