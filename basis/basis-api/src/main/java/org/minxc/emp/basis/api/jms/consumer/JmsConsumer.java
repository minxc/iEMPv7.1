package org.minxc.emp.basis.api.jms.consumer;

import java.io.Serializable;

import org.minxc.emp.basis.api.jms.model.JmsDTO;


/**
 * 所有消费者 均需要实现该接口
 * 通过type 获取具体的处理者
 */
public interface JmsConsumer<T extends Serializable> {
	 String getType();
	 boolean handlerMessage(JmsDTO<T> message);
}
