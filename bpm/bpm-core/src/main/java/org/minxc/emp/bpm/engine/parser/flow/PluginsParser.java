package org.minxc.emp.bpm.engine.parser.flow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnPluginDef;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.ValidateUtil;
import org.springframework.stereotype.Component;

@Component
public class PluginsParser extends AbsFlowParse<BpmnPluginContext> {
	
	
	public Object parseDef(DefaultBpmnProcessDefinition bpmProcessDef, String json) {
		JSONObject plugins = JSON.parseObject((String) json);
		ArrayList<BpmnPluginContext> pluginContextList = new ArrayList<BpmnPluginContext>();
		for (String pluginName : plugins.keySet()) {
			BpmnPluginContext pluginContext = (BpmnPluginContext) AppContextUtil
					.getBean((String) (pluginName + "PluginContext"));
			if (pluginContext instanceof BpmnPluginContext) {
				try {
					pluginContext.parse((JSON) plugins.get((Object) pluginName));
					BpmnPluginDef def = pluginContext.getBpmPluginDef();
					ValidateUtil.validate((Object) def);
				} catch (Exception e) {
					this.LOG.error("插件{}解析失败:{}！", new Object[]{pluginContext.getTitle(), e.getMessage(), e});
				}
			}
			pluginContextList.add(pluginContext);
		}
		return pluginContextList;
	}

	public String getKey() {
		return "plugins";
	}

	public String validate(Object o) {
		return null;
	}

	public void setDefParam(DefaultBpmnProcessDefinition bpmProcessDef, Object object) {
		ArrayList<BpmnPluginContext> pluginContextList = (ArrayList) object;
		bpmProcessDef.setPluginContextList(pluginContextList);
	}

}