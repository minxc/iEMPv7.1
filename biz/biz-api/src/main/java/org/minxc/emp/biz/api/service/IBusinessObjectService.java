package org.minxc.emp.biz.api.service;

import java.util.List;

import org.minxc.emp.biz.api.model.IBusinessObject;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Bo对其他模块提供出的service
 * 
 * 
 * 日期:2018年3月26日 下午5:46:56
 * 
 * 
 */
public interface IBusinessObjectService {
	/**
	 * 
	 * 根据key获取BusinessObject
	 * 
	 * 
	 * @param key
	 * @return
	 */
	IBusinessObject getByKey(String key);

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
	
	IBusinessObject getFilledByKey(String key);
}
