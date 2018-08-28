package org.minxc.emp.bpm.plugin.task.reminders.def;

public class WarningSet {
	protected String L;
	protected Integer M;
	protected Integer N;

	public String getWarnName() {
		return this.L;
	}

	public void setWarnName(String warnName) {
		this.L = warnName;
	}

	public Integer getWarnTime() {
		return this.M;
	}

	public void setWarnTime(Integer warnTime) {
		this.M = warnTime;
	}

	public Integer getLevel() {
		return this.N;
	}

	public void setLevel(Integer level) {
		this.N = level;
	}
}