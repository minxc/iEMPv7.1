package org.minxc.emp.bpm.engine.plugin.factory;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.plugin.context.BpmPluginContext;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmExecutionPlugin;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmTaskPlugin;

import com.minxc.emp.core.util.AppContextUtil;

public class BpmPluginFactory {
	public static BpmExecutionPlugin buildExecutionPlugin(BpmPluginContext bpmPluginContext, EventType eventType) {
		if (bpmPluginContext.getEventTypes().contains((Object) eventType)) {
			try {
				BpmExecutionPlugin bpmExecutionPlugin = (BpmExecutionPlugin) AppContextUtil
						.getBean((Class) bpmPluginContext.getPluginClass());
				return bpmExecutionPlugin;
			} catch (Exception bpmExecutionPlugin) {
				// empty catch block
			}
		}
		return null;
	}

	public static BpmTaskPlugin buildTaskPlugin(BpmPluginContext bpmPluginContext, EventType eventType) {
		if (bpmPluginContext.getEventTypes().contains((Object) eventType)) {
			return (BpmTaskPlugin) AppContextUtil.getBean((Class) bpmPluginContext.getPluginClass());
		}
		return null;
	}
}