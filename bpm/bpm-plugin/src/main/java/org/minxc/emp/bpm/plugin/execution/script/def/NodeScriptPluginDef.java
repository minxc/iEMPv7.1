package org.minxc.emp.bpm.plugin.execution.script.def;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;

public class NodeScriptPluginDef extends AbstractBpmExecutionPluginDef {
	private static final long serialVersionUID = -3317887978999803032L;
	@NotEmpty(message = "事件脚本节点ID不能为空")
	private String a = "";
	private Map<EventType, String> k = new HashMap<EventType, String>();

	public String a(EventType event) {
		return this.k.get((Object) event);
	}

	public void a(EventType event, String scritp) {
		this.k.put(event, scritp);
	}

	public Map<EventType, String> getScript() {
		return this.k;
	}

	public void setScript(Map<EventType, String> script) {
		this.k = script;
	}

	public String getNodeId() {
		return this.a;
	}

	public void setNodeId(String nodeId) {
		this.a = nodeId;
	}
}