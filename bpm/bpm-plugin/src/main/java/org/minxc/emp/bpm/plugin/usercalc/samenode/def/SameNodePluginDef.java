package org.minxc.emp.bpm.plugin.usercalc.samenode.def;

import com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
import org.hibernate.validator.constraints.NotEmpty;

public class SameNodePluginDef extends AbstractUserCalcPluginDef {
	@NotEmpty(message = "人员插件相同节点执行人，节点ID不能为空")
	private String a = "";

	public String getNodeId() {
		return this.a;
	}

	public void setNodeId(String nodeId) {
		this.a = nodeId;
	}
}