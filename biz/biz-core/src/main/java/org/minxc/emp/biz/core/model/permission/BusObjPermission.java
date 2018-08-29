package org.minxc.emp.biz.core.model.permission;

import java.util.HashMap;
import java.util.Map;

import org.minxc.emp.biz.api.model.permission.IBusObjPermission;
import org.minxc.emp.biz.core.model.permission.AbstractPermission;
import org.minxc.emp.biz.core.model.permission.BusTablePermission;

public class BusObjPermission extends AbstractPermission implements IBusObjPermission {
	
	
	private String key;
	private String name;
	private Map<String, BusTablePermission> R = new HashMap<String, BusTablePermission>();

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

	public Map<String, BusTablePermission> getTableMap() {
		return this.R;
	}

	public void setTableMap(Map<String, BusTablePermission> tableMap) {
		this.R = tableMap;
	}
}