package org.minxc.emp.bpm.plugin.task.reminders.context;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.context.AbstractBpmnTaskPluginContext;
import org.minxc.emp.bpm.plugin.task.reminders.def.RemindersPluginDefinition;
import org.minxc.emp.bpm.plugin.task.reminders.plugin.RemindersPlugin;

/**
 *
 * 催办处理事项上下文
 *
 */
public class RemindersPluginContext extends AbstractBpmnTaskPluginContext<RemindersPluginDefinition> {
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

	protected RemindersPluginDefinition parseFromJson(JSON pluginJson) {
		RemindersPluginDefinition def = (RemindersPluginDefinition) JSON.toJavaObject((JSON) pluginJson, RemindersPluginDefinition.class);
		return def;
	}

	public String getTitle() {
		return "任务催办";
	}

//	protected BpmnPluginDef parseFromJson(JSON jSON) {
//		return this.d(jSON);
//	}
}