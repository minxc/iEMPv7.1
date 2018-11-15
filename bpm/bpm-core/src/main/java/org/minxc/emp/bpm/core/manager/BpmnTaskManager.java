package org.minxc.emp.bpm.core.manager;


import java.util.List;

import org.minxc.emp.bpm.core.model.BpmnTaskImpl;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.QueryFilter;

public interface BpmnTaskManager extends Manager<String, BpmnTaskImpl> {
	public List<BpmnTaskImpl> getByInstIdNodeId(String var1, String var2);

	public List<BpmnTaskImpl> getByInstId(String instanceId);

	public List<BpmnTaskImpl> getTodoList(String var1, QueryFilter queryFilter);

	public void assigneeTask(String var1, String var2, String var3);

	public void unLockTask(String var1);
}