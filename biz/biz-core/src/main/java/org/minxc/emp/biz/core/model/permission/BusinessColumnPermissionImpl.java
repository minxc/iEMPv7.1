package org.minxc.emp.biz.core.model.permission;

import org.minxc.emp.biz.api.model.permission.BusinessColumnPermission;

public class BusinessColumnPermissionImpl extends AbstractPermission implements BusinessColumnPermission {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8456302348736295048L;
	
	private String key;
	private String comment;

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}