package org.minxc.emp.bpm.engine.action.handler.task;


import org.minxc.emp.basis.impl.util.ContextUtil;
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
public class TaskLockActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd> {
	public ActionType getActionType() {
		return ActionType.LOCK;
	}

	public void f(DefualtTaskActionCmd model) {
		this.b(model);
		this.k((BaseActionCmd) model);
		BpmTask task = (BpmTask) model.getBpmTask();
		if (!task.getAssigneeId().equals("0")) {
			throw new BusinessException("该任务不支持锁定,或已经被锁定");
		}
		task.setAssigneeId(ContextUtil.getCurrentUserId());
		task.setAssigneeNames(ContextUtil.getCurrentUser().getFullname());
		task.setStatus(TaskStatus.LOCK.getKey());
		this.ay.update(task);
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
		return "if(task.getAssignee()==\"0\") return true; return false;";
	}

	public String getConfigPage() {
		return "";
	}
	@Override
	protected void i(DefualtTaskActionCmd baseActionCmd) {
		this.d((DefualtTaskActionCmd) baseActionCmd);
	}
	@Override
	protected void h(DefualtTaskActionCmd baseActionCmd) {
		this.e((DefualtTaskActionCmd) baseActionCmd);
	}
	@Override
	public void a(DefualtTaskActionCmd baseActionCmd) {
		this.f((DefualtTaskActionCmd) baseActionCmd);
	}
	@Override
	public void execute(DefualtTaskActionCmd actionCmd) {
		this.f((DefualtTaskActionCmd) actionCmd);
	}

	
}