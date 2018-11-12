package org.minxc.emp.common.db.table.impl.oracle;

import org.minxc.emp.common.db.api.table.ViewOperator;
import org.minxc.emp.common.db.api.table.model.Column;
import org.minxc.emp.common.db.api.table.model.Table;
import org.minxc.emp.common.db.table.BaseViewOperator;
import org.minxc.emp.common.db.table.colmap.OracleColumnMap;
import org.minxc.emp.common.db.table.model.DefaultTable;
import org.minxc.emp.common.db.table.util.SQLConst;
import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.core.api.query.Page;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Oracle 视图操作的实现类。
 *
 * 
 * 
 */
public class OracleViewOperator extends BaseViewOperator implements
        ViewOperator {

    // private static final String
    // sqlAllView="select view_name from user_views ";
//	private static final String sqlAllView = "SELECT TABLE_NAME FROM information_schema.`TABLES` WHERE TABLE_TYPE LIKE 'VIEW'";
    private static final String sqlAllView = "SELECT USER_VIEWS.VIEW_NAME FROM USER_VIEWS";
    // private static final String SQL_GET_COLUMNS =
    // "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH,"
    // +
    // "NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT "
    // +
    // " FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='%s' ";
    private static final String SQL_GET_COLUMNS_BATCH = "SELECT"
            + " TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH,"
            + " NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT "
            + " FROM" + " INFORMATION_SCHEMA.COLUMNS "
            + " WHERE TABLE_SCHEMA=DATABASE() ";

    private static final String DB_TYPE = SQLConst.DB_ORACLE;

    /*
     * (non-Javadoc)
     *
     * @see org.minxc.emp.common.api.db.IViewOperator#createOrRep(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void createOrRep(String viewName, String sql) throws Exception {
        String getSql = "CREATE OR REPLACE VIEW " + viewName + " as (" + sql
                + ")";
        jdbcTemplate.execute(getSql);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.minxc.emp.common.api.db.IViewOperator#getViews(java.lang.String)
     */
    @Override
    public List<String> getViews(String viewName) throws SQLException {
        String sql = sqlAllView;
        if (StringUtils.isNotEmpty(viewName)) {
//			sql += " AND TABLE_NAME LIKE '" + viewName + "%'";
            sql += "WHERE USER_VIEWS.VIEW_NAME LIKE " + "'" + viewName + "%'";
        }
        return this.jdbcTemplate.queryForList(sql, String.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.minxc.emp.common.api.db.IViewOperator#getViews(java.lang.String,
     * org.minxc.emp.common.api.Page)
     */
    @Override
    public List<String> getViews(String viewName, Page page)
            throws SQLException, Exception {
        String sql = sqlAllView;
        if (StringUtils.isNotEmpty(viewName)) {
//			sql += " AND TABLE_NAME LIKE '" + viewName + "%'";
            sql += "WHERE USER_VIEWS.VIEW_NAME LIKE " + "'" + viewName + "%'";
        }
        return super.getForList(sql, page, String.class, DB_TYPE);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.minxc.emp.common.api.db.IViewOperator#getViewsByName(java.lang.String,
     * org.minxc.emp.common.api.Page)
     */
    @Override
    public List<Table> getViewsByName(String viewName, Page page)
            throws Exception {
        String sql = sqlAllView;
        if (StringUtils.isNotEmpty(viewName)) {
//			sql += " AND TABLE_NAME LIKE '" + viewName + "%'";
            sql += "WHERE USER_VIEWS.VIEW_NAME LIKE " + "'" + viewName + "%'";
        }

        RowMapper<Table> rowMapper = new RowMapper<Table>() {
            @Override
            public Table mapRow(ResultSet rs, int row) throws SQLException {
                Table table = new DefaultTable();
                table.setTableName(rs.getString("table_name"));
                table.setComment(table.getTableName());
                return table;
            }
        };
        List<Table> tableModels = getForList(sql, page, rowMapper, DB_TYPE);

        List<String> tableNames = new ArrayList<String>();
        // get all table names
        for (Table table : tableModels) {
            tableNames.add(table.getTableName());
        }
        // batch get table columns
        Map<String, List<Column>> tableColumnsMap = getColumnsByTableName(tableNames);
        // extract table columns from paraTypeMap by table name;
        for (Entry<String, List<Column>> entry : tableColumnsMap.entrySet()) {
            // set TableModel's columns
            for (Table table : tableModels) {
                if (table.getTableName().equalsIgnoreCase(entry.getKey())) {
                    table.setColumnList(entry.getValue());
                }
            }
        }
        return tableModels;
    }

    /**
     * 根据表名获取列。此方法使用批量查询方式。
     *
     * @param tableNames
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, List<Column>> getColumnsByTableName(
            List<String> tableNames) {
        String sql = SQL_GET_COLUMNS_BATCH;
        Map<String, List<Column>> map = new HashMap<String, List<Column>>();
        if (tableNames != null && tableNames.size() == 0) {
            return map;
        } else {
            StringBuffer buf = new StringBuffer();
            for (String str : tableNames) {
                buf.append("'" + str + "',");
            }
            buf.deleteCharAt(buf.length() - 1);
            sql += " AND TABLE_NAME IN (" + buf.toString() + ") ";
        }
        // jdbcHelper.setCurrentDb(currentDb);

        List<Column> columns = jdbcTemplate.query(sql, new OracleColumnMap());
        for (Column column : columns) {
            String tableName = column.getTableName();
            if (map.containsKey(tableName)) {
                map.get(tableName).add(column);
            } else {
                List<Column> cols = new ArrayList<Column>();
                cols.add(column);
                map.put(tableName, cols);
            }
        }
        return map;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.minxc.emp.common.db.table.BaseViewOperator#getType(java.lang.String)
     */
    @Override
    public String getType(String type) {
        type = type.toLowerCase();
        if (type.indexOf("number") > -1)
            return Column.COLUMN_TYPE_NUMBER;
        else if (type.indexOf("date") > -1) {
            return Column.COLUMN_TYPE_DATE;
        } else if (type.indexOf("char") > -1) {
            return Column.COLUMN_TYPE_VARCHAR;
        }
        return Column.COLUMN_TYPE_VARCHAR;
    }

}
