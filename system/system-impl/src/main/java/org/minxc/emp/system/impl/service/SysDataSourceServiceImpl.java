package org.minxc.emp.system.impl.service;

import org.minxc.emp.system.api.model.SystemDataSource;
import org.minxc.emp.system.api.service.SystemDataSourceService;
import org.minxc.emp.system.impl.manager.DataSourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * 描述：系统数据源对其他模块的服务类
 */

@Service
public class SysDataSourceServiceImpl implements SystemDataSourceService {
    @Autowired
    DataSourceManager sysDataSourceManager;

    @Override
    public SystemDataSource getByKey(String key) {
        return sysDataSourceManager.getByKey(key);
    }

    @Override
    public DataSource getDataSourceByKey(String key) {
        return sysDataSourceManager.getDataSourceByKey(key);
    }

    @Override
    public JdbcTemplate getJdbcTemplateByKey(String key) {
        return sysDataSourceManager.getJdbcTemplateByKey(key);
    }
}
