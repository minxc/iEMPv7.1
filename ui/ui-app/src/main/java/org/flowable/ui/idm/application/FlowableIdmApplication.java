package org.flowable.ui.idm.application;

import org.flowable.ui.idm.conf.ApplicationConfiguration;
import org.flowable.ui.idm.servlet.AppDispatcherServletConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import({
    ApplicationConfiguration.class,
    AppDispatcherServletConfiguration.class
})
@SpringBootApplication
public class FlowableIdmApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.out.println("-------------------------");
        SpringApplication.run(FlowableIdmApplication.class, args);
    }
}
