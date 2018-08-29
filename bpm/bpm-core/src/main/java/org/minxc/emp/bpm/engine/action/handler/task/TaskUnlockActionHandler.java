package org.minxc.emp.bpm.engine.action.handler.task;


import javax.annotation.Resource;

import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.constant.TaskStatus;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.core.manager.BpmTaskManager;
import org.minxc.emp.bpm.core.model.BpmTask;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.bpm.engine.action.handler.task.AbstractTaskActionHandler;
import org.minxc.emp.core.api.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class TaskUnlockActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd> {
	@Resource
	BpmTaskManager aC;

	public ActionType getActionType() {
		return ActionType.UNLOCK;
	}

	public void f(DefualtTaskActionCmd model) {
		this.b(model);
		this.k((BaseActionCmd) model);
		BpmTask task = (BpmTask) model.getBpmTask();
		if (!task.getStatus().equals(TaskStatus.LOCK.getKey())) {
			throw new BusinessException("该任务并非锁定状态,或已经被解锁锁定，解锁失败");
		}
		this.aC.unLockTask(task.getId());
	}

	protected void d(DefualtTaskActionCmd actionModel) {
	}

	protected void e(DefualtTaskActionCmd actionModel) {
	}

	public int getSn() {
		return 6;
	}

	public Boolean isDefault() {
		return false;
	}

	public String getDefaultGroovyScript() {
		return "return task.getStatus().equals(TaskStatus.LOCK.getKey();";
	}

	public String getConfigPage() {
		return "";
	}
	
	@Override
	protected void i(DefualtTaskActionCmd baseActionCmd) {
		this.d(baseActionCmd);
	}
	@Override
	protected void h(DefualtTaskActionCmd baseActionCmd) {
		this.e(baseActionCmd);
	}

	public void a(BaseActionCmd baseActionCmd) {
		this.f((DefualtTaskActionCmd) baseActionCmd);
	}

	public void execute(DefualtTaskActionCmd actionCmd) {
		this.f((DefualtTaskActionCmd) actionCmd);
	}

}