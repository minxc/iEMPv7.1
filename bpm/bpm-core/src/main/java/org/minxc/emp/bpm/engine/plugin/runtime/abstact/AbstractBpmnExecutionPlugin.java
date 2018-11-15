package org.minxc.emp.bpm.engine.plugin.runtime.abstact;

import org.activiti.engine.delegate.VariableScope;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnExecutionPluginDef;
import org.minxc.emp.bpm.api.model.task.BpmTask;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmnExecutionPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnExecutionPluginSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBpmnExecutionPlugin<S extends BpmnExecutionPluginSession, M extends BpmnExecutionPluginDef>
		implements
        BpmnExecutionPlugin<S, M> {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	protected boolean isTask() {
		ActionCmd actionCmd = BpmnContext.getActionModel();
		if (actionCmd instanceof TaskActionCmd) {
			return true;
		}
		return false;
	}

	protected BpmTask getTaskIfTask() {
		ActionCmd actionCmd = BpmnContext.getActionModel();
		if (actionCmd instanceof TaskActionCmd) {
			TaskActionCmd taskCmd = (TaskActionCmd) actionCmd;
			return taskCmd.getBpmTask();
		}
		return null;
	}

	protected String getActivitiId(BpmnExecutionPluginSession session) {
		VariableScope scope = session.getVariableScope();
		if (scope instanceof ExecutionEntity) {
			ExecutionEntity execution = (ExecutionEntity) scope;
			return execution.getActivityId();
		}
		if (scope instanceof TaskEntity) {
			TaskEntity task = (TaskEntity) scope;
			return task.getTaskDefinitionKey();
		}
		return null;
	}
}