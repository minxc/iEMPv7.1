package org.minxc.emp.bpm.api.constant;

/**
 * 流程实例的状态。
 */
public enum InstanceStatus {
	/**
	 * 草稿
	 */
	STATUS_DRAFT("draft", "草稿"),
	/**
	 * 运行中
	 */
	STATUS_RUNNING("running", "运行中"),
	/**
	 * 结束
	 */
	STATUS_END("end", "结束"),
	/**
	 * 人工结束
	 */
	STATUS_MANUAL_END("manualend", "人工结束"),

	STATUS_BACK("back", "驳回"),

	STATUS_UNDEFINED("undefined", "未定义"),

	STATUS_REVOKE("revoke", "撤销");

	// 键
	private String key = "";
	// 值
	private String value = "";

	// 构造方法
	private InstanceStatus(String key, String value) {
		this.key = key;
		this.value = value;
	}

	// =====getting and setting=====
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return key;
	}

	/**
	 * 通过key获取对象
	 *
	 * @param key
	 * @return
	 */
	public static InstanceStatus fromKey(String key) {
		for (InstanceStatus c : InstanceStatus.values()) {
			if (c.getKey().equalsIgnoreCase(key))
				return c;
		}
		throw new IllegalArgumentException(key);
	}

	public static InstanceStatus getByActionName(String actionName) {
		ActionType action = ActionType.fromKey(actionName);

		switch (action) {
		case AGREE:
			return InstanceStatus.STATUS_RUNNING;
		case OPPOSE:
			return InstanceStatus.STATUS_RUNNING;
		case REJECT:
			return InstanceStatus.STATUS_BACK;
		case REJECT2START:
			return InstanceStatus.STATUS_BACK;
		case RECOVER:
			return InstanceStatus.STATUS_REVOKE;
		case TASKOPINION:
			return InstanceStatus.STATUS_RUNNING;
		case MANUALEND:
			return InstanceStatus.STATUS_MANUAL_END;
		default:
			return InstanceStatus.STATUS_RUNNING;
		}
	}

}
