package org.minxc.emp.system.api.permission;

import com.alibaba.fastjson.JSONObject;

/**
 * 系统内置权限
 */
public interface IPermissionCalculator {
	/**
	 * 
	 * 类型
	 * 	
	 * @return
	 */
	String getType();
	/**
	 * 
	 * 名字
	 * 	
	 * @return
	 */
	String getTitle();
	/**
	 * 
	 * 判断当前用户是否有这个权限
	 * json格式:{type:"everyone",...(一些不同类型约定的参数)}
	 * 	
	 * @param jsonObject
	 * @return
	 */
	boolean haveRights(JSONObject json);
	
}
