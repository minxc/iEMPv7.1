package org.minxc.emp.bpm.engine.listener;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.constant.ScriptType;
import org.minxc.emp.bpm.api.constant.TaskStatus;
import org.minxc.emp.bpm.api.constant.TaskType;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.exception.WorkFlowException;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.task.BpmTask;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.core.manager.BpmnTaskManager;
import org.minxc.emp.bpm.core.manager.BpmnTaskOpinionManager;
import org.minxc.emp.bpm.core.manager.BpmnTaskStackManager;
import org.minxc.emp.bpm.core.manager.TaskIdentityLinkManager;
import org.minxc.emp.bpm.core.model.BpmnTaskImpl;
import org.minxc.emp.bpm.core.model.BpmnTaskStack;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class TaskCreateListener extends AbstractTaskListener<DefualtTaskActionCmd> {
	
	private static final long serialVersionUID = -7836822392037648008L;
	@Resource
	private BpmnTaskManager aQ;
	@Resource
	private BpmnProcessDefinitionService a;
	@Resource
	private BpmnTaskOpinionManager aO;
	@Resource
	private BpmnTaskStackManager aR;
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
		BpmTask task = taskActionModel.getBpmTask();
		this.d((TaskActionCmd) taskActionModel);
		this.aQ.create((BpmnTaskImpl) task);
		this.aO.createOpinionByTask((TaskActionCmd) taskActionModel);
		BpmnTaskStack stack = this.aR.createStackByTask(task, taskActionModel.getParentTaskStack());
		taskActionModel.setTaskStack(stack);
	}

	public void i(DefualtTaskActionCmd taskActionModel) {
	}

	protected ScriptType getScriptType() {
		return ScriptType.CREATE;
	}

	private void d(TaskActionCmd taskActionModel) {
		BpmTask bpmTask = taskActionModel.getBpmTask();
		List<SystemIdentity> identityList = taskActionModel.getBpmIdentity(bpmTask.getNodeId());
		BpmNodeDef nodeDef = this.a.getBpmNodeDef(bpmTask.getDefId(), bpmTask.getNodeId());
		if (!nodeDef.getNodeProperties().isAllowExecutorEmpty() && BeanUtils.isEmpty((Object) identityList)) {
			throw new WorkFlowException(bpmTask.getNodeId() + "任务候选人为空", BpmnStatusCode.NO_ASSIGN_USER);
		}
		if (BeanUtils.isEmpty((Object) identityList)) {
			return;
		}
		SystemIdentity firstIdentity = (SystemIdentity) identityList.get(0);
		if (identityList.size() == 1 && firstIdentity.getType().equals("user")) {
			bpmTask.setAssigneeId(firstIdentity.getId());
			bpmTask.setAssigneeNames(firstIdentity.getName());
		} else {
			bpmTask.setAssigneeId("0");
			ArrayList<String> names = new ArrayList<String>();
			for (SystemIdentity identity : identityList) {
				names.add(identity.getName());
			}
			bpmTask.setAssigneeNames(StringUtil.convertCollectionAsString(names));
		}
		this.i.createIdentityLink(bpmTask, identityList);
	}

	public DefualtTaskActionCmd b(TaskEntity taskEntity) {
		BaseActionCmd model = (BaseActionCmd) BpmnContext.getActionModel();
		if (!taskEntity.getProcessInstanceId().equals(model.getBpmInstance().getActInstId())) {
			throw new BusinessException("数据异常，actioncdm 与 TaskEntity数据不一致，请检查！");
		}
		BpmnTaskImpl task = this.a(taskEntity, model.getBpmInstance());
		DefualtTaskActionCmd createModel = new DefualtTaskActionCmd();
		createModel.setBpmInstance(model.getBpmInstance());
		createModel.setBpmDefinition(model.getBpmDefinition());
		createModel.setBizDataMap(model.getBizDataMap());
		createModel.setBpmIdentities(model.getBpmIdentities());
		createModel.setBusinessKey(model.getBusinessKey());
		createModel.setActionName(ActionType.CREATE.getKey());
		createModel.setBpmTask((BpmTask) task);
		createModel.setDelagateTask((DelegateTask) taskEntity);
		if (model instanceof DefualtTaskActionCmd) {
			createModel.setParentTaskStack(((DefualtTaskActionCmd) model).getTaskStack());
		}
		BpmnContext.setActionModel((ActionCmd) createModel);
		return createModel;
	}

	private BpmnTaskImpl a(TaskEntity taskEntity, BpmnInstance bpmnInstance) {
		BpmNodeDef nodeDef = this.a.getBpmNodeDef(bpmnInstance.getDefId(), taskEntity.getTaskDefinitionKey());
		int isSupportMobile = 1;
		if (nodeDef.getMobileForm() != null && nodeDef.getMobileForm().isFormEmpty()) {
			isSupportMobile = 0;
		}
		BpmnTaskImpl task = new BpmnTaskImpl();
		task.setActExecutionId(taskEntity.getExecutionId());
		task.setActInstId(taskEntity.getProcessInstanceId());
		task.setDefId(bpmnInstance.getDefId());
		task.setId(taskEntity.getId());
		task.setInstId(bpmnInstance.getId());
		task.setName(taskEntity.getName());
		task.setNodeId(taskEntity.getTaskDefinitionKey());
		task.setParentId("0");
		task.setPriority(Integer.valueOf(taskEntity.getPriority()));
		task.setStatus(TaskType.NORMAL.getKey());
		task.setTaskType(this.a(nodeDef.getType()));
		task.setSubject(bpmnInstance.getSubject());
		task.setSupportMobile(Integer.valueOf(isSupportMobile));
		task.setStatus(TaskStatus.NORMAL.getKey());
		task.setTaskId(taskEntity.getId());
		task.setTypeId(bpmnInstance.getTypeId());
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