package org.minxc.emp.form.api.constant;

/**
 * 
* 项目名称：form-api   
* 类名称：FormCustomDialogObjectType   
* 类描述：   FormCustomDialog中的objType枚举
* 创建人：Xianchang.min   
* 创建时间：2018年10月27日 下午8:36:06   
* 修改人：Xianchang.min   
* 修改时间：2018年10月27日 下午8:36:06   
* 修改备注：   
* @version  1.0  
*
 */
public enum FormCustomDialogObjectType {
	
	
	TABLE("table", "表"), VIEW("view", "视图");
	/**
	 * key
	 */
	private String key;
	/**
	 * 描述
	 */
	private String desc;

	private FormCustomDialogObjectType(String key, String desc) {
		this.key = key;
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
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
}
