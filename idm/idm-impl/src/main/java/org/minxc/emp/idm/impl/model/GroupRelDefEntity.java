package org.minxc.emp.idm.impl.model;

import com.minxc.emp.core.impl.model.AbstractCommonModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 组织关系定义
 */
public class GroupRelDefEntity extends AbstractCommonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8687697768301929010L;

	/**
	 * id_
	 */
	protected String id;

	/**
	 * 名称
	 */
	protected String name;

	/**
	 * 编码
	 */
	protected String code;

	/**
	 * 职务级别
	 */
	protected String postLevel;

	/**
	 * 描述
	 */
	protected String description;

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回 id_
	 *
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 名称
	 *
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 返回 编码
	 *
	 * @return
	 */
	public String getCode() {
		return this.code;
	}

	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}

	/**
	 * 返回 职务级别
	 *
	 * @return
	 */
	public String getPostLevel() {
		return this.postLevel;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 返回 描述
	 *
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("code", this.code)
				.append("postLevel", this.postLevel).append("description", this.description).toString();
	}
}