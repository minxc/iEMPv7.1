package com.dstz.bus.model;

import com.dstz.bus.api.model.IBusTableRelFk;
import java.io.Serializable;

public class BusTableRelFk implements IBusTableRelFk, Serializable {
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -8146703461596028045L;
	
	/**
	 * <pre>
	 * 业务表对应的映射字段
	 * </pre>
	 * 
	 * @return
	 */
	private String from;
	
	/**
	 * <pre>
	 * 映射的方式 枚举 BusTableRelFkType
	 * </pre>
	 * 
	 * @return
	 */
	private String type;
	
	/**
	 * <pre>
	 * 值
	 * </pre>
	 * 
	 * @return
	 */
	private String value;

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}