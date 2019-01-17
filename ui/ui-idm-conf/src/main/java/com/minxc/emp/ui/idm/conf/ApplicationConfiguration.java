package com.minxc.emp.ui.idm.conf;

import com.minxc.emp.ui.idm.servlet.ApiDispatcherServletConfiguration;
import com.minxc.emp.ui.common.service.idm.RemoteIdmServiceImpl;
import com.minxc.emp.ui.idm.properties.EnterpriseManagementPlatformIdmAppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@EnableConfigurationProperties(EnterpriseManagementPlatformIdmAppProperties.class)
@ComponentScan(basePackages = {
    "com.minxc.emp.ui.common.conf",
    "com.minxc.emp.ui.idm.conf",
    "com.minxc.emp.ui.idm.security",
    "com.minxc.emp.ui.idm",
    "com.minxc.emp.ui.idm.service"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = RemoteIdmServiceImpl.class)})
public class ApplicationConfiguration {

    @Bean
    public ServletRegistrationBean apiServlet(ApplicationContext applicationContext) {
        AnnotationConfigWebApplicationContext dispatcherServletConfiguration = new AnnotationConfigWebApplicationContext();
        dispatcherServletConfiguration.setParent(applicationContext);
        dispatcherServletConfiguration.register(ApiDispatcherServletConfiguration.class);
        DispatcherServlet servlet = new DispatcherServlet(dispatcherServletConfiguration);
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet, "/api/*");
        registrationBean.setName("Enterprise Management Platform IDM App API Servlet");
        registrationBean.setLoadOnStartup(1);
        registrationBean.setAsyncSupported(true);
        return registrationBean;
    }

}
