package org.minxc.emp.basis.impl.simplemq;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.jms.model.JmsDTO;
import org.minxc.emp.basis.api.jms.producer.JmsProducer;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;



@Service("Service")
public class RedisJmsProducer implements JmsProducer {
	
   @Resource
    private RedisTemplate redisTemplate;
	   
	@Override
	public void sendToQueue(JmsDTO message) {
        redisTemplate.convertAndSend("message", message);
    }

	@Override
	public void sendToQueue(List<JmsDTO> jsmDtos) {
		for(JmsDTO dto : jsmDtos) {
			this.sendToQueue(dto);
		}
	}

}
