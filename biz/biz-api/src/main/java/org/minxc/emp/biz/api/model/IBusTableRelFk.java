package org.minxc.emp.biz.api.model;

/**
 * 
 * BusTableRelFk接口类
 * 
 * 
 * 日期:2018年3月26日 下午5:25:46
 * 
 * 
 */
public interface IBusTableRelFk {
	/**
	 * 
	 * 业务表对应的映射字段
	 * 
	 * 
	 * @return
	 */
	String getFrom();

	/**
	 * 
	 * 映射的方式 枚举 BusTableRelFkType
	 * 
	 * 
	 * @return
	 */
	String getType();

	/**
	 * 
	 * 值
	 * 
	 * 
	 * @return
	 */
	String getValue();
}
