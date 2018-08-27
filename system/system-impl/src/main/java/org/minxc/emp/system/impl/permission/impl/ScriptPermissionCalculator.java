package org.minxc.emp.system.impl.permission.impl;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.IGroovyScriptEngine;
import org.minxc.emp.system.api.permission.IPermissionCalculator;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

//import com.alibaba.fastjson.JSONObject;

/**
 * 描述：脚本
 */
@Service
public class ScriptPermissionCalculator implements IPermissionCalculator {
	@Resource
	IGroovyScriptEngine groovyScriptEngine;

	@Override
	public String getTitle() {
		return "脚本";
	}

	@Override
	public String getType() {
		return "script";
	}

	@Override
	public boolean haveRights(JsonNode json) {
//		String script = json.getString("id");
//		return groovyScriptEngine.executeBoolean(script, null);
		String script = json.get("id").asText();
		return groovyScriptEngine.executeBoolean(script, null);
	}

}
