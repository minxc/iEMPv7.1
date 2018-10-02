package org.minxc.emp.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**

* @Title: MybatisConfiguration 

* @Package: org.minxc.emp.config 

* @Description:  Mybatis配置文件

* @author: Xianchang.min  

* @date  2018/8/19 16:51 

* @version V1.0  

*/ 
@Configuration
@MapperScan("org.minxc.emp.**.dao")
public class MybatisConfiguration{// implements ConfigurationCustomizer{

//    @Resource
//    private DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        return sessionFactory.getObject();
//    }
//
//	@Override
//	public void customize(org.apache.ibatis.session.Configuration configuration) {
//		configuration.
//	}
}
