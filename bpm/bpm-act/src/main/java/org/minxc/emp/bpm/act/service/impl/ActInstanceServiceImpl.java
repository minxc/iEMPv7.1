package org.minxc.emp.bpm.act.service.impl;

import java.util.Collection;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.cmd.GetExecutionVariableCmd;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.runtime.ProcessInstance;
import org.minxc.emp.bpm.act.cmd.GetSuperVariableCmd;
import org.minxc.emp.bpm.act.cmd.ProcessInstanceEndCmd;
import org.minxc.emp.bpm.act.service.ActInstanceService;
import org.minxc.emp.bpm.act.util.ActivitiUtil;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ActInstanceServiceImpl implements ActInstanceService {
	private static Logger log = LoggerFactory.getLogger(ActInstanceServiceImpl.class);
	@Resource
	RuntimeService runtimeService;
	@Resource
	ProcessEngine processEngine;
	@Resource
	RepositoryService repositoryService;
	@Resource
	BpmProcessDefService h;

	public String startProcessInstance(String actDefId, String businessKey, Map<String, Object> variables,
			String[] aryDestination) {
		String actInstId;
		String defId = this.h.getDefinitionByActDefId(actDefId).getId();
		BpmNodeDef bpmNodeDef = this.h.getStartEvent(defId);
		String nodeId = bpmNodeDef.getNodeId();
		Map activityMap = ActivitiUtil.a((String) actDefId, (String) nodeId, (String[]) aryDestination);
		actInstId = "";
		try {
			actInstId = this.startProcessInstance(actDefId, businessKey, variables);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			ActivitiUtil.a((Map) activityMap);
		}
		return actInstId;
	}

	public String startProcessInstance(String actDefId, String businessKey, Map<String, Object> variables) {
		try {
			User user = ContextUtil.getCurrentUser();
			Authentication.setAuthenticatedUserId((String) user.getUserId());
			ProcessInstance instance = this.runtimeService.startProcessInstanceById(actDefId, businessKey, variables);
			String string = instance.getId();
			return string;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			Authentication.setAuthenticatedUserId(null);
		}
	}

	public Map<String, Object> getVariables(String processInstanceId) {
		return this.runtimeService.getVariables(processInstanceId);
	}

	public void setVariable(String executionId, String variableName, Object value) {
		this.runtimeService.setVariable(executionId, variableName, value);
	}

	public void setVariableLocal(String executionId, String variableName, Object value) {
		this.runtimeService.setVariableLocal(executionId, variableName, value);
	}

	public void setVariables(String executionId, Map<String, ? extends Object> variables) {
		this.runtimeService.setVariables(executionId, variables);
	}

	public void setVariablesLocal(String executionId, Map<String, ? extends Object> variables) {
		this.runtimeService.setVariablesLocal(executionId, variables);
	}

	public void removeVariable(String executionId, String variableName) {
		this.runtimeService.removeVariable(executionId, variableName);
	}

	public void removeVariableLocal(String executionId, String variableName) {
		this.runtimeService.removeVariableLocal(executionId, variableName);
	}

	public void removeVariables(String executionId, Collection<String> variableNames) {
		this.runtimeService.removeVariables(executionId, variableNames);
	}

	public void removeVariablesLocal(String executionId, Collection<String> variableNames) {
		this.runtimeService.removeVariablesLocal(executionId, variableNames);
	}

	public boolean hasVariableLocal(String executionId, String variableName) {
		return this.runtimeService.hasVariableLocal(executionId, variableName);
	}

	public Object getVariableLocal(String executionId, String variableName) {
		CommandExecutor executor = ActivitiUtil.getCommandExecutor();
		GetExecutionVariableCmd cmd = new GetExecutionVariableCmd(executionId, variableName, true);
		return executor.execute(cmd);
	}

	public boolean hasVariable(String executionId, String variableName) {
		return this.runtimeService.hasVariable(executionId, variableName);
	}

	public Object getVariable(String executionId, String variableName) {
		return this.runtimeService.getVariable(executionId, variableName);
	}

	public Map<String, Object> getVariablesLocal(String executionId, Collection<String> variableNames) {
		return this.runtimeService.getVariablesLocal(executionId);
	}

	public Map<String, Object> getVariablesLocal(String executionId) {
		return this.runtimeService.getVariablesLocal(executionId);
	}

	public Map<String, Object> getVariables(String executionId, Collection<String> variableNames) {
		return this.runtimeService.getVariables(executionId, variableNames);
	}

	public void endProcessInstance(String bpmnInstanceId) {
		ProcessEngineImpl engine = (ProcessEngineImpl) this.processEngine;
		CommandExecutor cmdExecutor = engine.getProcessEngineConfiguration().getCommandExecutor();
		cmdExecutor.execute(new ProcessInstanceEndCmd(bpmnInstanceId));
	}

	public void activateProcessInstanceById(String bpmnInstanceId) {
		this.runtimeService.activateProcessInstanceById(bpmnInstanceId);
	}

	public void suspendProcessInstanceById(String bpmnInstanceId) {
		this.runtimeService.suspendProcessInstanceById(bpmnInstanceId);
	}

	public void deleteProcessInstance(String bpmnInstId, String reason) {
		this.runtimeService.deleteProcessInstance(bpmnInstId, reason);
	}

	public Object getSuperVariable(String bpmnId, String varName) {
		CommandExecutor executor = ActivitiUtil.getCommandExecutor();
		GetSuperVariableCmd cmd = new GetSuperVariableCmd(bpmnId, varName);
		return executor.execute(cmd);
	}
}