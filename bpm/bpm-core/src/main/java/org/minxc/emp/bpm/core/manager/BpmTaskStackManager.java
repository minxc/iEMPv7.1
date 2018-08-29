package org.minxc.emp.bpm.core.manager;

import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.core.model.BpmTaskStack;
import org.minxc.emp.common.manager.Manager;


public interface BpmTaskStackManager extends Manager<String, BpmTaskStack> {
	public BpmTaskStack getByTaskId(String var1);

	public BpmTaskStack createStackByTask(IBpmTask var1, BpmTaskStack var2);

	public void removeByInstanceId(String var1);
}