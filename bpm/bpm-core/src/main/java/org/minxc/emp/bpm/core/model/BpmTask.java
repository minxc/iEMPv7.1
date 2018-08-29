package org.minxc.emp.bpm.core.model;

import com.minxc.emp.core.impl.model.AbtractCommonModel;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.minxc.emp.bpm.api.model.task.IBpmTask;

public class BpmTask implements IBaseModel, IBpmTask {
	protected String id;
	protected String name;
	protected String subject;
	protected String o;
	protected String taskId;
	protected String R;
	protected String defId;
	protected String S;
	protected String T;
	protected String status;
	protected Integer priority;
	protected Date U;
	protected String V;
	protected String parentId;
	protected String D;
	protected String W;
	protected String r;
	protected Date createTime;
	protected String createBy;
	protected Integer supportMobile;
	protected String X;

	public String getTaskType() {
		return this.V;
	}

	public void setTaskType(String taskType) {
		this.V = taskType;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getAssigneeNames() {
		return this.T;
	}

	public void setAssigneeNames(String assigneeNames) {
		this.T = assigneeNames;
	}

	public void setInstId(String instId) {
		this.o = instId;
	}

	public String getInstId() {
		return this.o;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setNodeId(String nodeId) {
		this.R = nodeId;
	}

	public String getNodeId() {
		return this.R;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}

	public String getDefId() {
		return this.defId;
	}

	public void setAssigneeId(String assigneeId) {
		this.S = assigneeId;
	}

	public String getAssigneeId() {
		return this.S;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public String getBackNode() {
		return this.X;
	}

	public void setBackNode(String backNode) {
		this.X = backNode;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setDueTime(Date dueTime) {
		this.U = dueTime;
	}

	public Date getDueTime() {
		return this.U;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setActInstId(String actInstId) {
		this.D = actInstId;
	}

	public String getActInstId() {
		return this.D;
	}

	public void setActExecutionId(String actExecutionId) {
		this.W = actExecutionId;
	}

	public String getActExecutionId() {
		return this.W;
	}

	public void setTypeId(String typeId) {
		this.r = typeId;
	}

	public String getTypeId() {
		return this.r;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setSupportMobile(Integer supportMobile) {
		this.supportMobile = supportMobile;
	}

	public Integer getSupportMobile() {
		return this.supportMobile;
	}

	public String toString() {
		return new ToStringBuilder((Object) this).append("id", (Object) this.id).append("name", (Object) this.name)
				.append("subject", (Object) this.subject).append("instId", (Object) this.o)
				.append("taskId", (Object) this.taskId).append("nodeId", (Object) this.R)
				.append("defId", (Object) this.defId).append("assigneeId", (Object) this.S)
				.append("status", (Object) this.status).append("priority", (Object) this.priority)
				.append("dueTime", (Object) this.U).append("taskType", (Object) this.V)
				.append("parentId", (Object) this.parentId).append("actInstId", (Object) this.D)
				.append("actExecutionId", (Object) this.W).append("typeId", (Object) this.r)
				.append("createTime", (Object) this.createTime).append("createBy", (Object) this.createBy)
				.append("supportMobile", (Object) this.supportMobile).toString();
	}

	public String getActExecutionIdId() {
		return this.W;
	}

	public Date getUpdateTime() {
		return null;
	}

	public void setUpdateTime(Date updatetime) {
	}

	public String getUpdateBy() {
		return null;
	}

	public void setUpdateBy(String updateBy) {
	}
}