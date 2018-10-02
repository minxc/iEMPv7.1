package org.minxc.emp.bpm.engine.action.cmd;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import org.activiti.engine.delegate.DelegateTask;
import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.exception.WorkFlowException;
import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.core.model.BpmTaskStack;
import org.minxc.emp.bpm.engine.action.handler.AbsActionHandler;
import org.minxc.emp.bpm.engine.constant.TaskSkipType;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.StringUtil;

public class DefualtTaskActionCmd extends BaseActionCmd implements TaskActionCmd {
	
	
	private String taskId;
	private IBpmTask ao;
	private DelegateTask ap;
	private String ai;
	private BpmTaskStack aq;
	private BpmTaskStack ar;
	private TaskSkipType as = TaskSkipType.NO_SKIP;

	public DefualtTaskActionCmd() {
	}

	public DefualtTaskActionCmd(String flowParam) {
		super(flowParam);
	}

	public String getTaskId() {
		if (this.ao != null) {
			return this.ao.getId();
		}
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void initSpecialParam(JSONObject flowParam) {
		String taskId = flowParam.getString("taskId");
		if (StringUtil.isEmpty((String) taskId)) {
			throw new BusinessException("taskId 不能为空", BpmStatusCode.TASK_NOT_FOUND);
		}
		this.setTaskId(taskId);
		String destination = flowParam.getString("destination");
		this.setDestination(destination);
		String opinion = flowParam.getString("opinion");
		this.setOpinion(opinion);
	}

	public IBpmTask getBpmTask() {
		return this.ao;
	}

	public void setBpmTask(IBpmTask task) {
		this.ao = task;
	}

	public DelegateTask getDelagateTask() {
		if (this.ap == null) {
			// empty if block
		}
		return this.ap;
	}

	public void setDelagateTask(DelegateTask task) {
		this.ap = task;
	}

	public String getNodeId() {
		return this.ao.getNodeId();
	}

	public String getOpinion() {
		return this.ai;
	}

	public void setOpinion(String opinion) {
		this.ai = opinion;
	}

	public BpmTaskStack getTaskStack() {
		return this.aq;
	}

	public void setTaskStack(BpmTaskStack taskStack) {
		this.aq = taskStack;
	}

	public BpmTaskStack getParentTaskStack() {
		return this.ar;
	}

	public void setParentTaskStack(BpmTaskStack parentTaskStack) {
		this.ar = parentTaskStack;
	}

	public void addVariable(String variableName, Object value) {
		if (this.ap == null) {
			throw new WorkFlowException( BpmStatusCode.VARIABLES_NO_SYNC);
		}
		this.ap.setVariable(variableName, value);
	}

	public Object getVariable(String variableName) {
		if (this.ap == null) {
			throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
		}
		return this.ap.getVariable(variableName);
	}

	public boolean hasVariable(String variableName) {
		if (this.ap == null) {
			throw new WorkFlowException( BpmStatusCode.VARIABLES_NO_SYNC);
		}
		return this.ap.hasVariable(variableName);
	}

	public void removeVariable(String variableName) {
		if (this.ap == null) {
			throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
		}
	}

	public Map<String, Object> getVariables() {
		return this.ap.getVariables();
	}

	public synchronized String a() {
		if (this.hasExecuted) {
			throw new BusinessException("action cmd caonot be invoked twice",
					BpmStatusCode.PARAM_ILLEGAL);
		}
		this.hasExecuted = true;
		ActionType actonType = ActionType.fromKey((String) this.getActionName());
		AbsActionHandler handler = (AbsActionHandler) AppContextUtil.getBean((String) actonType.getBeanId());
		if (handler == null) {
			throw new BusinessException("action beanId cannot be found :" + actonType.getName(),
					BpmStatusCode.NO_TASK_ACTION);
		}
		handler.g((BaseActionCmd) this);
		return handler.getActionType().getName();
	}

	public TaskSkipType b() {
		return this.as;
	}

	public void setHasSkipThisTask(TaskSkipType isSkip) {
		this.as = isSkip;
	}
}