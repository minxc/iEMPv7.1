package org.minxc.emp.system.rest.listener;

import org.minxc.emp.common.db.api.table.DbTypeEnum;
import org.minxc.emp.common.db.datasource.DataSourceUtil;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.system.impl.manager.DataSourceManager;
import org.minxc.emp.system.impl.model.SystemDataSourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 在spring容器启动时加载数据源：
 * 从spring文件中做加载。 扫描系统spring 配置中数据源动态加入到系统的dataSourceMap数据源中，
 */
@Slf4j
@Component
public class DataSourceInitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DataSourceManager sysDataSourceManager;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent ev) {
        // 防止重复执行。
        if (ev.getApplicationContext().getParent() != null)
            return;

        ApplicationContext context = ev.getApplicationContext();
        // 加载配置文件中的数据源然后放到dynamicDataSource中，然后增加到系统数据中
        loadDataSourceFromFile(context);
        // 加载系统数据源到dynamicDataSource中
        loadDataSourceFromSysDataSource();
    }

    /**
     * 
     * 加载系统数据源到dynamicDataSource中
     * 
     */
    private void loadDataSourceFromSysDataSource() {
        for (SystemDataSourceEntity sysDataSource : sysDataSourceManager.getAll()) {
            if (DataSourceUtil.isDataSourceExist(sysDataSource.getKey())) {
                continue;
            }
            DataSourceUtil.addDataSource(sysDataSource.getKey(), sysDataSourceManager.tranform2DataSource(sysDataSource), sysDataSource.getDbType(), false);
            log.debug("add datasource " + sysDataSource.getKey());
        }
    }

    /**
     * 加载配置文件中的数据源然后放到dynamicDataSource中，然后增加到系统数据中
     *
     * @param context void
     */
    void loadDataSourceFromFile(ApplicationContext context) {
        Map<String, DataSource> mapDataSource = context.getBeansOfType(DataSource.class);
        for (Entry<String, DataSource> entry : mapDataSource.entrySet()) {
            // 本地数据源不需要再次增加进去
            if (entry.getKey().equals(DataSourceUtil.GLOBAL_DATASOURCE) || entry.getKey().equals(DataSourceUtil.DEFAULT_DATASOURCE)) {
                continue;
            }
            String dbType = getDbType(entry.getValue());
            DataSourceUtil.addDataSource(entry.getKey(), entry.getValue(), dbType, false);
            log.debug("add datasource " + entry.getKey());
            // 将其新增到系统配置的数据源中，供客户使用
            if (sysDataSourceManager.getByKey(entry.getKey()) == null) {
                SystemDataSourceEntity sysDataSource = new SystemDataSourceEntity();
                sysDataSource.setKey(entry.getKey());
                sysDataSource.setName(entry.getKey() + "数据源");
                sysDataSource.setId(UniqueIdUtil.getSuid());
                sysDataSource.setDbType(dbType);
                sysDataSourceManager.create(sysDataSource);
            }
        }
    }

    /**
     * 
     * 根据数据源获取数据库类型
     * 
     *
     * @param dataSource
     * @return
     */
    private String getDbType(DataSource dataSource) {
        try {
            Class<?> cls = dataSource.getClass();
            Method getDriverClassNameMethod = cls.getDeclaredMethod("getDriverClassName");
            String driverClassName = (String) getDriverClassNameMethod.invoke(dataSource);
            for (DbTypeEnum dbType : DbTypeEnum.values()) {
                if (driverClassName.contains(dbType.getKey())) {
                    return dbType.getKey();
                }
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

}
