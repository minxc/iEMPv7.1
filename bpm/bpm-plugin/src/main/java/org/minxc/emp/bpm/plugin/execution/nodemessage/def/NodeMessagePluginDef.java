package org.minxc.emp.bpm.plugin.execution.nodemessage.def;

import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;

import java.util.List;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;
import org.minxc.emp.bpm.plugin.execution.nodemessage.def.NodeMessage;

public class NodeMessagePluginDef extends AbstractBpmExecutionPluginDef {
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