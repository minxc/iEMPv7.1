package org.minxc.emp.bpm.plugin.task.reminders.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "warningSet")
public class WarningSet {
	@XmlAttribute(name = "warnName")
	protected String L;
	@XmlAttribute(name = "warnTime")
	protected Integer M;
	@XmlAttribute(name = "level")
	protected Integer N;

	public String getWarnName() {
		return this.L;
	}

	public void setWarnName(String value) {
		this.L = value;
	}

	public Integer getWarnTime() {
		return this.M;
	}

	public void setWarnTime(Integer value) {
		this.M = value;
	}

	public Integer getLevel() {
		return this.N;
	}

	public void setLevel(Integer value) {
		this.N = value;
	}
}