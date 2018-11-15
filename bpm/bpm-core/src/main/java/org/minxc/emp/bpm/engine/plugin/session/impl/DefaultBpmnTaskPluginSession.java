package org.minxc.emp.bpm.engine.plugin.session.impl;

import org.minxc.emp.bpm.api.model.task.BpmTask;
import org.minxc.emp.bpm.engine.plugin.session.BpmnTaskPluginSession;

public class DefaultBpmnTaskPluginSession extends DefaultBpmnExecutionPluginSession implements BpmnTaskPluginSession {
	private static final long serialVersionUID = 1L;
	private BpmTask ao;

	public BpmTask getBpmTask() {
		return this.ao;
	}

	public void setBpmTask(BpmTask bpmTask) {
		this.put("bpmTask", bpmTask);
		this.ao = bpmTask;
	}
}