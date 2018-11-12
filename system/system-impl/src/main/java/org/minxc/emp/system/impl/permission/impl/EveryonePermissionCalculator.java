package org.minxc.emp.system.impl.permission.impl;


import org.minxc.emp.system.api.permission.IPermissionCalculator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * 所有人
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

	@Override
	public boolean haveRights(JSONObject json) {
		return true;
	}

}
