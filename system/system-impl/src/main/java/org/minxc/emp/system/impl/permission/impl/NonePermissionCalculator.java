package org.minxc.emp.system.impl.permission.impl;

import org.minxc.emp.system.api.permission.IPermissionCalculator;
import org.springframework.stereotype.Service;

//import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
/**
 * 描述：无人
 */
@Service
public class NonePermissionCalculator implements IPermissionCalculator {

	@Override
	public String getTitle() {
		return "无";
	}

	@Override
	public String getType() {
		return "none";
	}

	@Override
	public boolean haveRights(JsonNode json) {
		return false;
	}

}
