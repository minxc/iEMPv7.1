package org.minxc.emp.bpm.engine.listener;

import com.minxc.emp.core.util.BeanUtils;
import com.dstz.org.api.model.IUser;
import org.minxc.emp.basis.impl.util.ContextUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.constant.InstanceStatus;
import org.minxc.emp.bpm.api.constant.OpinionStatus;
import org.minxc.emp.bpm.api.constant.ScriptType;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;
import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.core.manager.BpmInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmTaskManager;
import org.minxc.emp.bpm.core.manager.BpmTaskOpinionManager;
import org.minxc.emp.bpm.core.manager.BpmTaskStackManager;
import org.minxc.emp.bpm.core.manager.TaskIdentityLinkManager;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.core.model.BpmTaskOpinion;
import org.minxc.emp.bpm.core.model.BpmTaskStack;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.bpm.engine.listener.AbstractTaskListener;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TaskCompleteListener extends AbstractTaskListener<DefualtTaskActionCmd> {
	private static final long serialVersionUID = 6844821899585103714L;
	@Resource
	BpmInstanceManager f;
	@Resource
	BpmTaskManager aQ;
	@Resource
	TaskIdentityLinkManager i;
	@Resource
	BpmTaskOpinionManager aO;
	@Resource
	BpmTaskStackManager aA;

	public EventType getBeforeTriggerEventType() {
		return EventType.TASK_COMPLETE_EVENT;
	}

	public EventType getAfterTriggerEventType() {
		return EventType.TASK_POST_COMPLETE_EVENT;
	}

	public void g(DefualtTaskActionCmd taskActionModel) {
		this.LOG.debug("任务【{}】执行完成事件 - TaskID: {}", (Object) taskActionModel.getBpmTask().getName(),
				(Object) taskActionModel.getBpmTask().getId());
		Map<String, Object> actionVariables = taskActionModel.getActionVariables();
		if (BeanUtils.isEmpty((Object) actionVariables)) {
			return;
		}
		for (String key : actionVariables.keySet()) {
			taskActionModel.addVariable(key, actionVariables.get(key));
		}
		this.LOG.debug("设置流程变量【{}】", (Object) actionVariables.keySet().toString());
	}

	public void h(DefualtTaskActionCmd taskActionModel) {
		DefualtTaskActionCmd complateModel = taskActionModel;
		this.LOG.trace("执行任务完成动作=====》更新任务意见状态");
		this.j(complateModel);
		this.LOG.trace("执行任务完成动作=====》更新任务堆栈记录");
		this.k(complateModel);
		this.LOG.trace("执行任务完成动作=====》删除任务相关信息 - 任务、任务后续人");
		this.l(complateModel);
	}

	public void i(DefualtTaskActionCmd taskActionModel) {
	}

	protected ScriptType getScriptType() {
		return ScriptType.COMPLETE;
	}

	public DefualtTaskActionCmd b(TaskEntity taskEntity) {
		DefualtTaskActionCmd model = (DefualtTaskActionCmd) BpmContext.getActionModel();
		model.setDelagateTask((DelegateTask) taskEntity);
		return model;
	}

	private void j(DefualtTaskActionCmd taskActionModel) {
		BpmTaskOpinion bpmTaskOpinion;
		InstanceStatus flowStatus = InstanceStatus.getByActionName((String) taskActionModel.getActionName());
		BpmInstance instance = (BpmInstance) taskActionModel.getBpmInstance();
		if (!flowStatus.getKey().equals(instance.getStatus())) {
			instance.setStatus(flowStatus.getKey());
			this.f.update(instance);
		}
		if ((bpmTaskOpinion = this.aO.getByTaskId(taskActionModel.getTaskId())) == null) {
			return;
		}
		OpinionStatus opnionStatus = this.d(taskActionModel.getActionName());
		bpmTaskOpinion.setStatus(opnionStatus.getKey());
		bpmTaskOpinion.setApproveTime(new Date());
		bpmTaskOpinion.setDurMs(
				Long.valueOf(bpmTaskOpinion.getApproveTime().getTime() - bpmTaskOpinion.getCreateTime().getTime()));
		bpmTaskOpinion.setOpinion(taskActionModel.getOpinion());
		IUser user = ContextUtil.getCurrentUser();
		if (user != null) {
			bpmTaskOpinion.setApprover(user.getUserId());
			bpmTaskOpinion.setApproverName(user.getFullname());
		}
		this.aO.update(bpmTaskOpinion);
	}

	private OpinionStatus d(String actionName) {
		ActionType action = ActionType.fromKey((String) actionName);
		switch (action) {
			case AGREE : {
				return OpinionStatus.AGREE;
			}
			case OPPOSE : {
				return OpinionStatus.OPPOSE;
			}
			case REJECT : {
				return OpinionStatus.REJECT;
			}
			case REJECT2START : {
				return OpinionStatus.REJECT_TO_START;
			}
			case RECOVER : {
				return OpinionStatus.REVOKER;
			}
			case START : {
				return OpinionStatus.START;
			}
			case MANUALEND : {
				return OpinionStatus.MANUAL_END;
			}
		}
		return OpinionStatus.AWAITING_CHECK;
	}

	private void k(DefualtTaskActionCmd taskActionModel) {
		BpmTaskStack bpmTaskStack = this.aA.getByTaskId(taskActionModel.getTaskId());
		bpmTaskStack.setEndTime(new Date());
		this.aA.update(bpmTaskStack);
		taskActionModel.setTaskStack(bpmTaskStack);
	}

	private void l(DefualtTaskActionCmd taskActionModel) {
		this.i.removeByTaskId(taskActionModel.getTaskId());
		this.aQ.remove(taskActionModel.getTaskId());
	}

	public DefualtTaskActionCmd a(TaskEntity taskEntity) {
		return this.b(taskEntity);
	}

//	public void c(TaskActionCmd taskActionCmd) {
//		this.i((DefualtTaskActionCmd) taskActionCmd);
//	}

//	public void b(TaskActionCmd taskActionCmd) {
//		this.h((DefualtTaskActionCmd) taskActionCmd);
//	}

//	public void a(TaskActionCmd taskActionCmd) {
//		this.g((DefualtTaskActionCmd) taskActionCmd);
//	}

	@Override
	public void a(DefualtTaskActionCmd taskActionCmd) {
		this.g( taskActionCmd);
	}

	@Override
	public void b(DefualtTaskActionCmd taskActionCmd) {
		this.h( taskActionCmd);
	}

	@Override
	public void c(DefualtTaskActionCmd taskActionCmd) {
		this.i(taskActionCmd);
	}

}