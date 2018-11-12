package org.minxc.emp.system.impl.permission.impl;

import org.minxc.emp.system.api.permission.PermissionCalculator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
/**
 * 无人
 */
@Service
public class NonePermissionCalculator implements PermissionCalculator {

	@Override
	public String getTitle() {
		return "无";
	}

	@Override
	public String getType() {
		return "none";
	}

	@Override
	public boolean haveRights(JSONObject json) {
		return false;
	}

}
