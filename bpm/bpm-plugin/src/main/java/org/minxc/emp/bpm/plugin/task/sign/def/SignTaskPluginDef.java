package org.minxc.emp.bpm.plugin.task.sign.def;

import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmTaskPluginDef;

import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.minxc.emp.bpm.plugin.task.sign.def.VoteType;

public class SignTaskPluginDef extends AbstractBpmTaskPluginDef {
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