package org.minxc.emp.common.db.api.table;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据库类型接口。
 * <pre>
 * 1.设置JdbcTemplate。
 * 2.设置方言。
 *
 * </pre>
 */
public interface DbType {

    /**
     * 设置spring 的JDBCTemplate
     *
     * @param template
     */
    void setJdbcTemplate(JdbcTemplate template);

}
