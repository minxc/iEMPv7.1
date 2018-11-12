package org.minxc.emp.system.impl.permission.impl;

import org.minxc.emp.system.api.permission.PermissionCalculator;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;


/**
 *  用户
 */
@Service
public class UsersPermissionCalculator implements PermissionCalculator {

	@Override
	public String getTitle() {
		return "用户";
	}

	@Override
	public String getType() {
		return "user";
	}

	@Override
	public boolean haveRights(JSONObject json) {
		for(String id :json.getString("id").split(",")) {
			if(id.equals(ContextUtil.getCurrentUserId())) {
				return true;
			}
		}
		return false;
	}
	
	
	

}
