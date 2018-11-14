package org.minxc.emp.biz.api.service;

import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.biz.api.model.BusinessObject;
import org.minxc.emp.biz.api.model.BusinessPermission;

import com.alibaba.fastjson.JSONObject;

/**
 * 业务数据服务接口
 */
public interface BusinessDataService {
	/**
	 * 
	 * 保存 FormDefData 中的data数据
	 * 	
	 * @param data
	 * @param businessPermission
	 */
	void saveFormDefData(JSONObject data, BusinessPermission businessPermission);

	/**
	 * 
	 * 获取formdefData中的data相关信息
	 * 
	 * 
	 * @param businessObject
	 * @param id
	 * @return
	 */
	JSONObject getFormDefData(BusinessObject businessObject, Object id);
	
	/**
	 * 
	 * 删除数据
	 * 
	 * 
	 * @param businessObject
	 * @param id
	 */
	void removeData(BusinessObject businessObject, Object id);
	
	void saveData(BusinessData businessData);
	
	/**
	 * 
	 * 加载数据
	 * 	
	 * @param businessObject
	 * @param id
	 * @return
	 */
	BusinessData loadData(BusinessObject businessObject, Object id);
	
	/**
	 * 将前端的json 转成 IbusinessData结构
	 * @param jsonObject
	 * @param modelCode
	 * @return
	 */
	BusinessData parseBusinessData(JSONObject jsonObject, String modelCode);
	
	/**
	 * 把businessData转成json格式<br>
	 * 用在BD执行过展示前置脚本后转成json提供给前端
	 * @param businessData
	 */
	JSONObject assemblyFormDefData(BusinessData businessData);
}
