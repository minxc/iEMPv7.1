package org.minxc.emp.bpm.plugin.task.reminders.def;

import java.util.List;

import org.minxc.emp.bpm.plugin.task.reminders.def.WarningSet;

public class Reminder {
	public static final String n = "create";
	public static final String o = "complete";
	public static final String p = "worktime";
	public static final String q = "caltime";
	public static final String r = "no-action";
	public static final String s = "auto-next";
	public static final String t = "end-process";
	public static final String u = "call-method";
	protected String name;
	protected String v;
	protected String w;
	protected String x;
	protected Integer y;
	protected String z;
	protected Boolean A;
	protected Integer B;
	protected Integer C;
	protected String c;
	protected Integer D;
	protected String condition;
	protected String E;
	protected String F;
	protected String G;
	protected List<WarningSet> H;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelNodeId() {
		return this.v;
	}

	public void setRelNodeId(String relNodeId) {
		this.v = relNodeId;
	}

	public String getMsgType() {
		return this.c;
	}

	public void setMsgType(String msgType) {
		this.c = msgType;
	}

	public String getRelNodeEvent() {
		return this.w;
	}

	public void setRelNodeEvent(String relNodeEvent) {
		this.w = relNodeEvent;
	}

	public String getDateType() {
		return this.x;
	}

	public void setDateType(String dateType) {
		this.x = dateType;
	}

	public Integer getDueTime() {
		return this.y;
	}

	public void setDueTime(Integer dueTime) {
		this.y = dueTime;
	}

	public String getDueAction() {
		return this.z;
	}

	public void setDueAction(String dueAction) {
		this.z = dueAction;
	}

	public Boolean getIsSendMsg() {
		return this.A;
	}

	public void setIsSendMsg(Boolean isSendMsg) {
		this.A = isSendMsg;
	}

	public Integer getMsgSendTime() {
		return this.B;
	}

	public void setMsgSendTime(Integer msgSendTime) {
		this.B = msgSendTime;
	}

	public Integer getMsgCount() {
		return this.C;
	}

	public void setMsgCount(Integer msgCount) {
		this.C = msgCount;
	}

	public Integer getMsgInterval() {
		return this.D;
	}

	public void setMsgInterval(Integer msgInterval) {
		this.D = msgInterval;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getHtmlMsg() {
		return this.E;
	}

	public void setHtmlMsg(String htmlMsg) {
		this.E = htmlMsg;
	}

	public String getPlainMsg() {
		return this.F;
	}

	public void setPlainMsg(String plainMsg) {
		this.F = plainMsg;
	}

	public String getDueScript() {
		return this.G;
	}

	public void setDueScript(String dueScript) {
		this.G = dueScript;
	}

	public List<WarningSet> getWarningSetList() {
		return this.H;
	}

	public void setWarningSetList(List<WarningSet> warningSetList) {
		this.H = warningSetList;
	}
}