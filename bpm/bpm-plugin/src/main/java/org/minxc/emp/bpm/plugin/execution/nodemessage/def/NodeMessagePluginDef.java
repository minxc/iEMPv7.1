package org.minxc.emp.bpm.plugin.execution.nodemessage.def;


import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
import org.minxc.emp.bpm.plugin.execution.nodemessage.def.NodeMessage;

public class NodeMessagePluginDef extends AbstractBpmExecutionPluginDef {
	
	private static final long serialVersionUID = -4292516613726022091L;
	@Valid
	@NotEmpty
	private List<NodeMessage> f;

	public NodeMessagePluginDef(List<NodeMessage> nodeMessageList) {
		this.f = nodeMessageList;
	}

	public List<NodeMessage> getNodeMessageList() {
		return this.f;
	}

	public void setNodeMessageList(List<NodeMessage> nodeMessageList) {
		this.f = nodeMessageList;
	}
}