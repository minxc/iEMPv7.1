package org.minxc.emp.basis.api.jms.producer;

import java.io.Serializable;
import java.util.List;

import org.minxc.emp.basis.api.jms.model.JmsDTO;

public interface JmsProducer<T extends Serializable> {

	void sendToQueue(JmsDTO<T> message);

	void sendToQueue(List<JmsDTO<T>> jmsDto);

}
