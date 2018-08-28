package com.dstz.bus.model.permission;

import com.dstz.bus.api.model.permission.IBusTablePermission;
import com.dstz.bus.model.permission.AbstractPermission;
import com.dstz.bus.model.permission.BusColumnPermission;
import java.util.HashMap;
import java.util.Map;

public class BusTablePermission extends AbstractPermission implements IBusTablePermission {
	private String key;
	private String comment;
	private Map<String, BusColumnPermission> S = new HashMap<String, BusColumnPermission>();

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

	public Map<String, BusColumnPermission> getColumnMap() {
		return this.S;
	}

	public void setColumnMap(Map<String, BusColumnPermission> columnMap) {
		this.S = columnMap;
	}
}