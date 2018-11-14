package org.minxc.emp.biz.core.model.permission;

import java.util.HashMap;
import java.util.Map;

import org.minxc.emp.biz.api.model.permission.BusinessObjectPermission;

public class BusinessObjectPermissionImpl extends AbstractPermission implements BusinessObjectPermission {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7942366046542813475L;
	
	private String key;
	private String name;
	
	private Map<String, BusinessTablePermissionImpl> R = new HashMap<String, BusinessTablePermissionImpl>();

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, BusinessTablePermissionImpl> getTableMap() {
		return this.R;
	}

	public void setTableMap(Map<String, BusinessTablePermissionImpl> tableMap) {
		this.R = tableMap;
	}
}