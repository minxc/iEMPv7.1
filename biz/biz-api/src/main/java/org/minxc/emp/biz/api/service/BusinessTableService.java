package org.minxc.emp.biz.api.service;

import org.minxc.emp.biz.api.model.BusinessTable;

/**
 * <pre>
 * 描述：业务表对其他模块的service
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年3月26日 下午6:03:05
 * 版权:summer
 * </pre>
 */
public interface BusinessTableService {
	/**
	 * <pre>
	 * 根据key获取businessTable
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	BusinessTable getByKey(String key);
	
	/**
	 * <pre>
	 * 获取填充好所有数据的表
	 * </pre>
	 * @param key
	 * @return
	 */
	BusinessTable getFilledByKey(String key);
	
}
