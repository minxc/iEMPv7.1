package org.minxc.emp.bpm.act.cmd;

import java.io.Serializable;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

public class GetExecutionVariableCmd implements Serializable, Command<Object> {
	private static final long serialVersionUID = 1L;
	protected String executionId;
	protected String variableName;
	protected boolean isLocal;

	public GetExecutionVariableCmd(String executionId, String variableName, boolean isLocal) {
		this.executionId = executionId;
		this.variableName = variableName;
		this.isLocal = isLocal;
	}

	public Object execute(CommandContext commandContext) {
		if (this.executionId == null) {
			throw new ActivitiIllegalArgumentException("executionId is null");
		}
		if (this.variableName == null) {
			throw new ActivitiIllegalArgumentException("variableName is null");
		}
		ExecutionEntity execution = commandContext.getExecutionEntityManager().findExecutionById(this.executionId);
		if (execution == null) {
			return null;
		}
		Object value = this.isLocal
				? execution.getVariableLocal(this.variableName)
				: execution.getVariable(this.variableName);
		return value;
	}
}