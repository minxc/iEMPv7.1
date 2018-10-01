package org.minxc.emp.bpm.core.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TaskIdentityLink implements Serializable {
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -4727369427742946466L;
	
	
	public static final String ak = "user";
	protected String id;
	protected String taskId;
	protected String o;
	protected String type;
	protected String al;
	protected String identity;
	protected String am;

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

	public void setInstId(String instId) {
		this.o = instId;
	}

	public String getInstId() {
		return this.o;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setIdentityName(String identityName) {
		this.al = identityName;
	}

	public String getIdentityName() {
		return this.al;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getIdentity() {
		return this.identity;
	}

	public void setPermissionCode(String permissionCode) {
		this.am = permissionCode;
	}

	public String getPermissionCode() {
		return this.am;
	}

	public String toString() {
		return new ToStringBuilder((Object) this).append("id", (Object) this.id).append("taskId", (Object) this.taskId)
				.append("instId", (Object) this.o).append("type", (Object) this.type)
				.append("identityName", (Object) this.al).append("identity", (Object) this.identity)
				.append("permissionCode", (Object) this.am).toString();
	}
}