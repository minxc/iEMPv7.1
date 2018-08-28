package org.minxc.emp.bpm.plugin.task.reminders.entity;

import javax.xml.bind.annotation.XmlRegistry;

import org.minxc.emp.bpm.plugin.task.reminders.entity.Reminder;
import org.minxc.emp.bpm.plugin.task.reminders.entity.Reminders;
import org.minxc.emp.bpm.plugin.task.reminders.entity.WarningSet;

@XmlRegistry
public class ObjectFactory {
	public Reminder a() {
		return new Reminder();
	}

	public WarningSet b() {
		return new WarningSet();
	}

	public Reminders c() {
		return new Reminders();
	}
}