package org.minxc.emp.biz.api.model;

import org.minxc.emp.biz.api.model.permission.IBusObjPermission;

/**
 *  
 * BusinessObject接口类
 */
public interface IBusinessObject {
	/**
	 * 
	 * key
	 * 
	 * 
	 * @return
	 */
	String getKey();

	/**
	 * 
	 * 名字
	 * 
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 
	 * 描述
	 * 
	 * 
	 * @return
	 */
	String getDesc();

	/**
	 * 
	 * 持久化类型 枚举： BusinessObjectPersistenceType
	 * 
	 * 
	 * @return
	 */
	String getPersistenceType();

	/**
	 * 
	 * 业务表对象的关联对象
	 * 
	 * 
	 * @return
	 */
	IBusTableRel getRelation();

	IBusObjPermission getPermission();

	void setPermission(IBusObjPermission permission);
	
	/**
	 * 
	 * 判断某个表有没有数据库操作权限
	 * 逻辑如下：
	 * 如果字段中有一个字段包含操作权限，则表就有数据库操作权限
	 * 如果一个字段都没，则表没有操作权限
	 * 
	 * 
	 * @param columnKey
	 * @return
	 */
	boolean haveTableDbEditRights(String tableKey);
	
	/**
	 * 
	 * 判断某个表有没有数据库读取权限
	 * 逻辑如下：
	 * 如果字段中有一个字段包含读取权限，则表就有数据库读取权限
	 * 如果一个字段都没，则表没有读取权限
	 * 
	 * 
	 * @param columnKey
	 * @return
	 */
	boolean haveTableDbReadRights(String tableKey);
	
	/**
	 * 
	 * 判断某个字段有没有数据库操作权限
	 * 
	 * 
	 * @param tableKey
	 * @param columnKey
	 * @return
	 */
	boolean haveColumnDbEditRights(String tableKey, String columnKey);
	
	/**
	 * 
	 * 判断某个字段有没有数据库读取权限
	 * 
	 * 
	 * @param tableKey
	 * @param columnKey
	 * @return
	 */
	boolean haveColumnDbReadRights(String tableKey, String columnKey);
}
