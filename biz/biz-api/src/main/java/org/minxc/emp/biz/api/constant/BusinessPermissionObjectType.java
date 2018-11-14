package org.minxc.emp.biz.api.constant;

/**
 * BusinessPermission的objType枚举
 * 日期:2018年4月22日 下午5:54:01
 */
public enum BusinessPermissionObjectType {

	/**
	 * 表单
	 */
	FORM("form", "表单"),
	/**
	 * 流程
	 */
	FLOW("flow", "流程"),
	/**
	 * 流程节点
	 */
	FLOW_NODE("flowNode", "流程节点");
	/**
	 * key
	 */
	private String key;
	/**
	 * 描述
	 */
	private String desc;

	private BusinessPermissionObjectType(String key, String desc) {
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
	 * 根据key获取泛型
	 * 
	 * @param key
	 * @return PermissionType
	 * @exception @since
	 *                1.0.0
	 */
	public static BusinessPermissionObjectType getByKey(String key) {
		for (BusinessPermissionObjectType value : BusinessPermissionObjectType.values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
