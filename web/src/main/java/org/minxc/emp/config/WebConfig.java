package org.minxc.emp.config;


import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.collect.Lists;

/**
 * 
 * @Title: WebConfiguration
 * 
 * @Package: org.minxc.emp.config
 * 
 * @Description: TODO:(用一句话描述该文件做什么)
 * 
 * @author: Xianchang.min
 * 
 * @date 2018/8/20 1:35
 * 
 * @version V1.0
 * 
 */
//@EnableWebMvc
//@Configuration
public class WebConfig implements WebMvcConfigurer {

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

//	@Bean
//	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
//		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		jsonConverter.setObjectMapper(objectMapper);
//		return jsonConverter;
//	}

	 	@Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
	                .indentOutput(true)
	                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
	                .modulesToInstall(new ParameterNamesModule());
	        
	        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
	        stringConverter.setDefaultCharset(StandardCharsets.UTF_8);
	        List<MediaType> supportedMediaTypes = Lists.newArrayList();
	        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
	        supportedMediaTypes.add(MediaType.TEXT_HTML);
	        converters.add(stringConverter);
	        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
	    }
}
