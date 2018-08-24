
/**  

* @Title: BusinessException.java 

* @Package com.minxc.emp.core.impl 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月24日 上午1:42:56 

* @version V1.0  

*/ 

package com.minxc.emp.core.exception;

import org.minxc.emp.core.api.StatusCode;

import com.minxc.emp.core.impl.CommonStatusCode;

/**      
* 项目名称：core-impl   
* 类名称：BusinessException   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月24日 上午1:42:56   
* 修改人：Xianchang.min   
* 修改时间：2018年8月24日 上午1:42:56   
* 修改备注：   
* @version  1.0  
**/

public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = -7289238698048230824L;
	
	public StatusCode statusCode = CommonStatusCode.SYSTEM_ERROR;

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
	}

	public BusinessException(String msg, StatusCode errorCode) {
		super(msg);
		this.statusCode = errorCode;
	}

	public BusinessException(StatusCode errorCode) {
		super(errorCode.getDescription());
		this.statusCode = errorCode;
	}

	public BusinessException(StatusCode errorCode, Throwable throwable) {
		super(errorCode.getDescription(), throwable);
		this.statusCode = errorCode;
	}

	public BusinessException(String msg, StatusCode errorCode, Throwable throwable) {
		super(errorCode.getDescription() + ":" + msg, throwable);
		this.statusCode = errorCode;
	}

	public String getStatuscode() {
		if (statusCode == null)
			return "";
		return statusCode.getCode();
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}
}
