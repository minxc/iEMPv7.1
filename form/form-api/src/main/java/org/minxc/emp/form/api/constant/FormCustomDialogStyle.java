package org.minxc.emp.form.api.constant;
/**
 *  
 * FormCustDialog中的style枚举
 * 
 * 
 * 日期:2018年3月1日 下午4:24:48
 * 
 * 
 */
public enum FormCustomDialogStyle {
	LIST("list", "列表"), TREE("tree", "树形");
	/**
	 * key
	 */
	private String key;
	/**
	 * 描述
	 */
	private String desc;

	private FormCustomDialogStyle(String key, String desc) {
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
	 * 
	 * 根据key来判断是否跟当前一致
	 * 
	 * 
	 * @param key
	 * @return
	 */
	public boolean equalsWithKey(String key) {
		return this.key.equals(key);
	}
}
