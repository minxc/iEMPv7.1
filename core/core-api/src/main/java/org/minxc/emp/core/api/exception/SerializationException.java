package org.minxc.emp.core.api.exception;

/*
* 项目名称：base-intf   
* 类名称：SerializationException   
* 类序列化异常  
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:48:05   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:48:05   
* 修改备注：   
* @version  1.0  
*
 */
public class SerializationException extends RuntimeException {
	private static final long serialVersionUID = 8847845622247770262L;

	public SerializationException(String msg) {
		super(msg);
	}

	public SerializationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public SerializationException(Throwable throwable) {
		super(throwable);
	}

}
