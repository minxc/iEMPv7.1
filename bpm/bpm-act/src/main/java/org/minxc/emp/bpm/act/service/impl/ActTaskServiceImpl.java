package org.minxc.emp.bpm.act.service.impl;

import java.util.Collection;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfoQuery;
import org.activiti.engine.task.TaskQuery;
import org.minxc.emp.bpm.act.service.ActTaskService;
import org.minxc.emp.bpm.act.util.ActivitiUtil;
import org.springframework.stereotype.Service;

@Service
public class ActTaskServiceImpl implements ActTaskService {
	@Resource
	TaskService taskService;
	@Resource
	RepositoryService repositoryService;

	public DelegateTask getByTaskId(String taskId) {
		TaskEntity task = (TaskEntity) ((TaskQuery) this.taskService.createTaskQuery().taskId(taskId)).singleResult();
		return task;
	}

	public void save(DelegateTask delegateTask) {
		this.taskService.saveTask((Task) ((TaskEntity) delegateTask));
	}

	public Object getVariable(String taskId, String variableName) {
		return this.taskService.getVariable(taskId, variableName);
	}

	public Object getVariableLocal(String taskId, String variableName) {
		return this.taskService.getVariableLocal(taskId, variableName);
	}

	public Map<String, Object> getVariables(String taskId) {
		return this.taskService.getVariables(taskId);
	}

	public Map<String, Object> getVariables(String taskId, Collection<String> variableNames) {
		return this.taskService.getVariables(taskId, variableNames);
	}

	public Map<String, Object> getVariablesLocal(String taskId) {
		return this.taskService.getVariablesLocal(taskId);
	}

	public Map<String, Object> getVariablesLocal(String taskId, Collection<String> variableNames) {
		return this.taskService.getVariablesLocal(taskId, variableNames);
	}

	public void completeTask(String taskId) {
		this.taskService.complete(taskId);
	}

	public void setVariable(String taskId, String variableName, Object value) {
		this.taskService.setVariable(taskId, variableName, value);
	}

	public void setVariableLocal(String taskId, String variableName, Object value) {
		this.taskService.setVariableLocal(taskId, variableName, value);
	}

	public void setVariables(String taskId, Map<String, ? extends Object> variables) {
		this.taskService.setVariables(taskId, variables);
	}

	public void setVariablesLocal(String taskId, Map<String, ? extends Object> variables) {
		this.taskService.setVariablesLocal(taskId, variables);
	}

	public void completeTask(String taskId, String... aryDestination) {
		TaskEntity task = (TaskEntity) ((TaskQuery) this.taskService.createTaskQuery().taskId(taskId)).singleResult();
		String curNodeId = task.getTaskDefinitionKey();
		String actDefId = task.getProcessDefinitionId();
		Map activityMap = ActivitiUtil.a((String) actDefId, (String) curNodeId, (String[]) aryDestination);
		try {
			this.taskService.complete(taskId);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			ActivitiUtil.a((Map) activityMap);
		}
	}

	public void completeTaskOnly(String taskId) {
		this.completeTask(taskId, null);
	}
}