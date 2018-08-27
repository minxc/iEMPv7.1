package org.minxc.emp.bpm.api.engine.plugin.context;

import com.alibaba.fastjson.JSON;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnPluginDef;


public interface PluginParse<T extends BpmnPluginDef> {
    /**
     * 解析插件定义。
     *
     * @param pluginDefJson
     * @return BpmPluginDef
     */
    public T parse(JSON json);

    public T parse(String jsonStr);

    /**
     * 返回JSON
     *
     * @return String
     */
    JSON getJson();


    /**
     * 插件类型。
     *
     * @return String
     */
    String getType();

}
