package org.minxc.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

/**

* @Title: WebApplication 

* @Package: org.minxc.emp 

* @Description:  Web入口程序

* @author: Xianchang.min  

* @date  2018/8/17 22:09 

* @version V1.0  
*/


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
public class WebApplication {
    public static void main(String[] args){
        SpringApplication.run(WebApplication.class, args);
    }
}


// 继承实现基于单独的WAR部署方式
//public class EmpUIApplication  extends SpringBootServletInitializer{    war部署方式
//public static void main(String[] args) {
//  SpringApplication.run(EmpUIApplication.class, args);
//}
//}