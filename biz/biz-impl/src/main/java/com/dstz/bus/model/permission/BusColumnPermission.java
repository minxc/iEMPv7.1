package com.dstz.bus.model.permission;

import com.dstz.bus.api.model.permission.IBusColumnPermission;
import com.dstz.bus.model.permission.AbstractPermission;

public class BusColumnPermission extends AbstractPermission implements IBusColumnPermission {
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