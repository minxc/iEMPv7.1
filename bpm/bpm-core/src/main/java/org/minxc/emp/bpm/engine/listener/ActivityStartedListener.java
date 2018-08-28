package org.minxc.emp.bpm.engine.listener;

import javax.annotation.Resource;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
import org.minxc.emp.bpm.act.listener.ActEventListener;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.core.manager.BpmTaskStackManager;
import org.minxc.emp.bpm.core.model.BpmTask;
import org.minxc.emp.bpm.core.model.BpmTaskStack;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.springframework.stereotype.Component;

@Component
public class ActivityStartedListener implements ActEventListener {
	@Resource
	BpmTaskStackManager aN;

	public void notify(ActivitiEvent event) {
		if (!(event instanceof ActivitiActivityEventImpl)) {
			return;
		}
		ActivitiActivityEventImpl activitEvent = (ActivitiActivityEventImpl) event;
		if (activitEvent.getActivityType().equals("callActivity")) {
			this.b(activitEvent);
		}
	}

	private void b(ActivitiActivityEventImpl activitEvent) {
		DefualtTaskActionCmd action = (DefualtTaskActionCmd) BpmContext.getActionModel();
		BpmTask task = new BpmTask();
		task.setId(activitEvent.getExecutionId());
		task.setName(activitEvent.getActivityName());
		task.setNodeId(activitEvent.getActivityId());
		task.setInstId(action.getInstanceId());
		this.aN.createStackByTask((IBpmTask) task, action.getTaskStack());
	}
}