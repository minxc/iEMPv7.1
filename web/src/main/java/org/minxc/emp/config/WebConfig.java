package org.minxc.emp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**

* @Title: WebConfiguration 

* @Package: org.minxc.emp.config 

* @Description:  TODO:(用一句话描述该文件做什么) 

* @author: Xianchang.min  

* @date  2018/8/20 1:35 

* @version V1.0  

*/
@Slf4j
@Configuration
public class WebConfig {

//    @Bean
//    public FilterRegistrationBean springSecurityFilter() {
//        FilterRegistrationBean springSecurityFilterChain = new FilterRegistrationBean();
//        springSecurityFilterChain.addUrlPatterns("/*");
//        springSecurityFilterChain.setFilter(new DelegatingFilterProxy());
//        springSecurityFilterChain.setName("springSecurityFilterChain");
//        return springSecurityFilterChain;
//    }

}
