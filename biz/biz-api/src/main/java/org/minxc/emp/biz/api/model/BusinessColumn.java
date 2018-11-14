package org.minxc.emp.biz.api.model;


/**
 * BusinessColumn对其他模块的支持
 */
public interface BusinessColumn {

	String getKey();
	
	String getName();

	String getType();

	int getLength();

	int getDecimal();

	boolean isRequired();

	boolean isPrimary();

	String getDefaultValue();

	String getComment();

	BusinessColumnControl getCtrl();

	String getTableId();

	BusinessTable getTable();
	
	/**
	 * 
	 * 获取字段的初始化值
	 * 根据defaultValue和type映射成对象
	 * 
	 * 
	 * @return
	 */
	Object initValue();
	
	/**
	 * 
	 * 把某个字符串按照字段类型转成真正的值
	 * 	
	 * @param str
	 * @return
	 */
	Object value(String str);

}
