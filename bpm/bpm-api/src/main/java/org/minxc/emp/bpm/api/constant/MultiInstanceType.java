package org.minxc.emp.bpm.api.constant;

/**
 * 多实例类型。 1.单实例。 2.并行。 3.串行。
 */
public enum MultiInstanceType {

	NO("no", "单实例"), PARALLEL("parallel", "并行"), SEQUENTIAL("sequential", "串行");

	// 键
	private String key = "";
	// 值
	private String value = "";

	// 构造方法
	private MultiInstanceType(String key, String value) {
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
	public static MultiInstanceType fromKey(String key) {
		for (MultiInstanceType c : MultiInstanceType.values()) {
			if (c.getKey().equalsIgnoreCase(key))
				return c;
		}
		throw new IllegalArgumentException(key);
	}
}
