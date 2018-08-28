package org.minxc.emp.bpm.engine.constant;

public enum TaskSkipType {
	NO_SKIP("noSkip", "不跳过"), ALL_SKIP("allSkip", "所有节点跳过"), FIRSTNODE_SKIP("firstNodeSkip", "开始节点跳过"),
	SAME_USER_SKIP("sameUserSkip", "前一节点相同用户则跳过"), USER_EMPTY_SKIP("userEmptySkip", "执行人为空则跳过"),
	SCRIPT_SKIP("scriptSkip", "脚本跳过");

	private String key = "";
	private String value = "";

	private TaskSkipType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return this.key;
	}

	public static TaskSkipType fromKey(String key) {
		for (TaskSkipType c : TaskSkipType.values()) {
			if (!c.getKey().equalsIgnoreCase(key))
				continue;
			return c;
		}
		throw new IllegalArgumentException(key);
	}
}