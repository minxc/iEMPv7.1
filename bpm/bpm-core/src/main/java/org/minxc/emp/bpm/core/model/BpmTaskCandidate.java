package org.minxc.emp.bpm.core.model;

import com.minxc.emp.core.impl.model.AbtractCommonModel;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BpmTaskCandidate implements IBaseModel {
	protected String id;
	protected String taskId;
	protected String type;
	protected String ab;
	protected String o;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setExecutor(String executor) {
		this.ab = executor;
	}

	public String getExecutor() {
		return this.ab;
	}

	public void setInstId(String instId) {
		this.o = instId;
	}

	public String getInstId() {
		return this.o;
	}

	public String toString() {
		return new ToStringBuilder((Object) this).append("id", (Object) this.id).append("taskId", (Object) this.taskId)
				.append("type", (Object) this.type).append("executor", (Object) this.ab)
				.append("instId", (Object) this.o).toString();
	}

	public String getCreateBy() {
		return null;
	}

	public void setCreateBy(String createBy) {
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date createtime) {
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