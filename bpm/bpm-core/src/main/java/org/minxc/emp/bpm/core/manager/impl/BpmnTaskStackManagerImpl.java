package org.minxc.emp.bpm.core.manager.impl;


import java.util.Date;
import javax.annotation.Resource;

import org.minxc.emp.bpm.api.model.task.BpmTask;
import org.minxc.emp.bpm.core.dao.BpmnTaskStackDao;
import org.minxc.emp.bpm.core.manager.BpmnTaskStackManager;
import org.minxc.emp.bpm.core.model.BpmnTaskStack;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

@Service(value = "bpmExecutionStackManager")
public class BpmnTaskStackManagerImpl extends CommonManager<String, BpmnTaskStack> implements BpmnTaskStackManager {
	@Resource
    BpmnTaskStackDao l;

	public BpmnTaskStack getByTaskId(String taskId) {
		return this.l.getByTaskId(taskId);
	}

	public BpmnTaskStack createStackByTask(BpmTask task, BpmnTaskStack parentStack) {
		BpmnTaskStack stack = new BpmnTaskStack();
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