package org.minxc.emp.system.impl.permission.impl;


import org.minxc.emp.system.api.permission.IPermissionCalculator;
import org.springframework.stereotype.Service;

//import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * 描述：所有人
 */
@Service
public class EveryonePermissionCalculator implements IPermissionCalculator {

	@Override
	public String getType() {
		return "everyone";
	}

	@Override
	public String getTitle() {
		return "所有人";
	}

//	@Override
//	public boolean haveRights(JSONObject json) {
//		return true;
//	}
	@Override
	public boolean haveRights(JsonNode json) {
		return true;
	}

}
