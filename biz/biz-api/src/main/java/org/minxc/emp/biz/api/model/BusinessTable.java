package org.minxc.emp.biz.api.model;

import java.util.List;
import java.util.Map;

/**
 * 
 * BusinessTable对其他模块提供的接口类
 * 
 * 
 * 日期:2018年2月9日 下午4:19:02
 * 
 * 
 */
public interface BusinessTable {
	/**
	 * 默认的主键id名称
	 */
	public static final String ID_COLUMN_NAME = "id";
	/**
	 * 
	 * 业务表的别名
	 * 
	 * 
	 * @return
	 */
	String getKey();

	/**
	 * 
	 * 表名
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
	String getComment();

	/**
	 * 
	 * 数据源别名
	 * 
	 * 
	 * @return
	 */
	String getDsKey();

	/**
	 * 
	 * 字段
	 * 
	 * 
	 * @return
	 */
	List<? extends BusinessColumn> getColumns();
	/**
	 * 
	 * 数据源名称
	 * 
	 * @return
	 */
	String getDsName();
	
	/**
	 * 
	 * 获取不包含主键的字段
	 * 
	 * @return
	 */
	List<? extends BusinessColumn> getColumnsWithoutPk();
	
	/**
	 * 
	 * 获取表的初始化数据库的数据
	 * 不包含主键
	 * 字段取的是name
	 * 其实就是获取字段的默认值
	 * 	
	 * @return
	 */
	Map<String, Object> initDbData();
	
	/**
	 * 
	 * 获取表的初始化数据
	 * 不包含主键
	 * 字段取的是key
	 * 其实就是获取字段的默认值
	 * 	
	 * @return
	 */
	Map<String, Object> initData();
	
	/**
	 * 
	 * 根据字段key获取字段
	 * 	
	 * @param key
	 * @return
	 */
	BusinessColumn getColumnByKey(String key);
	
	/**
	 * 
	 * 根据字段Name获取字段
	 * 	
	 * @param name
	 * @return
	 */
	BusinessColumn getColumn(String name);
}
