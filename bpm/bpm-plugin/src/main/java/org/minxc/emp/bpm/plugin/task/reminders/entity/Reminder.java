package org.minxc.emp.bpm.plugin.task.reminders.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.minxc.emp.bpm.plugin.task.reminders.entity.WarningSet;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"condition", "htmlMsg", "plainMsg", "dueScript", "warningSet"})
@XmlRootElement(name = "reminder")
public class Reminder {
	protected String condition;
	protected String E;
	protected String F;
	protected String G;
	protected List<WarningSet> O;
	@XmlAttribute(name = "name")
	protected String name;
	@XmlAttribute(name = "relNodeId")
	protected String v;
	@XmlAttribute(name = "relNodeEvent")
	protected String w;
	@XmlAttribute(name = "dateType")
	protected String x;
	@XmlAttribute(name = "dueTime")
	protected Integer y;
	@XmlAttribute(name = "dueAction")
	protected String z;
	@XmlAttribute(name = "sendMsg")
	protected Boolean P;
	@XmlAttribute(name = "msgSendTime")
	protected Integer B;
	@XmlAttribute(name = "msgCount")
	protected Integer C;
	@XmlAttribute(name = "msgType")
	protected String c;
	@XmlAttribute(name = "msgInterval")
	protected Integer D;

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String value) {
		this.condition = value;
	}

	public String getHtmlMsg() {
		return this.E;
	}

	public void setHtmlMsg(String value) {
		this.E = value;
	}

	public String getPlainMsg() {
		return this.F;
	}

	public void setPlainMsg(String value) {
		this.F = value;
	}

	public String getDueScript() {
		return this.G;
	}

	public void setDueScript(String value) {
		this.G = value;
	}

	public List<WarningSet> getWarningSet() {
		if (this.O == null) {
			this.O = new ArrayList<WarningSet>();
		}
		return this.O;
	}

	public void setWarningSet(List<WarningSet> warningSetExtList) {
		this.O = warningSetExtList;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getRelNodeId() {
		return this.v;
	}

	public void setRelNodeId(String value) {
		this.v = value;
	}

	public String getRelNodeEvent() {
		return this.w;
	}

	public void setRelNodeEvent(String value) {
		this.w = value;
	}

	public String getDateType() {
		return this.x;
	}

	public void setDateType(String value) {
		this.x = value;
	}

	public Integer getDueTime() {
		return this.y;
	}

	public void setDueTime(Integer value) {
		this.y = value;
	}

	public String getDueAction() {
		return this.z;
	}

	public void setDueAction(String value) {
		this.z = value;
	}

	public Boolean d() {
		return this.P;
	}

	public void setSendMsg(Boolean value) {
		this.P = value;
	}

	public Integer getMsgSendTime() {
		return this.B;
	}

	public void setMsgSendTime(Integer value) {
		this.B = value;
	}

	public Integer getMsgCount() {
		return this.C;
	}

	public void setMsgCount(Integer value) {
		this.C = value;
	}

	public String getMsgType() {
		return this.c;
	}

	public void setMsgType(String value) {
		this.c = value;
	}

	public Integer getMsgInterval() {
		return this.D;
	}

	public void setMsgInterval(Integer value) {
		this.D = value;
	}
}