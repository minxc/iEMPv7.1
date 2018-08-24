
/**  

* @Title: SortDirection.java 

* @Package org.minxc.emp.core.constant 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月24日 下午9:17:44 

* @version V1.0  

*/

package org.minxc.emp.core.constant;

/*
 * 
* 项目名称：core-api   
* 类名称：SortDirection   
* 类描述：   数据库查询排序方式
* 创建人：Xianchang.min   
* 创建时间：2018年8月24日 下午9:21:02   
* 修改人：Xianchang.min   
* 修改时间：2018年8月24日 下午9:21:02   
* 修改备注：   
* @version  1.0  
*
 */

public enum SortDirection {

	ASC("ASC", "升序"), DESC("DESC", "降序");

	/**
	 * key
	 */
	private String key;
	/**
	 * 描述
	 */
	private String description;

	private SortDirection(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * <pre>
	 * 根据key来判断是否跟当前一致
	 * </pre>
	 *
	 * @param key
	 * @return
	 */
	public boolean equalsWithKey(String key) {
		return this.key.equals(key);
	}

	public static SortDirection fromString(String value) {
		try {
			return SortDirection.valueOf(value.toUpperCase());
		} catch (Exception e) {
			return ASC;

		}
	}
}
