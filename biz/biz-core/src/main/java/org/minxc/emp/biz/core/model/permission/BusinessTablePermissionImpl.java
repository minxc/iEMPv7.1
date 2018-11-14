package org.minxc.emp.biz.core.model.permission;

import java.util.HashMap;
import java.util.Map;

import org.minxc.emp.biz.api.model.permission.BusinessTablePermission;

public class BusinessTablePermissionImpl extends AbstractPermission implements BusinessTablePermission {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3349087551365334534L;
	
	private String key;
	private String comment;
	private Map<String, BusinessColumnPermissionImpl> S = new HashMap<String, BusinessColumnPermissionImpl>();

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

	public Map<String, BusinessColumnPermissionImpl> getColumnMap() {
		return this.S;
	}

	public void setColumnMap(Map<String, BusinessColumnPermissionImpl> columnMap) {
		this.S = columnMap;
	}
}