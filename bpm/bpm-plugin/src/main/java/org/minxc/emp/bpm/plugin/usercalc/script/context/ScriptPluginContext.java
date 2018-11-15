package org.minxc.emp.bpm.plugin.usercalc.script.context;

import com.alibaba.fastjson.JSONObject;

import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.context.AbstractUserCalculatePluginContext;
import org.minxc.emp.bpm.plugin.usercalc.script.def.ScriptPluginDefinition;
import org.minxc.emp.bpm.plugin.usercalc.script.runtime.ScriptPlugin;
import org.minxc.emp.core.util.JsonUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class ScriptPluginContext extends AbstractUserCalculatePluginContext<ScriptPluginDefinition> {
	private static final long serialVersionUID = -2353875054502587417L;

	public String getDescription() {
		ScriptPluginDefinition def = (ScriptPluginDefinition) this.getBpmPluginDef();
		if (def == null) {
			return "";
		}
		return def.getDescription();
	}

	public Class<? extends RunTimePlugin> getPluginClass() {
		return ScriptPlugin.class;
	}

	public String getTitle() {
		return "脚本";
	}

	protected ScriptPluginDefinition parseJson(JSONObject pluginJson) {
		ScriptPluginDefinition def = new ScriptPluginDefinition();
		String script = pluginJson.getString("script");
		String description = JsonUtil.getString((JSONObject) pluginJson, (String) "description", (String) "脚本");
		def.setScript(script);
		def.setDescription(description);
		return def;
	}

//	protected BpmnUserCalculatePluginDefinition parseJson(JSONObject jSONObject) {
//		return this.c(jSONObject);
//	}
}