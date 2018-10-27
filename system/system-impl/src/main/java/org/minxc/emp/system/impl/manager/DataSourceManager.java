package org.minxc.emp.system.impl.manager;

import javax.sql.DataSource;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.SystemDataSourceEntity;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据源 Manager处理接口
 */
public interface DataSourceManager extends Manager<String, SystemDataSourceEntity> {

    /**
     * <pre>
     * 把sysDataSource转换成javax.sql.DataSource
     * </pre>
     *
     * @param sysDataSource
     * @return
     */
    DataSource tranform2DataSource(SystemDataSourceEntity sysDataSource);

    /**
     * <pre>
     * 根据key获取对象
     * </pre>
     *
     * @param key
     * @return
     */
    SystemDataSourceEntity getByKey(String key);

    /**
     * <pre>
     * 根据数据库别名获取 javax.sql.DataSource
     * 1 先从spring的容器数据源（DynamicDataSource）中拿
     * 2 再从系统配置的数据源(sysDataSource)中拿
     * ps：所以，如果你在spring配置文件中配置的数据源别名跟系统配置的别名一样，会优先取spring配置文件中的
     * </pre>
     *
     * @param key
     * @param add 当数据源是从系统中取出来的，是否追加到spring容器数据源中
     * @return
     */
    DataSource getDataSourceByKey(String key, boolean add);

    /**
     * <pre>
     * 根据数据库别名获取 javax.sql.DataSource
     * 1 先从spring的容器数据源（DynamicDataSource）中拿
     * 2 再从系统配置的数据源(sysDataSource)中拿，当数据源是从系统中取出来的时，追加到spring容器数据源中
     * ps：所以，如果你在spring配置文件中配置的数据源别名跟系统配置的别名一样，会优先取spring配置文件中的
     * </pre>
     *
     * @param key
     * @return
     */
    DataSource getDataSourceByKey(String key);

    /**
     * <pre>
     * 根据数据库别名获取 javax.sql.JdbcTemplate
     * 1 先从spring的容器数据源（DynamicDataSource）中拿
     * 2 再从系统配置的数据源(sysDataSource)中拿，当数据源是从系统中取出来的时，追加到spring容器数据源中
     * 3 把拿到的数据源new一个JdbcTemplate
     * ps：所以，如果你在spring配置文件中配置的数据源别名跟系统配置的别名一样，会优先取spring配置文件中的
     * </pre>
     *
     * @param key
     * @return
     */
    JdbcTemplate getJdbcTemplateByKey(String key);

}
