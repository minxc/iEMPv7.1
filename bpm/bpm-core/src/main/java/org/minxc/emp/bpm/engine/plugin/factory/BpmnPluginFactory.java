package org.minxc.emp.bpm.engine.plugin.factory;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmnExecutionPlugin;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmnTaskPlugin;
import org.minxc.emp.core.util.AppContextUtil;

public class BpmnPluginFactory {
	public static BpmnExecutionPlugin buildExecutionPlugin(BpmnPluginContext bpmnPluginContext, EventType eventType) {
		if (bpmnPluginContext.getEventTypes().contains((Object) eventType)) {
			try {
				BpmnExecutionPlugin bpmnExecutionPlugin = (BpmnExecutionPlugin) AppContextUtil
						.getBean((Class) bpmnPluginContext.getPluginClass());
				return bpmnExecutionPlugin;
			} catch (Exception bpmExecutionPlugin) {
				// empty catch block
			}
		}
		return null;
	}

	public static BpmnTaskPlugin buildTaskPlugin(BpmnPluginContext bpmnPluginContext, EventType eventType) {
		if (bpmnPluginContext.getEventTypes().contains((Object) eventType)) {
			return (BpmnTaskPlugin) AppContextUtil.getBean((Class) bpmnPluginContext.getPluginClass());
		}
		return null;
	}
}