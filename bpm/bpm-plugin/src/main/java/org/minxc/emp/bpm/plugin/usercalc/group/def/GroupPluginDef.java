package org.minxc.emp.bpm.plugin.usercalc.group.def;

import javax.validation.constraints.NotEmpty;

import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;

public class GroupPluginDef extends AbstractUserCalcPluginDef {

	private static final long serialVersionUID = -7918828703071848183L;
	@NotEmpty(message = "人员插件组类型不能为空")
	private String type = "";
	private String typeName = "";
	@NotEmpty(message = "人员插件组KEY不能为空")
	private String ab = "";
	private String groupName = "";

	public String getGroupKey() {
		return this.ab;
	}

	public void setGroupKey(String groupKey) {
		this.ab = groupKey;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}