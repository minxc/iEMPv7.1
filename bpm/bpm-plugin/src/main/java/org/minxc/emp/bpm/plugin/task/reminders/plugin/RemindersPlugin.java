package org.minxc.emp.bpm.plugin.task.reminders.plugin;

import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.GroovyScriptEngine;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmTaskPluginSession;
import org.minxc.emp.bpm.plugin.task.reminders.def.Reminder;
import org.minxc.emp.bpm.plugin.task.reminders.def.RemindersPluginDef;
import org.minxc.emp.core.util.StringUtil;

public class RemindersPlugin extends AbstractBpmTaskPlugin<BpmTaskPluginSession, RemindersPluginDef> {
	@Resource
	GroovyScriptEngine groovyScriptEngine;

	public Void execute(BpmTaskPluginSession pluginSession, RemindersPluginDef pluginDef) {
		if (pluginSession.getEventType() == EventType.TASK_COMPLETE_EVENT) {
			return null;
		}
		RemindersPluginDef reminderDef = pluginDef;
		List<Reminder> reminderList = reminderDef.getReminderList();
		for (Reminder reminder : reminderList) {
			this.a(reminder, pluginSession);
		}
		return null;
	}

	private void a(Reminder reminder, BpmTaskPluginSession pluginSession) {
		Object object;
		String condition = reminder.getCondition();
		if (StringUtil.isNotEmpty((String) condition) && (object = null) instanceof Boolean
				&& !((Boolean) object).booleanValue()) {
			return;
		}
	}
}