package org.minxc.emp.basis.api.jms.producer;

import java.util.List;

import org.minxc.emp.basis.api.jms.model.JmsDTO;


public interface JmsProducer {

	void sendToQueue(JmsDTO message);

	void sendToQueue(List<JmsDTO> jmsDto);

}
