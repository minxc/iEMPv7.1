package org.minxc.emp.common.db.api.table;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据库类型接口。
 * 
 * 1.设置JdbcTemplate。
 * 2.设置方言。
 *
 * 
 */
public interface DbType {

    /**
     * 设置spring 的JDBCTemplate
     *
     * @param template
     */
    void setJdbcTemplate(JdbcTemplate template);

}
