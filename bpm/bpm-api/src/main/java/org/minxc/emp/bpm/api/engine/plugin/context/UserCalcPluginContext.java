package org.minxc.emp.bpm.api.engine.plugin.context;


/**
 * 用户插件。
 * 
 * 
 */
public interface UserCalcPluginContext extends PluginContext {


    /**
     * 获取人员描述。
     * 
     * 用户：A,B
     * 
     *
     * @param bpmPluginDef
     * @return String
     */
    String getDescription();


}
