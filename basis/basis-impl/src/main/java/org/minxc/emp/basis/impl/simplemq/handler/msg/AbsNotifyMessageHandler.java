package org.minxc.emp.basis.impl.simplemq.handler.msg;

import java.io.Serializable;

import org.minxc.emp.basis.api.jms.consumer.JmsConsumer;
import org.minxc.emp.basis.api.jms.model.JmsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;



/**
 * 做消息类型的公共逻辑 ：如日志等
 * @param <T>
 */

@Slf4j
public abstract class AbsNotifyMessageHandler<T extends Serializable> implements JmsConsumer<T>{

	
	public abstract String getTitle();

	public boolean getIsDefault() {
		return false;
	}

	public boolean getSupportHtml() {
		return true;
	}
	
	
	 @Override
	 public boolean handlerMessage(JmsDTO<T> message) {
		 // 日志记录一下？？？
		 return  sendMessage(message.getData());
	 }

	 public abstract boolean sendMessage(T data);

}
