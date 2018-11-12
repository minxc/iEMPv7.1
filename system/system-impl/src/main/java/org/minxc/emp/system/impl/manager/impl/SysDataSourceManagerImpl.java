package org.minxc.emp.system.impl.manager.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.datasource.DataSourceUtil;
import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.api.status.CommonStatusCode;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.system.impl.dao.SysDataSourceDao;
import org.minxc.emp.system.impl.manager.DataSourceManager;
import org.minxc.emp.system.impl.model.SystemDataSourceEntity;
import org.minxc.emp.system.impl.model.def.SysDataSourceDefAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 数据源 Manager处理实现类
 */
@Service
public class SysDataSourceManagerImpl extends CommonManager<String, SystemDataSourceEntity> implements DataSourceManager {
   
	@Autowired
    SysDataSourceDao sysDataSourceDao;

    @Override
    public DataSource tranform2DataSource(SystemDataSourceEntity sysDataSource) {
        try {
            Class<?> cls = Class.forName(sysDataSource.getClassPath());// 拿到类路径
            DataSource dataSource = (DataSource) cls.newInstance();// 初始化一个对象

            // 设置值
            for (SysDataSourceDefAttribute attribute : sysDataSource.getAttributes()) {
                if (StringUtils.isEmpty(attribute.getValue())) {
                    continue;
                }
                Object value = BeanUtils.getValue(attribute.getType(), attribute.getValue());
                BeanUtils.setProperty(dataSource, attribute.getName(), value);
            }
            return dataSource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SystemDataSourceEntity getByKey(String key) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("key_", key, QueryOperator.EQUAL);
        return this.queryOne(filter);
    }

    @Override
    public DataSource getDataSourceByKey(String key, boolean add) {
        // 1从spring配置中取
        Map<String, DataSource> dataSourceMap = DataSourceUtil.getDataSources();
        DataSource dataSource = dataSourceMap.get(key);
        if (dataSource != null) {
            return dataSource;
        }
        // 2 从系统配置中取
        SystemDataSourceEntity sysDataSource = getByKey(key);
        if (sysDataSource != null) {
            dataSource = tranform2DataSource(sysDataSource);
            if (add) {
                DataSourceUtil.addDataSource(key, dataSource, sysDataSource.getDbType(), true);
            }
        }
        throw new BusinessException("在系统中找不到key为[" + key + "]的数据源", CommonStatusCode.SYSTEM_ERROR);
    }

    @Override
    public DataSource getDataSourceByKey(String key) {
        return getDataSourceByKey(key, true);
    }

    @Override
    public JdbcTemplate getJdbcTemplateByKey(String key) {
    	//本地数据源 拿系统配置的jdbc 为了事务保护 哎 处理好jta事务就没这种问题了，nnd
    	if(DataSourceUtil.DEFAULT_DATASOURCE.equals(key)) {
    		return AppContextUtil.getBean(JdbcTemplate.class);
    	}
        return new JdbcTemplate(getDataSourceByKey(key));
    }

}
