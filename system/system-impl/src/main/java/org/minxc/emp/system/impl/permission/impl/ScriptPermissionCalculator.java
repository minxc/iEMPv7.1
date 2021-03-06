package org.minxc.emp.system.impl.permission.impl;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.GroovyScriptEngine;
import org.minxc.emp.system.api.permission.PermissionCalculator;
import org.springframework.stereotype.Service;


import com.alibaba.fastjson.JSONObject;

/**
 * 脚本
 */
@Service
public class ScriptPermissionCalculator implements PermissionCalculator {
	@Resource
	GroovyScriptEngine groovyScriptEngine;

	@Override
	public String getTitle() {
		return "脚本";
	}

	@Override
	public String getType() {
		return "script";
	}

	@Override
	public boolean haveRights(JSONObject json) {
		String script = json.getString("id");
		return groovyScriptEngine.executeBoolean(script, null);
	}

}
