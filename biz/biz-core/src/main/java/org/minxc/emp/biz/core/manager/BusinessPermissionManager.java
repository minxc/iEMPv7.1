package org.minxc.emp.biz.core.manager;

import org.minxc.emp.biz.core.model.BusinessPermissionImpl;
import org.minxc.emp.common.manager.Manager;


public interface BusinessPermissionManager extends Manager<String, BusinessPermissionImpl> {
	
	//TODO:思考两个方法的区别
	public BusinessPermissionImpl getByObjTypeAndObjVal(String var1, String var2);

	public BusinessPermissionImpl getByObjTypeAndObjVal(String var1, String var2, String var3);
}