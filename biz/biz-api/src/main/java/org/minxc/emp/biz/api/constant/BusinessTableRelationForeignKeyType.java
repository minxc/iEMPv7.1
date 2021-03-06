package org.minxc.emp.biz.api.constant;
/**
 *  
 * BusTableRelFkType中type属性的枚举
 *
 */
public enum BusinessTableRelationForeignKeyType {
	/**
	 * 对应父实例的字段
	 */
	PARENT_FIELD("parentField", "子表外键 对应 父实例字段"),
	/**
	 * 固定值
	 */
	FIXED_VALUE("fixedValue", "固定值"),
	/**
	 * 父实例对应字段
	 */
	CHILD_FIELD("childField", "子表字段 对应 父实例外键");

	private String key;
	private String desc;

	private BusinessTableRelationForeignKeyType(String key, String desc) {
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
