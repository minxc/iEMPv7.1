package org.minxc.emp.biz.core.model.permission;

import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.Map;

import org.minxc.emp.biz.api.model.permission.IAbstractPermission;

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