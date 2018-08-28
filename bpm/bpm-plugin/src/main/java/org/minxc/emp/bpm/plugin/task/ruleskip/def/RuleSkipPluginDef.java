package org.minxc.emp.bpm.plugin.task.ruleskip.def;

import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmTaskPluginDef;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.minxc.emp.bpm.plugin.task.ruleskip.def.JumpRule;

public class RuleSkipPluginDef extends AbstractBpmTaskPluginDef {
	@Valid
	private List<JumpRule> S = new ArrayList<JumpRule>();

	public List<JumpRule> getJumpRules() {
		return this.S;
	}

	public void setJumpRules(List<JumpRule> jumpRules) {
		this.S = jumpRules;
	}
}