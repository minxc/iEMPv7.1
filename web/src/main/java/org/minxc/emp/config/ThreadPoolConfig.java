package org.minxc.emp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**

* @Title: ThreadPoolConfiguration 

* @Package: org.minxc.emp.config 

* @Description:  TODO:(用一句话描述该文件做什么) 

* @author: Xianchang.min  

* @date  2018/8/19 20:33 

* @version V1.0  

*/ 
@Configuration
@Slf4j
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setKeepAliveSeconds(300);
        threadPoolTaskExecutor.setQueueCapacity(30);
        return  threadPoolTaskExecutor;
    }
}
