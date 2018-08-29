package org.minxc.emp.bpm.engine.action.handler.task;

import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.bpm.engine.action.handler.task.AbstractTaskActionHandler;
import org.springframework.stereotype.Component;

@Component
public class TaskAgreeActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd> {
	public ActionType getActionType() {
		return ActionType.AGREE;
	}

	protected void d(DefualtTaskActionCmd actionModel) {
	}

	protected void e(DefualtTaskActionCmd actionModel) {
		this.c(actionModel);
	}

	public int getSn() {
		return 1;
	}

	public String getConfigPage() {
		return "/bpm/task/taskOpinionDialog.html";
	}

	@Override
	protected void i(DefualtTaskActionCmd baseActionCmd) {
		this.d((DefualtTaskActionCmd) baseActionCmd);
	}
	@Override
	protected void h(DefualtTaskActionCmd baseActionCmd) {
		this.e((DefualtTaskActionCmd) baseActionCmd);
	}

}