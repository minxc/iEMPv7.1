package org.minxc.emp.bpm.plugin.execution.nodemessage.def;


import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractBpmnExecutionPluginDefinition;

public class NodeMessagePluginDefinition extends AbstractBpmnExecutionPluginDefinition {
	
	private static final long serialVersionUID = -4292516613726022091L;
	@Valid
	@NotEmpty
	private List<NodeMessage> nodeMessageList;

	public NodeMessagePluginDefinition(List<NodeMessage> nodeMessageList) {
		this.nodeMessageList = nodeMessageList;
	}

	public List<NodeMessage> getNodeMessageList() {
		return this.nodeMessageList;
	}

	public void setNodeMessageList(List<NodeMessage> nodeMessageList) {
		this.nodeMessageList = nodeMessageList;
	}
}