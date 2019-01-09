package com.minxc.emp.ui.idm.application;

import com.minxc.emp.ui.idm.conf.ApplicationConfiguration;
import com.minxc.emp.ui.idm.servlet.AppDispatcherServletConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import({
    ApplicationConfiguration.class,
    AppDispatcherServletConfiguration.class
})
@SpringBootApplication
public class EnterpriseManagementPlatformApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EnterpriseManagementPlatformApplication.class, args);
    }
}
