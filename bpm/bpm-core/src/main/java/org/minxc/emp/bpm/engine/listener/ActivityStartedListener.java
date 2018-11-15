package org.minxc.emp.bpm.engine.listener;

import javax.annotation.Resource;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
import org.minxc.emp.bpm.act.listener.ActEventListener;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.api.model.task.BpmTask;
import org.minxc.emp.bpm.core.manager.BpmnTaskStackManager;
import org.minxc.emp.bpm.core.model.BpmnTaskImpl;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.springframework.stereotype.Component;

@Component
public class ActivityStartedListener implements ActEventListener {
	@Resource
    BpmnTaskStackManager aN;

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
		DefualtTaskActionCmd action = (DefualtTaskActionCmd) BpmnContext.getActionModel();
		BpmnTaskImpl task = new BpmnTaskImpl();
		task.setId(activitEvent.getExecutionId());
		task.setName(activitEvent.getActivityName());
		task.setNodeId(activitEvent.getActivityId());
		task.setInstId(action.getInstanceId());
		this.aN.createStackByTask((BpmTask) task, action.getTaskStack());
	}
}