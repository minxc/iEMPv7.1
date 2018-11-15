package org.minxc.emp.bpm.plugin.task.reminders.plugin;

import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.GroovyScriptEngine;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractBpmnTaskPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnTaskPluginSession;
import org.minxc.emp.bpm.plugin.task.reminders.def.Reminder;
import org.minxc.emp.bpm.plugin.task.reminders.def.RemindersPluginDefinition;
import org.minxc.emp.core.util.StringUtil;

public class RemindersPlugin extends AbstractBpmnTaskPlugin<BpmnTaskPluginSession, RemindersPluginDefinition> {
	@Resource
	GroovyScriptEngine groovyScriptEngine;

	public Void execute(BpmnTaskPluginSession pluginSession, RemindersPluginDefinition pluginDef) {
		if (pluginSession.getEventType() == EventType.TASK_COMPLETE_EVENT) {
			return null;
		}
		RemindersPluginDefinition reminderDef = pluginDef;
		List<Reminder> reminderList = reminderDef.getReminderList();
		for (Reminder reminder : reminderList) {
			this.a(reminder, pluginSession);
		}
		return null;
	}

	private void a(Reminder reminder, BpmnTaskPluginSession pluginSession) {
		Object object;
		String condition = reminder.getCondition();
		if (StringUtil.isNotEmpty((String) condition) && (object = null) instanceof Boolean
				&& !((Boolean) object).booleanValue()) {
			return;
		}
	}
}