package org.minxc.emp.common.db.api;

/**
 * 
* 项目名称：common-db   
* 类名称：IdGenerator   
* 类 ID主键产生器  
* 创建人：Xianchang.min   
* 创建时间：2018年9月3日 下午7:51:39   
* 修改人：Xianchang.min   
* 修改时间：2018年9月3日 下午7:51:39   
* 修改备注：   
* @version  1.0  
*
 */
public interface IdGenerator {

	/**
	 * 获取唯一主键ID
	 *
	 * @return 长整型
	 */
	public Long getUId();

	/**
	 * 获取
	 *
	 * @return
	 */
	public String getSuid();
}
