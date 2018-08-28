package org.minxc.emp.bpm.core.model;

import com.dstz.base.api.model.IBaseModel;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.minxc.emp.bpm.api.model.def.IBpmDefinition;

public class BpmDefinition implements IBaseModel, IBpmDefinition {
	protected String id;
	protected String name;
	protected String key;
	protected String desc;
	protected String r;
	protected String status = "draft";
	protected String s;
	protected String t;
	protected String u;
	protected Integer version;
	protected String v;
	protected String w;
	protected String createBy;
	protected Date createTime;
	protected String x;
	protected String updateBy;
	protected Date updateTime;
	protected Integer supportMobile = 0;
	protected String y;
	protected Integer z;

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

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setTypeId(String typeId) {
		this.r = typeId;
	}

	public String getTypeId() {
		return this.r;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setActDefId(String actDefId) {
		this.s = actDefId;
	}

	public String getActDefId() {
		return this.s;
	}

	public void setActModelId(String actModelId) {
		this.t = actModelId;
	}

	public String getActModelId() {
		return this.t;
	}

	public void setActDeployId(String actDeployId) {
		this.u = actDeployId;
	}

	public String getActDeployId() {
		return this.u;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setMainDefId(String mainDefId) {
		this.v = mainDefId;
	}

	public String getMainDefId() {
		return this.v;
	}

	public void setIsMain(String isMain) {
		this.w = isMain;
	}

	public String getIsMain() {
		return this.w;
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

	public void setSupportMobile(Integer supportMobile) {
		this.supportMobile = supportMobile;
	}

	public Integer getSupportMobile() {
		return this.supportMobile;
	}

	public void setDefSetting(String defSetting) {
		this.y = defSetting;
	}

	public String getDefSetting() {
		return this.y;
	}

	public void setRev(Integer rev) {
		this.z = rev;
	}

	public Integer getRev() {
		return this.z;
	}

	public String toString() {
		return new ToStringBuilder((Object) this).append("id", (Object) this.id).append("name", (Object) this.name)
				.append("key", (Object) this.key).append("desc", (Object) this.desc).append("typeId", (Object) this.r)
				.append("status", (Object) this.status).append("actDefId", (Object) this.s)
				.append("actModelId", (Object) this.t).append("actDeployId", (Object) this.u)
				.append("version", (Object) this.version).append("mainDefId", (Object) this.v)
				.append("isMain", (Object) this.w).append("createBy", (Object) this.createBy)
				.append("createTime", (Object) this.createTime).append("createOrgId", (Object) this.x)
				.append("updateBy", (Object) this.updateBy).append("updateTime", (Object) this.updateTime)
				.append("supportMobile", (Object) this.supportMobile).append("defSetting", (Object) this.y)
				.append("rev", (Object) this.z).toString();
	}
}