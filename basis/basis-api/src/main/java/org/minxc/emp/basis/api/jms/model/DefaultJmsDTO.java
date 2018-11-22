package org.minxc.emp.basis.api.jms.model;

import java.io.Serializable;

/**
 * 默认的消息
 *
 */
public class DefaultJmsDTO<T extends Serializable> implements JmsDTO<T>{

	private static final long serialVersionUID = 6441512940066825924L;

	private String type;
	
	private T data;
	
	
	public DefaultJmsDTO(String type, T data) {
		this.type = type;
		this.data = data;
	}

	@Override
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
