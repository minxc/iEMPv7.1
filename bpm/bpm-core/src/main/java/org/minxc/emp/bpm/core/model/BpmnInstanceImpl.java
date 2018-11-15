package org.minxc.emp.bpm.core.model;


import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.core.api.model.CommonModel;

public class BpmnInstanceImpl implements CommonModel, BpmnInstance {
	private static final long serialVersionUID = -2713628361947043674L;
	protected String id;
	protected String subject;
	protected String defId;
	protected String s;
	protected String A;
	protected String defName;
	protected String B;
	protected String status;
	protected Date endTime;
	protected Long C;
	protected String r;
	protected String D;
	protected String createBy;
	protected String creator;
	protected Date createTime;
	protected String x;
	protected String updateBy;
	protected Date updateTime;
	protected String E;
	protected String F;
	protected Short G = 0;
	protected String H;
	protected Integer supportMobile;
	protected String I;
	protected transient boolean J = true;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}

	public String getDefId() {
		return this.defId;
	}

	public void setActDefId(String actDefId) {
		this.s = actDefId;
	}

	public String getActDefId() {
		return this.s;
	}

	public void setDefKey(String defKey) {
		this.A = defKey;
	}

	public String getDefKey() {
		return this.A;
	}

	public void setDefName(String defName) {
		this.defName = defName;
	}

	public String getDefName() {
		return this.defName;
	}

	public void setBizKey(String bizKey) {
		this.B = bizKey;
	}

	public String getBizKey() {
		if (this.B == null) {
			return "";
		}
		return this.B;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setDuration(Long duration) {
		this.C = duration;
	}

	public Long getDuration() {
		return this.C;
	}

	public void setTypeId(String typeId) {
		this.r = typeId;
	}

	public String getTypeId() {
		return this.r;
	}

	public void setActInstId(String actInstId) {
		this.D = actInstId;
	}

	public String getActInstId() {
		return this.D;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateOrgId(String createOrgId) {
		this.x = createOrgId;
	}

	public String getCreateOrgId() {
		return this.x;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setIsFormmal(String isFormmal) {
		this.E = isFormmal;
	}

	public String getIsFormmal() {
		return this.E;
	}

	public void setParentInstId(String parentInstId) {
		this.F = parentInstId;
	}

	public String getParentInstId() {
		return this.F;
	}

	public void setIsForbidden(Short isForbidden) {
		this.G = isForbidden;
	}

	public Short getIsForbidden() {
		return this.G;
	}

	public void setDataMode(String dataMode) {
		this.H = dataMode;
	}

	public String getDataMode() {
		return this.H;
	}

	public void setSupportMobile(Integer supportMobile) {
		this.supportMobile = supportMobile;
	}

	public Integer getSupportMobile() {
		return this.supportMobile;
	}

	public void setSuperNodeId(String superNodeId) {
		this.I = superNodeId;
	}

	public String getSuperNodeId() {
		return this.I;
	}

	public String toString() {
		return new ToStringBuilder((Object) this).append("id", (Object) this.id)
				.append("subject", (Object) this.subject).append("defId", (Object) this.defId)
				.append("actDefId", (Object) this.s).append("defKey", (Object) this.A)
				.append("defName", (Object) this.defName).append("bizKey", (Object) this.B)
				.append("status", (Object) this.status).append("endTime", (Object) this.endTime)
				.append("duration", (Object) this.C).append("typeId", (Object) this.r)
				.append("actInstId", (Object) this.D).append("createBy", (Object) this.createBy)
				.append("creator", (Object) this.creator).append("createTime", (Object) this.createTime)
				.append("createOrgId", (Object) this.x).append("updateBy", (Object) this.updateBy)
				.append("updateTime", (Object) this.updateTime).append("isFormmal", (Object) this.E)
				.append("parentInstId", (Object) this.F).append("isForbidden", (Object) this.G)
				.append("dataMode", (Object) this.H).append("supportMobile", (Object) this.supportMobile)
				.append("superNodeId", (Object) this.I).toString();
	}

	public Boolean hasCreate() {
		return this.J;
	}

	public void setHasCreate(Boolean hasCreate) {
		this.J = hasCreate;
	}
}