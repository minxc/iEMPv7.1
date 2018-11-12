package org.minxc.emp.biz.api.service;

import org.minxc.emp.biz.api.model.IBusinessTable;

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
