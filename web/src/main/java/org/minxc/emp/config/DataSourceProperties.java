package org.minxc.emp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**

* @Title: DataSourceProperties 

* @Package: org.minxc.emp.config 

* @Description:

* @author: Xianchang.min  

* @date  2018/8/19 17:06 

* @version V1.0  

*/

@Component
@ConfigurationProperties(prefix="spring.datasource.hikari")
@Setter
@Getter
public class DataSourceProperties {

    private String driverClassName;
    private String connectionTimeout;
    private String jdbcUrl;
    private String username;
    private String password;
    private int maximumPoolSize;
    private int minimumIdle;
    private String idleTimeout;
    private String validationTimeout;
    private String maxLifetime;
    private int loginTimeout;
    private String poolName;
}
