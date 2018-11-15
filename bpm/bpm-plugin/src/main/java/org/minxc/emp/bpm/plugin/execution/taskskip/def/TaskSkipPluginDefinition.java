package org.minxc.emp.bpm.plugin.execution.taskskip.def;

import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractBpmnExecutionPluginDefinition;

public class TaskSkipPluginDefinition extends AbstractBpmnExecutionPluginDefinition {

	private static final long serialVersionUID = -4003504624542764374L;
	private String skipTypeArr;
	private String script = "";

	public String getScript() {
		return this.script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getSkipTypeArr() {
		return this.skipTypeArr;
	}

	public void setSkipTypeArr(String skipTypeArr) {
		this.skipTypeArr = skipTypeArr;
	}
}