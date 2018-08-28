package org.minxc.emp.bpm.plugin.execution.taskskip.def;

import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;

public class TaskSkipPluginDef extends AbstractBpmExecutionPluginDef {
	private String l;
	private String script = "";

	public String getScript() {
		return this.script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getSkipTypeArr() {
		return this.l;
	}

	public void setSkipTypeArr(String skipTypeArr) {
		this.l = skipTypeArr;
	}
}