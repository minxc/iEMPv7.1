package org.minxc.emp.bpm.plugin.usercalc.script.context;

import com.alibaba.fastjson.JSONObject;

import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
import org.minxc.emp.bpm.plugin.usercalc.script.def.ScriptPluginDef;
import org.minxc.emp.bpm.plugin.usercalc.script.runtime.ScriptPlugin;
import org.minxc.emp.core.util.JsonUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class ScriptPluginContext extends AbstractUserCalcPluginContext<ScriptPluginDef> {
	private static final long serialVersionUID = -2353875054502587417L;

	public String getDescription() {
		ScriptPluginDef def = (ScriptPluginDef) this.getBpmPluginDef();
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

	protected ScriptPluginDef parseJson(JSONObject pluginJson) {
		ScriptPluginDef def = new ScriptPluginDef();
		String script = pluginJson.getString("script");
		String description = JsonUtil.getString((JSONObject) pluginJson, (String) "description", (String) "脚本");
		def.setScript(script);
		def.setDescription(description);
		return def;
	}

//	protected BpmUserCalcPluginDef parseJson(JSONObject jSONObject) {
//		return this.c(jSONObject);
//	}
}