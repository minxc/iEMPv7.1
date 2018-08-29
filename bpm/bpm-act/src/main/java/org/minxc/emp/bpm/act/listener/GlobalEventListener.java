package org.minxc.emp.bpm.act.listener;


import java.util.HashMap;
import java.util.Map;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.impl.ActivitiEventImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.minxc.emp.bpm.act.listener.ActEventListener;
import org.springframework.stereotype.Component;

import com.minxc.emp.core.util.AppContextUtil;

@Component
public class GlobalEventListener implements ActivitiEventListener {
	public Log logger = LogFactory.getLog(GlobalEventListener.class);
	private Map<String, String> g = new HashMap<String, String>();

	public void onEvent(ActivitiEvent event) {
		String eventType = event.getType().name();
		String eventHandlerBeanId = this.g.get(eventType);
		if (eventHandlerBeanId != null) {
			ActEventListener handler = (ActEventListener) AppContextUtil.getBean((String) eventHandlerBeanId);
			ActivitiEventImpl e = (ActivitiEventImpl) event;
			handler.notify((ActivitiEvent) e);
		} else {
			this.logger.debug((Object) ("eventListener:" + eventType + " skiped"));
		}
	}

	public boolean isFailOnException() {
		return true;
	}

	public Map<String, String> getHandlers() {
		return this.g;
	}

	public void setHandlers(Map<String, String> handlers) {
		this.g = handlers;
	}
}