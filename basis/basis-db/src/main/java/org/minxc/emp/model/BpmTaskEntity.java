package org.minxc.emp.model;

import java.util.Date;

public class BpmTaskEntity {
    private String id;

    private String name;

    private String subject;

    private String instId;

    private String taskId;

    private String actInstId;

    private String actExecutionId;

    private String nodeId;

    private String defId;

    private String assigneeId;

    private String assigneeNames;

    private String status;

    private Long priority;

    private Date dueTime;

    private String taskType;

    private String parentId;

    private String typeId;

    private Date createTime;

    private String createBy;

    private Long supportMobile;

    private String backNode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getActInstId() {
        return actInstId;
    }

    public void setActInstId(String actInstId) {
        this.actInstId = actInstId == null ? null : actInstId.trim();
    }

    public String getActExecutionId() {
        return actExecutionId;
    }

    public void setActExecutionId(String actExecutionId) {
        this.actExecutionId = actExecutionId == null ? null : actExecutionId.trim();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId == null ? null : defId.trim();
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId == null ? null : assigneeId.trim();
    }

    public String getAssigneeNames() {
        return assigneeNames;
    }

    public void setAssigneeNames(String assigneeNames) {
        this.assigneeNames = assigneeNames == null ? null : assigneeNames.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Long getSupportMobile() {
        return supportMobile;
    }

    public void setSupportMobile(Long supportMobile) {
        this.supportMobile = supportMobile;
    }

    public String getBackNode() {
        return backNode;
    }

    public void setBackNode(String backNode) {
        this.backNode = backNode == null ? null : backNode.trim();
    }
}