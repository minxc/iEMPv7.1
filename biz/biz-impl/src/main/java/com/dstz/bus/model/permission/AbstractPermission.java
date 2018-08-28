package com.dstz.bus.model.permission;

import com.alibaba.fastjson.JSONArray;
import com.dstz.bus.api.model.permission.IAbstractPermission;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPermission implements IAbstractPermission {
	protected Map<String, JSONArray> P = new HashMap<String, JSONArray>();
	protected String Q;

	public String getResult() {
		return this.Q;
	}

	public void setResult(String result) {
		this.Q = result;
	}

	public Map<String, JSONArray> getRights() {
		return this.P;
	}

	public void setRights(Map<String, JSONArray> rights) {
		this.P = rights;
	}
}