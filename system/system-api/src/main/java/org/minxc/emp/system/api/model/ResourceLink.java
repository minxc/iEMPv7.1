package org.minxc.emp.system.api.model;

/**
 * 
* 项目名称：system-api   
* 类名称：ResourceLink   
* 类  关联资源
* 创建人：Xianchang.min   
* 创建时间：2018年9月4日 下午1:24:45   
* 修改人：Xianchang.min   
* 修改时间：2018年9月4日 下午1:24:45   
* 修改备注：   
* @version  1.0  
*
 */
public interface ResourceLink {

	/**
	 * 返回 主键
	 *
	 */
	public String getId();

	/**
	 * 返回 资源ID
	 *
	 */
	public String getResId();

	/**
	 * 返回 名称
	 *
	 */
	public String getName();

	/**
	 * 返回 资源地址
	 *
	 */
	public String getResUrl();

}
