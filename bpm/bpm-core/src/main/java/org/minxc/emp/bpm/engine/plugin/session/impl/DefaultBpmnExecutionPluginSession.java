package org.minxc.emp.bpm.engine.plugin.session.impl;

import java.util.HashMap;
import java.util.Map;
import org.activiti.engine.delegate.VariableScope;
import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.bpm.engine.plugin.session.BpmnExecutionPluginSession;
import org.minxc.emp.system.util.ContextUtil;

public class DefaultBpmnExecutionPluginSession extends HashMap<String, Object> implements BpmnExecutionPluginSession {
	private static final long serialVersionUID = 4225343560381914372L;
	private Map<String, BusinessData> bM;
	private EventType bN;
	private VariableScope variableScope;
	private BpmnInstance bpmInstance;

	public DefaultBpmnExecutionPluginSession() {
		BaseActionCmd cmd = (BaseActionCmd) BpmnContext.submitActionModel();
		ActionType actionType = cmd.getActionType();
		this.put("submitActionDesc", actionType.getName());
		this.put("submitActionName", actionType.getKey());
		if (cmd instanceof DefualtTaskActionCmd) {
			DefualtTaskActionCmd taskCmd = (DefualtTaskActionCmd) cmd;
			this.put("isTask", true);
			this.put("submitOpinion", taskCmd.getOpinion());
			this.put("currentUser", ContextUtil.getCurrentUser());
			this.put("submitTaskname", taskCmd.getBpmTask().getName());
		}
	}

	public Map<String, BusinessData> getBoDatas() {
		return this.bM;
	}

	public void setBoDatas(Map<String, BusinessData> boDatas) {
		this.putAll(boDatas);
		this.bM = boDatas;
	}

	public BpmnInstance getBpmInstance() {
		return this.bpmInstance;
	}

	public void setBpmInstance(BpmnInstance bpmInstance) {
		this.put("bpmInstance", bpmInstance);
		this.bpmInstance = bpmInstance;
	}

	public EventType getEventType() {
		return this.bN;
	}

	public void setEventType(EventType eventType) {
		this.put("eventType", eventType.getKey());
		this.bN = eventType;
	}

	public VariableScope getVariableScope() {
		return this.variableScope;
	}

	public void setVariableScope(VariableScope variableScope) {
		this.put("variableScope", variableScope);
		this.variableScope = variableScope;
	}
}