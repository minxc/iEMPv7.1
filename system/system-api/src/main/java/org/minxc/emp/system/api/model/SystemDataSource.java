package org.minxc.emp.system.api.model;


/**
 * 系统数据源
 * 日期:2018年1月29日 上午10:21:25
 */
public interface SystemDataSource {
	
    /**
     * 数据源的别名
     *
     * @return
     */
    public String getKey();

    /**
     * 名字
     *
     * @return
     */
    public String getName();

    /**
     * 描述
     *
     * @return
     */
    public String getDesc();

    /**
     * 
     * 数据库类型 枚举在com.dstz.base.api.db.DbType 的key
     * 
     *
     * @return
     */
    public String getDbType();

}
