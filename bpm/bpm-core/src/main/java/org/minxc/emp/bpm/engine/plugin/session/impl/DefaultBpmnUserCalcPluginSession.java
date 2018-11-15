package org.minxc.emp.bpm.engine.plugin.session.impl;

import org.minxc.emp.bpm.api.model.task.BpmTask;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;

public class DefaultBpmnUserCalcPluginSession extends DefaultBpmnExecutionPluginSession
		implements
		BpmnUserCalcPluginSession {
	private static final long serialVersionUID = 1132300282829841447L;
	private BpmTask ao;
	private Boolean bO = false;

	public BpmTask getBpmTask() {
		return this.ao;
	}

	public void setBpmTask(BpmTask bpmTask) {
		this.put("bpmTask", bpmTask);
		this.ao = bpmTask;
	}

	public Boolean isPreVrewModel() {
		return this.bO;
	}

	public void setIsPreVrewModel(Boolean isPreVrewModel) {
		this.bO = isPreVrewModel;
	}
}