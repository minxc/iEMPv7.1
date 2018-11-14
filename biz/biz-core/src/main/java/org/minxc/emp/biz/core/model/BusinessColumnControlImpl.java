package org.minxc.emp.biz.core.model;

import javax.validation.constraints.NotEmpty;

import org.minxc.emp.biz.api.model.BusinessColumnControl;
import org.minxc.emp.core.impl.model.AbstractCommonModel;

public class BusinessColumnControlImpl extends AbstractCommonModel implements BusinessColumnControl {
	/**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 * 
	 */
	private static final long serialVersionUID = -1717378058777116112L;

	@NotEmpty
	private String columnId;
	@NotEmpty
	private String type;
	private String config;
	private String validRule;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColumnId() {
		return this.columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConfig() {
		return this.config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getValidRule() {
		return this.validRule;
	}

	public void setValidRule(String validRule) {
		this.validRule = validRule;
	}
}