package org.minxc.emp.form.model;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.form.api.model.IFormDef;

import com.minxc.emp.core.impl.model.AbstractCommonModel;

/**
 * 描述：表单对象 作者:min.xianchang 邮箱:xianchangmin@126.com 日期:2018年3月16日 下午4:19:26
 */
public class FormDef extends AbstractCommonModel implements IFormDef {
	private static final long serialVersionUID = 8588462586796201414L;

	/**
	 * 表单类型 FormType
	 */
	private String type;
	/**
	 * key
	 */
	@NotEmpty
	private String key;
	/**
	 * 名字
	 */
	@NotEmpty
	private String name;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 分组id
	 */
	private String groupId;
	/**
	 * 分组名称
	 */
	private String groupName;
	/**
	 * 业务对象key
	 */
	private String boKey;
	/**
	 * 业务对象名称
	 */
	private String boName;
	/**
	 * <pre>
	 * 表单内容
	 * </pre>
	 */
	private String html;

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.minxc.emp.form.model.IFormDef#getGroupName()
	 */
	@Override
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.minxc.emp.form.model.IFormDef#getBoKey()
	 */
	@Override
	public String getBoKey() {
		return boKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.minxc.emp.form.model.IFormDef#setBoKey(java.lang.String)
	 */
	@Override
	public void setBoKey(String boKey) {
		this.boKey = boKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.minxc.emp.form.model.IFormDef#getBoName()
	 */
	@Override
	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	@Override
	public String getHtml() {
		if (StringUtils.isNotEmpty(html)) {
			String content = html.replaceAll("&apos;", "'").replaceAll("&#39;", "'").replaceAll("#ctx#", "ctx");
			return content;
		} // &#39;

		return html;
	}

	@Override
	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

}
