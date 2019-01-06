package org.minxc.emp.config;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.minxc.emp.common.db.dao.baseinterceptor.QueryInterceptor;
import org.minxc.emp.common.db.dao.baseinterceptor.SaveInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

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
@EnableTransactionManagement
@MapperScan("org.minxc.emp.**.dao")
public class MybatisConfiguration implements TransactionManagementConfigurer {

    @Resource
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        Interceptor[] interceptors = new Interceptor[] {new QueryInterceptor(), new SaveInterceptor()};
        
        sessionFactoryBean.setPlugins(interceptors);
        sessionFactoryBean.setTypeAliasesPackage("org.minxc.emp.**.model");
        
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        config.setDefaultFetchSize(100);
        config.setDefaultStatementTimeout(30);
        sessionFactoryBean.setConfiguration(config);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
        	sessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:org/minxc/emp/**/*.xml"));
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		 return new DataSourceTransactionManager(dataSource);
	}
}
