package org.minxc.emp.biz.api.service;

import org.minxc.emp.biz.api.model.IBusinessData;
import org.minxc.emp.biz.api.model.IBusinessObject;
import org.minxc.emp.biz.api.model.IBusinessPermission;

import com.alibaba.fastjson.JSONObject;

/**
 * 业务数据服务接口
 */
public interface IBusinessDataService {
	/**
	 * 
	 * 保存 FormDefData 中的data数据
	 * 	
	 * @param data
	 * @param businessPermission
	 */
	void saveFormDefData(JSONObject data,IBusinessPermission businessPermission);

	/**
	 * 
	 * 获取formdefData中的data相关信息
	 * 
	 * 
	 * @param businessObject
	 * @param id
	 * @return
	 */
	JSONObject getFormDefData(IBusinessObject businessObject, Object id);
	
	/**
	 * 
	 * 删除数据
	 * 
	 * 
	 * @param businessObject
	 * @param id
	 */
	void removeData(IBusinessObject businessObject, Object id);
	
	void saveData(IBusinessData businessData);
	
	/**
	 * 
	 * 加载数据
	 * 	
	 * @param businessObject
	 * @param id
	 * @return
	 */
	IBusinessData loadData(IBusinessObject businessObject, Object id);
	
	/**
	 * 将前端的json 转成 IbusinessData结构
	 * @param jsonObject
	 * @param modelCode
	 * @return
	 */
	IBusinessData parseBusinessData(JSONObject jsonObject, String modelCode);
	
	/**
	 * 把businessData转成json格式<br>
	 * 用在BD执行过展示前置脚本后转成json提供给前端
	 * @param data
	 * @param businessData
	 */
	JSONObject assemblyFormDefData(IBusinessData businessData);
}
