package org.minxc.emp.bpm.plugin.task.sign.def;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractBpmnTaskPluginDefinition;

public class SignTaskPluginDefinition extends AbstractBpmnTaskPluginDefinition {
	
	private static final long serialVersionUID = 5996255881839450739L;
	private Boolean T = false;
	private VoteType U = VoteType.PERCENT;
	private Boolean V = false;
	@NotEmpty
	@Min(value = 1L)
	private int W = 51;
	@NotBlank
	private String X = ActionType.OPPOSE.getKey();

	public VoteType getVoteType() {
		return this.U;
	}

	public void setVoteType(VoteType voteType) {
		this.U = voteType;
	}

	public Boolean getNeedAllSign() {
		return this.V;
	}

	public void setNeedAllSign(Boolean needAllSign) {
		this.V = needAllSign;
	}

	public int getVoteAmount() {
		return this.W;
	}

	public void setVoteAmount(int voteAmount) {
		this.W = voteAmount;
	}

	public String getOpposedAction() {
		return this.X;
	}

	public void setOpposedAction(String opposedAction) {
		this.X = opposedAction;
	}
}