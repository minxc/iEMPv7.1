package org.minxc.emp.biz.api.service;

import org.minxc.emp.biz.api.model.BusinessTable;

/**
 * 
 * 业务表对其他模块的service
 */
public interface BusinessTableService {
	/**
	 * 
	 * 根据key获取businessTable
	 * 
	 * 
	 * @param key
	 * @return
	 */
	BusinessTable getByKey(String key);
	
	/**
	 * 
	 * 获取填充好所有数据的表
	 * 
	 * @param key
	 * @return
	 */
	BusinessTable getFilledByKey(String key);
	
}
