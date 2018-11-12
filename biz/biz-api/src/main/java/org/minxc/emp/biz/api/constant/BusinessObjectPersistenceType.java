package org.minxc.emp.biz.api.constant;
/**
 *  
 * BusinessObject中PersistenceType属性的枚举
 * 
 * 
 * 日期:2018年2月26日 下午3:19:05
 * 
 * 
 */
public enum BusinessObjectPersistenceType {
	/**
	 * 数据库
	 */
	DB("db", "数据库"), 
	/**
	 * 实例表
	 */
	INST("inst", "实例表");
	private String key;
	private String desc;

	private BusinessObjectPersistenceType(String key, String desc) {
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
	 * @param key
	 * @return
	 */
	public boolean equalsWithKey(String key) {
		return this.key.equals(key);
	}
}
