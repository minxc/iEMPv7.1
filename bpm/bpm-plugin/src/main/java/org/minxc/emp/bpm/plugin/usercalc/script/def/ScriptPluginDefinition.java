package org.minxc.emp.bpm.plugin.usercalc.script.def;

import javax.validation.constraints.NotEmpty;

import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractUserCalculatePluginDefinition;

public class ScriptPluginDefinition extends AbstractUserCalculatePluginDefinition {
	private static final long serialVersionUID = 3971853142447396218L;
	@NotEmpty(message = "脚本插件，脚本不能为空")
	private String script = "";
	@NotEmpty(message = "脚本插件，脚本描述不能为空")
	private String description = "";

	public String getScript() {
		return this.script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}