package org.minxc.emp.system.api.permission;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述：系统内置权限
 */
public interface IPermissionCalculator {
	/**
	 * <pre>
	 * 类型
	 * </pre>	
	 * @return
	 */
	String getType();
	/**
	 * <pre>
	 * 名字
	 * </pre>	
	 * @return
	 */
	String getTitle();
	/**
	 * <pre>
	 * 判断当前用户是否有这个权限
	 * json格式:{type:"everyone",...(一些不同类型约定的参数)}
	 * </pre>	
	 * @param jsonObject
	 * @return
	 */
	boolean haveRights(JSONObject json);
	
}
