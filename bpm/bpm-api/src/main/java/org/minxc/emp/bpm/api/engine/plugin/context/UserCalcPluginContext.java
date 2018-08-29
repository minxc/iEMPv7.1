package org.minxc.emp.bpm.api.engine.plugin.context;


/**
 * 用户插件。
 * <pre>
 * </pre>
 */
public interface UserCalcPluginContext extends PluginContext {


    /**
     * 获取人员描述。
     * <pre>
     * 用户：A,B
     * </pre>
     *
     * @param bpmPluginDef
     * @return String
     */
    String getDescription();


}
