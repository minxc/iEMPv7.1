package org.minxc.emp.bpm.engine.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.minxc.emp.basis.api.model.SysIdentity;
import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.constant.ScriptType;
import org.minxc.emp.bpm.api.constant.TaskStatus;
import org.minxc.emp.bpm.api.constant.TaskType;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.exception.WorkFlowException;
import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.api.model.def.NodeProperties;
import org.minxc.emp.bpm.api.model.form.BpmForm;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.core.manager.BpmTaskManager;
import org.minxc.emp.bpm.core.manager.BpmTaskOpinionManager;
import org.minxc.emp.bpm.core.manager.BpmTaskStackManager;
import org.minxc.emp.bpm.core.manager.TaskIdentityLinkManager;
import org.minxc.emp.bpm.core.model.BpmTask;
import org.minxc.emp.bpm.core.model.BpmTaskStack;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.bpm.engine.listener.AbstractTaskListener;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TaskCreateListener extends AbstractTaskListener<DefualtTaskActionCmd> {
	
	private static final long serialVersionUID = -7836822392037648008L;
	@Resource
	private BpmTaskManager aQ;
	@Resource
	private BpmProcessDefService a;
	@Resource
	private BpmTaskOpinionManager aO;
	@Resource
	private BpmTaskStackManager aR;
	@Resource
	private TaskIdentityLinkManager i;

	public EventType getBeforeTriggerEventType() {
		return EventType.TASK_CREATE_EVENT;
	}

	public EventType getAfterTriggerEventType() {
		return EventType.TASK_POST_CREATE_EVENT;
	}

	public void g(DefualtTaskActionCmd taskActionModel) {
		this.LOG.debug("任务【{}】执行创建过程 - taskID: {}", (Object) taskActionModel.getBpmTask().getName(),
				(Object) taskActionModel.getBpmTask().getId());
	}

	public void h(DefualtTaskActionCmd taskActionModel) {
		IBpmTask task = taskActionModel.getBpmTask();
		this.d((TaskActionCmd) taskActionModel);
		this.aQ.create((BpmTask) task);
		this.aO.createOpinionByTask((TaskActionCmd) taskActionModel);
		BpmTaskStack stack = this.aR.createStackByTask(task, taskActionModel.getParentTaskStack());
		taskActionModel.setTaskStack(stack);
	}

	public void i(DefualtTaskActionCmd taskActionModel) {
	}

	protected ScriptType getScriptType() {
		return ScriptType.CREATE;
	}

	private void d(TaskActionCmd taskActionModel) {
		IBpmTask bpmTask = taskActionModel.getBpmTask();
		List<SysIdentity> identityList = taskActionModel.getBpmIdentity(bpmTask.getNodeId());
		BpmNodeDef nodeDef = this.a.getBpmNodeDef(bpmTask.getDefId(), bpmTask.getNodeId());
		if (!nodeDef.getNodeProperties().isAllowExecutorEmpty() && BeanUtils.isEmpty((Object) identityList)) {
			throw new WorkFlowException(bpmTask.getNodeId() + "任务候选人为空", BpmStatusCode.NO_ASSIGN_USER);
		}
		if (BeanUtils.isEmpty((Object) identityList)) {
			return;
		}
		SysIdentity firstIdentity = (SysIdentity) identityList.get(0);
		if (identityList.size() == 1 && firstIdentity.getType().equals("user")) {
			bpmTask.setAssigneeId(firstIdentity.getId());
			bpmTask.setAssigneeNames(firstIdentity.getName());
		} else {
			bpmTask.setAssigneeId("0");
			ArrayList<String> names = new ArrayList<String>();
			for (SysIdentity identity : identityList) {
				names.add(identity.getName());
			}
			bpmTask.setAssigneeNames(StringUtil.convertCollectionAsString(names));
		}
		this.i.createIdentityLink(bpmTask, identityList);
	}

	public DefualtTaskActionCmd b(TaskEntity taskEntity) {
		BaseActionCmd model = (BaseActionCmd) BpmContext.getActionModel();
		if (!taskEntity.getProcessInstanceId().equals(model.getBpmInstance().getActInstId())) {
			throw new BusinessException("数据异常，actioncdm 与 TaskEntity数据不一致，请检查！");
		}
		BpmTask task = this.a(taskEntity, model.getBpmInstance());
		DefualtTaskActionCmd createModel = new DefualtTaskActionCmd();
		createModel.setBpmInstance(model.getBpmInstance());
		createModel.setBpmDefinition(model.getBpmDefinition());
		createModel.setBizDataMap(model.getBizDataMap());
		createModel.setBpmIdentities(model.getBpmIdentities());
		createModel.setBusinessKey(model.getBusinessKey());
		createModel.setActionName(ActionType.CREATE.getKey());
		createModel.setBpmTask((IBpmTask) task);
		createModel.setDelagateTask((DelegateTask) taskEntity);
		if (model instanceof DefualtTaskActionCmd) {
			createModel.setParentTaskStack(((DefualtTaskActionCmd) model).getTaskStack());
		}
		BpmContext.setActionModel((ActionCmd) createModel);
		return createModel;
	}

	private BpmTask a(TaskEntity taskEntity, IBpmInstance iBpmInstance) {
		BpmNodeDef nodeDef = this.a.getBpmNodeDef(iBpmInstance.getDefId(), taskEntity.getTaskDefinitionKey());
		int isSupportMobile = 1;
		if (nodeDef.getMobileForm() != null && nodeDef.getMobileForm().isFormEmpty()) {
			isSupportMobile = 0;
		}
		BpmTask task = new BpmTask();
		task.setActExecutionId(taskEntity.getExecutionId());
		task.setActInstId(taskEntity.getProcessInstanceId());
		task.setDefId(iBpmInstance.getDefId());
		task.setId(taskEntity.getId());
		task.setInstId(iBpmInstance.getId());
		task.setName(taskEntity.getName());
		task.setNodeId(taskEntity.getTaskDefinitionKey());
		task.setParentId("0");
		task.setPriority(Integer.valueOf(taskEntity.getPriority()));
		task.setStatus(TaskType.NORMAL.getKey());
		task.setTaskType(this.a(nodeDef.getType()));
		task.setSubject(iBpmInstance.getSubject());
		task.setSupportMobile(Integer.valueOf(isSupportMobile));
		task.setStatus(TaskStatus.NORMAL.getKey());
		task.setTaskId(taskEntity.getId());
		task.setTypeId(iBpmInstance.getTypeId());
		return task;
	}

	private String a(NodeType type) {
		switch (type) {
			case SIGNTASK : {
				return TaskType.SIGN.getKey();
			}
			case CALLACTIVITY : {
				return TaskType.SUBFLOW.getKey();
			}
			case USERTASK : {
				return TaskType.NORMAL.getKey();
			}
		}
		return TaskType.NORMAL.getKey();
	}
	@Override
	public DefualtTaskActionCmd a(TaskEntity taskEntity) {
		return this.b(taskEntity);
	}
//
//	public void c(TaskActionCmd taskActionCmd) {
//		this.i((DefualtTaskActionCmd) taskActionCmd);
//	}
//
//	public void b(TaskActionCmd taskActionCmd) {
//		this.h((DefualtTaskActionCmd) taskActionCmd);
//	}


	@Override
	public void a(DefualtTaskActionCmd taskActionCmd) {
		this.g(taskActionCmd);
	}

	@Override
	public void b(DefualtTaskActionCmd taskActionCmd) {
		this.h(taskActionCmd);
	}

	@Override
	public void c(DefualtTaskActionCmd taskActionCmd) {
		this.i(taskActionCmd);
	}


}