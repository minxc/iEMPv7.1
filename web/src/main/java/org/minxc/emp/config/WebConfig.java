package org.minxc.emp.config;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.compress.utils.Lists;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**

* @Title: WebConfiguration 

* @Package: org.minxc.emp.config 

* @Description:  TODO:(用一句话描述该文件做什么) 

* @author: Xianchang.min  

* @date  2018/8/20 1:35 

* @version V1.0  

*/
@Configuration
public class WebConfig{

//    @Bean
//    public FilterRegistrationBean springSecurityFilter() {
//        FilterRegistrationBean springSecurityFilterChain = new FilterRegistrationBean();
//        springSecurityFilterChain.addUrlPatterns("/*");
//        springSecurityFilterChain.setFilter(new DelegatingFilterProxy());
//        springSecurityFilterChain.setName("springSecurityFilterChain");
//        return springSecurityFilterChain;
//    }
	
//	@Bean
//	public HttpMessageConverters customConverters() {
//		// 1、需要先定义一个 convert 转换消息的对象;
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//
//        //2、创建Fastjosn对象并设定序列化规则
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//
//        //3、规则赋予转换对象
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//
//        
//        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
//        stringConverter.setDefaultCharset(StandardCharsets.UTF_8);
//        
//        List<MediaType> supportedMediaTypes = Lists.newArrayList();
//        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
//        supportedMediaTypes.add(MediaType.TEXT_HTML);
//		return new HttpMessageConverters(fastConverter, stringConverter);
//	}


}
