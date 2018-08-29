package org.minxc.emp.bpm.core.manager;

import org.minxc.emp.core.api.query.QueryFilter;
import com.dstz.base.manager.Manager;

import java.util.List;

import org.minxc.emp.bpm.core.model.BpmTask;

public interface BpmTaskManager extends Manager<String, BpmTask> {
	public List<BpmTask> getByInstIdNodeId(String var1, String var2);

	public List<BpmTask> getByInstId(String instanceId);

	public List<BpmTask> getTodoList(String var1, QueryFilter queryFilter);

	public void assigneeTask(String var1, String var2, String var3);

	public void unLockTask(String var1);
}