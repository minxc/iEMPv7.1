package org.minxc.emp.bpm.act.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;

public interface ActEventListener {
	public void notify(ActivitiEvent var1);
}