package org.minxc.emp.biz.api.service;

import java.util.List;

import org.minxc.emp.biz.api.model.IBusinessObject;

import com.alibaba.fastjson.JSONObject;

/**
 * <pre>
 * 描述：Bo对其他模块提供出的service
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018年3月26日 下午5:46:56
 * 版权:summer
 * </pre>
 */
public interface IBusinessObjectService {
	/**
	 * <pre>
	 * 根据key获取BusinessObject
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	IBusinessObject getByKey(String key);

	/**
	 * <pre>
	 * 生成bo数据树
	 * 是pid那种形式
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	List<JSONObject> boTreeData(String key);
	
	IBusinessObject getFilledByKey(String key);
}
