package org.minxc.emp.bpm.core.manager.impl;

import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.manager.impl.CommonManager;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Resource;

import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.core.dao.BpmTaskStackDao;
import org.minxc.emp.bpm.core.manager.BpmTaskStackManager;
import org.minxc.emp.bpm.core.model.BpmTaskStack;
import org.springframework.stereotype.Service;

@Service(value = "bpmExecutionStackManager")
public class BpmTaskStackManagerImpl extends CommonManager<String, BpmTaskStack> implements BpmTaskStackManager {
	@Resource
	BpmTaskStackDao l;

	public BpmTaskStack getByTaskId(String taskId) {
		return this.l.getByTaskId(taskId);
	}

	public BpmTaskStack createStackByTask(IBpmTask task, BpmTaskStack parentStack) {
		BpmTaskStack stack = new BpmTaskStack();
		String id = UniqueIdUtil.getSuid();
		stack.setId(id);
		stack.setNodeId(task.getNodeId());
		stack.setNodeName(task.getName());
		stack.setTaskId(task.getId());
		stack.setStartTime(new Date());
		stack.setInstId(task.getInstId());
		if (parentStack == null) {
			stack.setPath(id + ".");
			stack.setParentId("0");
		} else {
			stack.setPath(parentStack.getPath() + id + ".");
			stack.setParentId(parentStack.getId());
		}
		this.create(stack);
		return stack;
	}

	public void removeByInstanceId(String instId) {
		this.l.removeByInstanceId(instId);
	}
}