package org.minxc.emp.bpm.engine.plugin.session;

import org.minxc.emp.bpm.api.model.task.BpmTask;

public interface BpmnUserCalcPluginSession extends BpmnPluginSession {
	public Boolean isPreVrewModel();

	public BpmTask getBpmTask();
}