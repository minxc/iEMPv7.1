package org.minxc.emp.basis.impl.simplemq;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.minxc.emp.basis.api.jms.consumer.JmsConsumer;
import org.minxc.emp.basis.api.jms.model.JmsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minxc.emp.core.util.AppContextUtil;
import com.minxc.emp.core.util.BeanUtils;

/**
 * 获取消息的具体消费者并交给该处理着处理消息
 */
public class RedisConsumer {
	protected  Logger LOG = LoggerFactory.getLogger(getClass());
	
	 Map<String, JmsConsumer> jmsConsumerMap = null;
	
	 public void receiveMessage(JmsDTO message) {
		 InitConsumer();
		 
		 JmsConsumer consumer = jmsConsumerMap.get(message.getType());
		 if(consumer == null) {
			 LOG.error("JMS消费者没有查找到【{}】类型的处理器! 消息发送时失败",message.getType());
			 return ;
		 }
		 
		 consumer.handlerMessage(message);
	  }

	 
	
	private synchronized void  InitConsumer() {
		if(BeanUtils.isNotEmpty(jmsConsumerMap)) return ;
		
		Map<String, JmsConsumer> jmsConsumerImpls = AppContextUtil.getImplInstance(JmsConsumer.class);
		
		jmsConsumerMap = new HashMap<>();
		for(Entry<String, JmsConsumer> consumer : jmsConsumerImpls.entrySet()) {
			JmsConsumer jmsConsumer = consumer.getValue();
			if(jmsConsumerMap.containsKey(consumer.getValue().getType())) {
				 LOG.error("JMS消费者处理器【{}】初始化失败! 该类型【{}】处理者已经存在！",consumer.getKey(),jmsConsumer.getType());
				 continue;
			}
			
			jmsConsumerMap.put(jmsConsumer.getType(), jmsConsumer);
		}
	}
	
}
