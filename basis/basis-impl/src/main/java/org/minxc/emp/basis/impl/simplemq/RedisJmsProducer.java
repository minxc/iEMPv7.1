package org.minxc.emp.basis.impl.simplemq;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.jms.model.JmsDTO;
import org.minxc.emp.basis.api.jms.producer.JmsProducer;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;

@Service("Service")
public class RedisJmsProducer<K,V extends Serializable> implements JmsProducer<V> {
	
   @Resource
    private RedisTemplate<K, V> redisTemplate;
	   
	@Override
	public void sendToQueue(JmsDTO<V> message) {
        redisTemplate.convertAndSend("message", message);
    }

	@Override
	public void sendToQueue(List<JmsDTO<V>> jsmDtos) {
		for(JmsDTO<V> dto : jsmDtos) {
			this.sendToQueue(dto);
		}
	}
}
