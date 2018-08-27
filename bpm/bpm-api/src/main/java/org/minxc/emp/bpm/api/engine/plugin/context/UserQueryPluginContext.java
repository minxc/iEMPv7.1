package org.minxc.emp.bpm.api.engine.plugin.context;

import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;

/**
 * 用户查询插件的运行环境
 */
public interface UserQueryPluginContext {
    public Class<? extends RunTimePlugin> getUserQueryPluginClass();
}
