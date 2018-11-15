package org.minxc.emp.bpm.engine.plugin.session;

import org.minxc.emp.bpm.api.model.task.BpmTask;

public interface BpmnTaskPluginSession extends BpmnPluginSession {
	public BpmTask getBpmTask();
}