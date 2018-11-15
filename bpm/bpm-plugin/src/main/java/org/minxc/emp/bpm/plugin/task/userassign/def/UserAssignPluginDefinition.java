package org.minxc.emp.bpm.plugin.task.userassign.def;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.minxc.emp.bpm.api.engine.plugin.def.UserAssignRule;
import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractBpmnTaskPluginDefinition;

public class UserAssignPluginDefinition extends AbstractBpmnTaskPluginDefinition {
	
	private static final long serialVersionUID = 4198014798083213885L;
	@Valid
	List<UserAssignRule> ruleList = new ArrayList<UserAssignRule>();

	public List<UserAssignRule> getRuleList() {
		return this.ruleList;
	}

	public void setRuleList(List<UserAssignRule> ruleList) {
		this.ruleList = ruleList;
	}
}