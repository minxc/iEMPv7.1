package org.minxc.emp.core.api.query;

import java.util.List;
import java.util.Map;

/**
 * 
* 项目名称：base-intf   
* 类名称:QueryFilter   
* 类描述:组合条件查询过滤
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:54:38   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:54:38   
* 修改备注：   
* @version  1.0  
*
 */

public interface QueryFilter {
	/**
	 * 返回分页信息
	 * @return
	 */
	public Page getPage();
	
	public void setPage(Page page);
	/**
	 * 返回字段组合查询逻辑
	 * @return
	 */
	public FieldLogic getFieldLogic();

	/**
	 * 返回组合的参数映射
	 * @return
	 */
	public Map<String,Object> getParams();
	
	public void addParams(Map<String, Object> params);
	
	/**
	 * 返回字段排序列表
	 * @return
	 */
	public List<FieldSort> getFieldSortList();
	/**
	 * 添加自定义过滤条件（用于自动组装条件：whereSql）
	 * @param name
	 * @param obj
	 * @param queryType
	 */
	public void addFilter(String name, Object obj, QueryOperator queryType);
	
	/**
	 * 添加自定义过滤条件（用于手动组装条件，在MAPPING文件判断用的参数）
	 * @param obj
	 */
	public void addParamsFilter(String key, Object obj);
	/**
	 * 
	 * 增加排序
	 * 
	 * @param orderField
	 * @param orderSeq
	 */
	void addFieldSort(String orderField, String orderSeq);
	
	
	String getWhereSql();
	
	String getOrderBySql();

}
