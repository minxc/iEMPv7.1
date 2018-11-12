package org.minxc.emp.biz.api.service;

import org.minxc.emp.biz.api.model.IBusinessTable;

/**
 * 
 * 业务表对其他模块的service
 * 
 * 
 * 日期:2018年3月26日 下午6:03:05
 * 
 * 
 */
public interface IBusinessTableService {
	/**
	 * 
	 * 根据key获取businessTable
	 * 
	 * 
	 * @param key
	 * @return
	 */
	IBusinessTable getByKey(String key);
	
	/**
	 * 
	 * 获取填充好所有数据的表
	 * 
	 * @param key
	 * @return
	 */
	IBusinessTable getFilledByKey(String key);
	
}
