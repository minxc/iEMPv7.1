package org.minxc.emp.common.db.table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import org.minxc.emp.common.db.api.table.DbType;

public class BaseDbType implements DbType {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    // JdbcTemplate
    protected JdbcTemplate jdbcTemplate;


    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
