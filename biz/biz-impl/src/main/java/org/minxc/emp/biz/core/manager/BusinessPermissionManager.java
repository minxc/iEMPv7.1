package org.minxc.emp.biz.core.manager;

import org.minxc.emp.biz.core.model.BusinessPermission;

import com.dstz.base.manager.Manager;

public interface BusinessPermissionManager extends Manager<String, BusinessPermission> {
	
	//TODO:思考两个方法的区别
	public BusinessPermission getByObjTypeAndObjVal(String var1, String var2);

	public BusinessPermission getByObjTypeAndObjVal(String var1, String var2, String var3);
}