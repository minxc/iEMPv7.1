package org.minxc.emp.bpm.plugin.usercalc.samenode.context;

import com.alibaba.fastjson.JSONObject;

import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.context.AbstractUserCalculatePluginContext;
import org.minxc.emp.bpm.plugin.usercalc.samenode.def.SameNodePluginDefinition;
import org.minxc.emp.bpm.plugin.usercalc.samenode.runtime.SameNodePlugin;
import org.minxc.emp.core.util.JsonUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class SameNodePluginContext extends AbstractUserCalculatePluginContext<SameNodePluginDefinition> {
	private static final long serialVersionUID = 919433269116580830L;

	public String getDescription() {
		SameNodePluginDefinition def = (SameNodePluginDefinition) this.getBpmPluginDef();
		if (def == null) {
			return "";
		}
		return "节点：" + def.getNodeId();
	}

	public String getTitle() {
		return "相同节点执行人";
	}

	public Class<? extends RunTimePlugin> getPluginClass() {
		return SameNodePlugin.class;
	}

	protected SameNodePluginDefinition parseJson(JSONObject pluginJson) {
		SameNodePluginDefinition def = new SameNodePluginDefinition();
		String nodeId = JsonUtil.getString((JSONObject) pluginJson, (String) "nodeId");
		def.setNodeId(nodeId);
		return def;
	}

//	protected BpmnUserCalculatePluginDefinition parseJson(JSONObject jSONObject) {
//		return this.b(jSONObject);
//	}
}