package org.minxc.emp.biz.api.service;

import java.util.List;

import org.minxc.emp.biz.api.model.BusinessObject;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Bo对其他模块提供出的service
 */
public interface BusinessObjectService {
	
	/**
	 * 
	 * 根据key获取BusinessObject
	 * 
	 * 
	 * @param key
	 * @return
	 */
	BusinessObject getByKey(String key);

	/**
	 * 
	 * 生成bo数据树
	 * 是pid那种形式
	 * 
	 * 
	 * @param key
	 * @return
	 */
	List<JSONObject> boTreeData(String key);
	
	BusinessObject getFilledByKey(String key);
}
