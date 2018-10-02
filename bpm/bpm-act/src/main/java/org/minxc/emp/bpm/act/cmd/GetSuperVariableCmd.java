package org.minxc.emp.bpm.act.cmd;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

public class GetSuperVariableCmd implements Command<Object> {
	private String d = "";
	private String e = "";

	public void setBpmnInstId(String bpmnInstId) {
		this.d = bpmnInstId;
	}

	public void setVarName(String varName) {
		this.e = varName;
	}

	public GetSuperVariableCmd() {
	}

	public GetSuperVariableCmd(String bpmnInstId, String varName) {
		this.d = bpmnInstId;
		this.e = varName;
	}

	public Object execute(CommandContext context) {
		ExecutionEntity execution = context.getExecutionEntityManager().findExecutionById(this.d);
		if (execution.getSuperExecution() == null) {
			return null;
		}
		ExecutionEntity superExecution = execution.getSuperExecution();
		return superExecution.getVariable(this.e);
	}
}