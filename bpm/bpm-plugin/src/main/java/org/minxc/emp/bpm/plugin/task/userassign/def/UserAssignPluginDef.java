package org.minxc.emp.bpm.plugin.task.userassign.def;

import org.minxc.emp.bpm.api.engine.plugin.def.UserAssignRule;
import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmTaskPluginDef;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

public class UserAssignPluginDef extends AbstractBpmTaskPluginDef {
	@Valid
	List<UserAssignRule> ruleList = new ArrayList<UserAssignRule>();

	public List<UserAssignRule> getRuleList() {
		return this.ruleList;
	}

	public void setRuleList(List<UserAssignRule> ruleList) {
		this.ruleList = ruleList;
	}
}