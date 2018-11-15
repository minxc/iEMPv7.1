package org.minxc.emp.bpm.core.manager;

import org.minxc.emp.bpm.api.model.task.BpmTask;
import org.minxc.emp.bpm.core.model.BpmnTaskStack;
import org.minxc.emp.common.manager.Manager;


public interface BpmnTaskStackManager extends Manager<String, BpmnTaskStack> {
	public BpmnTaskStack getByTaskId(String var1);

	public BpmnTaskStack createStackByTask(BpmTask var1, BpmnTaskStack var2);

	public void removeByInstanceId(String var1);
}