package org.minxc.emp.model;

import java.util.Date;

public class BpmTaskReminderEntity {
    private String id;

    private String taskId;

    private String name;

    private String relDate;

    private String dueAction;

    private String dueScript;

    private Date dueDate;

    private Long isSendMsg;

    private Date msgSendDate;

    private Long msgInterval;

    private Long msgCount;

    private String msgType;

    private String htmlMsg;

    private String plainMsg;

    private String warningset;

    private Date triggerDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRelDate() {
        return relDate;
    }

    public void setRelDate(String relDate) {
        this.relDate = relDate == null ? null : relDate.trim();
    }

    public String getDueAction() {
        return dueAction;
    }

    public void setDueAction(String dueAction) {
        this.dueAction = dueAction == null ? null : dueAction.trim();
    }

    public String getDueScript() {
        return dueScript;
    }

    public void setDueScript(String dueScript) {
        this.dueScript = dueScript == null ? null : dueScript.trim();
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getIsSendMsg() {
        return isSendMsg;
    }

    public void setIsSendMsg(Long isSendMsg) {
        this.isSendMsg = isSendMsg;
    }

    public Date getMsgSendDate() {
        return msgSendDate;
    }

    public void setMsgSendDate(Date msgSendDate) {
        this.msgSendDate = msgSendDate;
    }

    public Long getMsgInterval() {
        return msgInterval;
    }

    public void setMsgInterval(Long msgInterval) {
        this.msgInterval = msgInterval;
    }

    public Long getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(Long msgCount) {
        this.msgCount = msgCount;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getHtmlMsg() {
        return htmlMsg;
    }

    public void setHtmlMsg(String htmlMsg) {
        this.htmlMsg = htmlMsg == null ? null : htmlMsg.trim();
    }

    public String getPlainMsg() {
        return plainMsg;
    }

    public void setPlainMsg(String plainMsg) {
        this.plainMsg = plainMsg == null ? null : plainMsg.trim();
    }

    public String getWarningset() {
        return warningset;
    }

    public void setWarningset(String warningset) {
        this.warningset = warningset == null ? null : warningset.trim();
    }

    public Date getTriggerDate() {
        return triggerDate;
    }

    public void setTriggerDate(Date triggerDate) {
        this.triggerDate = triggerDate;
    }
}