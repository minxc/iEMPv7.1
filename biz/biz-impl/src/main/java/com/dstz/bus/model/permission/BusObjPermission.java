package com.dstz.bus.model.permission;

import com.dstz.bus.api.model.permission.IBusObjPermission;
import com.dstz.bus.model.permission.AbstractPermission;
import com.dstz.bus.model.permission.BusTablePermission;
import java.util.HashMap;
import java.util.Map;

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