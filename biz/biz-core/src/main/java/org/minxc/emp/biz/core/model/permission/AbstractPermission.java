package org.minxc.emp.biz.core.model.permission;

import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.Map;

import org.minxc.emp.biz.api.model.permission.IAbstractPermission;

public abstract class AbstractPermission implements IAbstractPermission {
	
	private static final long serialVersionUID = 4633557074058674394L;
	
	
	protected Map<String, JSONArray> rights = new HashMap<String, JSONArray>();
	protected String result;

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Map<String, JSONArray> getRights() {
		return this.rights;
	}

	public void setRights(Map<String, JSONArray> rights) {
		this.rights = rights;
	}
}