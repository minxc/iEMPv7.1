package org.minxc.emp.form.api.constant;

/**
 * FormCustDialogConditionField中的valueSource枚举
 */
public enum FormCustomDialogConditionFieldValueSource {
	/**
	 * 页面输入或者url传入
	 */
	PARAM("param", "参数传入", new String[] { FormCustomDialogStyle.LIST.getKey(),FormCustomDialogStyle.TREE.getKey() }),
	/**
	 * 固定值
	 */
	FIXED_VALUE("fixedValue", "固定值", new String[] { FormCustomDialogStyle.LIST.getKey(), FormCustomDialogStyle.TREE.getKey() }),
	/**
	 * 脚本
	 */
	SCRIPT("script", "脚本", new String[] { FormCustomDialogStyle.LIST.getKey(), FormCustomDialogStyle.TREE.getKey() });
	/**
	 * key
	 */
	private String key;
	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 支持的自定义对话框的显示类型
	 */
	private String[] supports;

	private FormCustomDialogConditionFieldValueSource(String key, String desc, String[] supports) {
		this.key = key;
		this.desc = desc;
		this.supports = supports;
	}

	public String getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
	}

	public String[] getSupports() {
		return supports;
	}

	/**
	 * 根据key来判断是否跟当前一致
	 * 
	 * @param key
	 * @return
	 */
	public boolean equalsWithKey(String key) {
		return this.key.equals(key);
	}
}
