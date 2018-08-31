package org.minxc.emp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**

* @Title: RedisConfigProperties 

* @Package: org.minxc.emp.config 

* @Description:  TODO:(用一句话描述该文件做什么) 

* @author: Xianchang.min  

* @date  2018/8/19 20:55 

* @version V1.0  

*/ 
@Component
@ConfigurationProperties(prefix="spring.datasource.hikari")
@Setter
@Getter
public class RedisConfigProperties {
}
