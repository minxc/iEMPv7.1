package org.minxc.emp.bpm.engine.plugin.session;

import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.engine.plugin.session.BpmPluginSession;

public interface BpmUserCalcPluginSession extends BpmPluginSession {
	public Boolean isPreVrewModel();

	public IBpmTask getBpmTask();
}