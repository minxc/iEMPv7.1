package org.minxc.emp.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**

* @Title: DataSourceConfiguration 

* @Package: org.minxc.emp.config 

* @Description:  数据库配置文件

* @author: Xianchang.min  

* @date  2018/8/19 16:55 

* @version V1.0  

*/


@Configuration
public class DataSourceConfiguration  {

    @Autowired
    private DataSourceProperties properties;
    @Bean
    public DataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setPoolName(properties.getPoolName());
        dataSource.setMaximumPoolSize(properties.getMaximumPoolSize());
        dataSource.setMinimumIdle(properties.getMinimumIdle());
        try {
            dataSource.setLoginTimeout(properties.getLoginTimeout());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dataSource.setJdbcUrl(properties.getJdbcUrl());
        return dataSource;
    }


}
