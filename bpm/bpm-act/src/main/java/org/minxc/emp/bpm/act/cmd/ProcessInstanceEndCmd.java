package org.minxc.emp.bpm.act.cmd;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

public class ProcessInstanceEndCmd implements Command<Void> {
	private String processInstanceId = null;

	public ProcessInstanceEndCmd(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Void execute(CommandContext cmdContext) {
		ExecutionEntity executionEntity = cmdContext.getExecutionEntityManager()
				.findExecutionById(this.processInstanceId);
		ExecutionEntity parentEnt = this.a(executionEntity);
		parentEnt.end();
		return null;
	}

	private ExecutionEntity a(ExecutionEntity executionEntity) {
		ExecutionEntity parentEnt = executionEntity.getParent();
		if (parentEnt == null) {
			return executionEntity;
		}
		return this.a(parentEnt);
	}
}