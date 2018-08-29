package org.minxc.emp.bpm.plugin.task.reminders.plugin;

import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
import com.dstz.sys.api.groovy.IGroovyScriptEngine;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.bpm.plugin.task.reminders.def.Reminder;
import org.minxc.emp.bpm.plugin.task.reminders.def.RemindersPluginDef;

public class RemindersPlugin extends AbstractBpmTaskPlugin<BpmTaskPluginSession, RemindersPluginDef> {
	@Resource
	IGroovyScriptEngine groovyScriptEngine;

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