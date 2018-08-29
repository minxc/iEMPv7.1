package org.minxc.emp.bpm.plugin.task.reminders.context;

import com.alibaba.fastjson.JSON;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.context.AbstractBpmTaskPluginContext;

import java.util.ArrayList;
import java.util.List;

import org.minxc.emp.bpm.plugin.task.reminders.def.RemindersPluginDef;
import org.minxc.emp.bpm.plugin.task.reminders.plugin.RemindersPlugin;

public class RemindersPluginContext extends AbstractBpmTaskPluginContext<RemindersPluginDef> {
	private static final long serialVersionUID = -8171025388788811778L;

	public List<EventType> getEventTypes() {
		ArrayList<EventType> list = new ArrayList<EventType>();
		list.add(EventType.TASK_CREATE_EVENT);
		list.add(EventType.TASK_COMPLETE_EVENT);
		return list;
	}

	public Class<? extends RunTimePlugin> getPluginClass() {
		return RemindersPlugin.class;
	}

	protected RemindersPluginDef parseFromJson(JSON pluginJson) {
		RemindersPluginDef def = (RemindersPluginDef) JSON.toJavaObject((JSON) pluginJson, RemindersPluginDef.class);
		return def;
	}

	public String getTitle() {
		return "任务催办";
	}

//	protected BpmPluginDef parseFromJson(JSON jSON) {
//		return this.d(jSON);
//	}
}