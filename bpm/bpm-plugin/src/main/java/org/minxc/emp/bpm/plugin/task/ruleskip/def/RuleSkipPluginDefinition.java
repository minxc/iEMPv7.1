package org.minxc.emp.bpm.plugin.task.ruleskip.def;


import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractBpmnTaskPluginDefinition;

public class RuleSkipPluginDefinition extends AbstractBpmnTaskPluginDefinition {
	private static final long serialVersionUID = -4299829181314028741L;
	@Valid
	private List<JumpRule> S = new ArrayList<JumpRule>();

	public List<JumpRule> getJumpRules() {
		return this.S;
	}

	public void setJumpRules(List<JumpRule> jumpRules) {
		this.S = jumpRules;
	}
}