package org.minxc.emp.bpm.core.model;

import java.util.Date;

public class BpmnTaskApprove {

	protected String id;
	protected String nodeName;
	protected String R;
	protected Date Y;
	protected Long Z;
	protected String aa;
	protected String subject;
	protected String defName;
	protected String status;
	protected Date endTime;
	protected Long C;
	protected String r;
	protected String D;
	protected String createBy;
	protected String creator;
	protected Date createTime;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Date getApproveTime() {
		return this.Y;
	}

	public void setApproveTime(Date approveTime) {
		this.Y = approveTime;
	}

	public Long getDurMs() {
		return this.Z;
	}

	public void setDurMs(Long durMs) {
		this.Z = durMs;
	}

	public String getApproveStatus() {
		return this.aa;
	}

	public void setApproveStatus(String approveStatus) {
		this.aa = approveStatus;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStatus() {
		return this.status;
	}

	public String getNodeId() {
		return this.R;
	}

	public void setNodeId(String nodeId) {
		this.R = nodeId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getDuration() {
		return this.C;
	}

	public void setDuration(Long duration) {
		this.C = duration;
	}

	public String getTypeId() {
		return this.r;
	}

	public void setTypeId(String typeId) {
		this.r = typeId;
	}

	public String getActInstId() {
		return this.D;
	}

	public String getDefName() {
		return this.defName;
	}

	public void setDefName(String defName) {
		this.defName = defName;
	}

	public void setActInstId(String actInstId) {
		this.D = actInstId;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}