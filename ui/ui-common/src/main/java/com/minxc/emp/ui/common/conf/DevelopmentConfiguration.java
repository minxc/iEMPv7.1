package com.minxc.emp.ui.common.conf;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile({"dev"})
public class DevelopmentConfiguration {

    protected static final String DATASOURCE_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    protected static final String DATASOURCE_URL = "jdbc:mysql://127.0.0.1:3306/flowable?characterEncoding=UTF-8";
    protected static final String DATASOURCE_USERNAME = "flowable";
    protected static final String DATASOURCE_PASSWORD = "flowable";

    @Bean
    @Primary
    public DataSource developmentDataSource() {
        return DataSourceBuilder
            .create()
            .driverClassName(DATASOURCE_DRIVER_CLASS_NAME)
            .url(DATASOURCE_URL)
            .username(DATASOURCE_USERNAME)
            .password(DATASOURCE_PASSWORD)
            .build();
    }

}