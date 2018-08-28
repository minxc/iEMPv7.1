package org.minxc.emp.bpm.engine.plugin.session.impl;

import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.impl.DefaultBpmExecutionPluginSession;

public class DefaultBpmUserCalcPluginSession extends DefaultBpmExecutionPluginSession
		implements
			BpmUserCalcPluginSession {
	private static final long serialVersionUID = 1132300282829841447L;
	private IBpmTask ao;
	private Boolean bO = false;

	public IBpmTask getBpmTask() {
		return this.ao;
	}

	public void setBpmTask(IBpmTask bpmTask) {
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