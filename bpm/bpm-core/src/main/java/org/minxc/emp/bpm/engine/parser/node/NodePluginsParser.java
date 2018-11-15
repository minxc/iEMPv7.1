package org.minxc.emp.bpm.engine.parser.node;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.api.model.nodedef.impl.BaseBpmnNodeDefinition;
import org.minxc.emp.core.util.AppContextUtil;
import org.springframework.stereotype.Component;

@Component
public class NodePluginsParser extends AbsNodeParse<BpmnPluginContext> {
	
	
	@Override
	public Object parseDef(BaseBpmnNodeDefinition userNodeDef, String json) {
		JSONObject plugins = JSON.parseObject((String) json);
		ArrayList<BpmnPluginContext> pluginContextList = new ArrayList<BpmnPluginContext>();
		for (String pluginName : plugins.keySet()) {
			BpmnPluginContext pluginContext = (BpmnPluginContext) AppContextUtil
					.getBean((String) (pluginName + "PluginContext"));
			if (pluginContext instanceof BpmnPluginContext) {
				Object object = plugins.get((Object) pluginName);
				pluginContext.parse((JSON) object);
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
	@Override
	public void setDefParam(BaseBpmnNodeDefinition userNodeDef, Object object) {
		ArrayList pluginContextList = (ArrayList) object;
		userNodeDef.setBpmnPluginContexts((List) pluginContextList);
	}
}