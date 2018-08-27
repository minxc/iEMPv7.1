package org.minxc.emp.biz.api.model;

import org.minxc.emp.biz.api.model.permission.BusinessObjectPermission;

/**
 * <pre> 
 * 描述：BusinessObject接口类
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年3月26日 下午5:40:45
 * 版权:summer
 * </pre>
 */
public interface BusinessObject {
	/**
	 * <pre>
	 * key
	 * </pre>
	 * 
	 * @return
	 */
	String getKey();

	/**
	 * <pre>
	 * 名字
	 * </pre>
	 * 
	 * @return
	 */
	String getName();

	/**
	 * <pre>
	 * 描述
	 * </pre>
	 * 
	 * @return
	 */
	String getDesc();

	/**
	 * <pre>
	 * 持久化类型 枚举： BusinessObjectPersistenceType
	 * </pre>
	 * 
	 * @return
	 */
	String getPersistenceType();

	/**
	 * <pre>
	 * 业务表对象的关联对象
	 * </pre>
	 * 
	 * @return
	 */
	BusinessTableRel getRelation();

	BusinessObjectPermission getPermission();

	void setPermission(BusinessObjectPermission permission);
	
	/**
	 * <pre>
	 * 判断某个表有没有数据库操作权限
	 * 逻辑如下：
	 * 如果字段中有一个字段包含操作权限，则表就有数据库操作权限
	 * 如果一个字段都没，则表没有操作权限
	 * </pre>
	 * 
	 * @param columnKey
	 * @return
	 */
	boolean haveTableDbEditRights(String tableKey);
	
	/**
	 * <pre>
	 * 判断某个表有没有数据库读取权限
	 * 逻辑如下：
	 * 如果字段中有一个字段包含读取权限，则表就有数据库读取权限
	 * 如果一个字段都没，则表没有读取权限
	 * </pre>
	 * 
	 * @param columnKey
	 * @return
	 */
	boolean haveTableDbReadRights(String tableKey);
	
	/**
	 * <pre>
	 * 判断某个字段有没有数据库操作权限
	 * </pre>
	 * 
	 * @param tableKey
	 * @param columnKey
	 * @return
	 */
	boolean haveColumnDbEditRights(String tableKey, String columnKey);
	
	/**
	 * <pre>
	 * 判断某个字段有没有数据库读取权限
	 * </pre>
	 * 
	 * @param tableKey
	 * @param columnKey
	 * @return
	 */
	boolean haveColumnDbReadRights(String tableKey, String columnKey);
}
