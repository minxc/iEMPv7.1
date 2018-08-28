package org.minxc.emp.bpm.plugin.task.reminders.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.minxc.emp.bpm.plugin.task.reminders.entity.Reminder;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"reminder"})
@XmlRootElement(name = "reminders")
public class Reminders {
	@XmlElement(required = true)
	protected List<Reminder> Q;

	public List<Reminder> getReminder() {
		if (this.Q == null) {
			this.Q = new ArrayList<Reminder>();
		}
		return this.Q;
	}

	public void setReminder(List<Reminder> reminderExtList) {
		this.Q = reminderExtList;
	}
}