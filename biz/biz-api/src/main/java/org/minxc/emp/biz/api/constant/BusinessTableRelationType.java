package org.minxc.emp.biz.api.constant;

/**
 * BusTableRel中type属性的枚举
 */
public enum BusinessTableRelationType {
	/**
	 * 主表
	 */
	MAIN("main", "主表"),
	/**
	 * 一对一
	 */
	ONE_TO_ONE("oneToOne", "一对一"),
	/**
	 * 一对多
	 */
	ONE_TO_MANY("oneToMany", "一对多");
	/**
	 * key
	 */
	private String key;
	/**
	 * 描述
	 */
	private String desc;

	private BusinessTableRelationType(String key, String desc) {
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

	/**
	 * 根据key获取
	 * @param key
	 * @return
	 */
	public static BusinessTableRelationType getByKey(String key) {
		for (BusinessTableRelationType type : BusinessTableRelationType.values()) {
			if (key.equals(type.getKey())) {
				return type;
			}
		}
		return null;
	}
}
