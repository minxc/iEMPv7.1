package org.minxc.emp.biz.core.model.permission;

import java.util.HashMap;
import java.util.Map;

import org.minxc.emp.biz.core.api.model.permission.IBusTablePermission;
import org.minxc.emp.biz.core.model.permission.AbstractPermission;
import org.minxc.emp.biz.core.model.permission.BusColumnPermission;

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