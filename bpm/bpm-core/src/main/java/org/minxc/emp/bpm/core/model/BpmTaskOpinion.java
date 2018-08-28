package org.minxc.emp.bpm.core.model;

import com.dstz.base.api.model.IBaseModel;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.minxc.emp.bpm.api.model.task.IBpmTaskOpinion;

public class BpmTaskOpinion implements IBaseModel, IBpmTaskOpinion {
	protected String id;
	protected String o;
	protected String ac;
	protected String taskId;
	protected String ad;
	protected String taskName;
	protected String ae;
	private String af;
	protected String ag;
	protected String ah;
	protected String ai;
	protected String status;
	protected String formId;
	protected String createBy;
	protected Date createTime;
	protected Date Y;
	protected Long Z;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setInstId(String instId) {
		this.o = instId;
	}

	public String getInstId() {
		return this.o;
	}

	public void setSupInstId(String supInstId) {
		this.ac = supInstId;
	}

	public String getSupInstId() {
		return this.ac;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskKey(String taskKey) {
		this.ad = taskKey;
	}

	public String getTaskKey() {
		return this.ad;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setToken(String token) {
		this.ae = token;
	}

	public String getToken() {
		return this.ae;
	}

	public void setApprover(String approver) {
		this.ag = approver;
	}

	public String getApprover() {
		return this.ag;
	}

	public void setApproverName(String approverName) {
		this.ah = approverName;
	}

	public String getApproverName() {
		return this.ah;
	}

	public void setOpinion(String opinion) {
		this.ai = opinion;
	}

	public String getOpinion() {
		return this.ai;
	}

	public Date getApproveTime() {
		return this.Y;
	}

	public void setApproveTime(Date approveTime) {
		this.Y = approveTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setDurMs(Long durMs) {
		this.Z = durMs;
	}

	public Long getDurMs() {
		return this.Z;
	}

	public String toString() {
		return new ToStringBuilder((Object) this).append("id", (Object) this.id).append("instId", (Object) this.o)
				.append("supInstId", (Object) this.ac).append("taskId", (Object) this.taskId)
				.append("taskKey", (Object) this.ad).append("taskName", (Object) this.taskName)
				.append("token", (Object) this.ae).append("approver", (Object) this.ag)
				.append("approverName", (Object) this.ah).append("opinion", (Object) this.ai)
				.append("status", (Object) this.status).append("formId", (Object) this.formId)
				.append("createBy", (Object) this.createBy).append("createTime", (Object) this.createTime)
				.append("approveTime", (Object) this.Y).append("durMs", (Object) this.Z).toString();
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

	public String getAssignInfo() {
		return this.af;
	}

	public void setAssignInfo(String assignInfo) {
		this.af = assignInfo;
	}
}