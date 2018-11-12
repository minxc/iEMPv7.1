package org.minxc.emp.common.db.table.impl.h2;

import org.minxc.emp.common.db.api.table.model.Column;
import org.minxc.emp.common.db.api.table.model.Table;
import org.minxc.emp.common.db.table.BaseTableOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * H2 数据库表操作的实现。
 *
 * 
 * 
 */
public class H2TableOperator extends BaseTableOperator {

    /*
     * 创建表 (non-Javadoc)
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#createTable(org.minxc.emp.common
     * .api.table.model.Table)
     */
    @Override
    public void createTable(Table model) throws SQLException {
        List<Column> columnList = model.getColumnList();
        // 建表语句
        StringBuffer sb = new StringBuffer();
        // 主键字段
        String pkColumn = null;
        // 例注释
        List<String> columnCommentList = new ArrayList<String>();
        // 建表开始
        sb.append("CREATE TABLE " + model.getTableName() + " (\n");
        for (int i = 0; i < columnList.size(); i++) {
            // 建字段
            Column cm = columnList.get(i);
            sb.append("    ").append(cm.getFieldName()).append("    ");
            sb.append(getColumnType(cm.getColumnType(), cm.getCharLen(),
                    cm.getIntLen(), cm.getDecimalLen()));
            sb.append(" ");
            // 主键
            if (cm.getIsPk()) {
                if (pkColumn == null) {
                    pkColumn = cm.getFieldName();
                } else {
                    pkColumn += "," + cm.getFieldName();
                }
            }
            // 添加默认值
            String defVal = getDefaultValueSQL(cm);
            if (StringUtils.isNotEmpty(defVal)) {
                sb.append(defVal);
            }

            // 非空
            if (!cm.getIsNull() || cm.getIsPk()) {
                sb.append(" NOT NULL ");
            }

            // 字段注释
            if (cm.getComment() != null && cm.getComment().length() > 0) {
                // createTableSql.append(" COMMENT '" + cm.getComment() + "'");
                columnCommentList.add("COMMENT ON COLUMN " + model.getTableName()
                        + "." + cm.getFieldName() + " IS '" + cm.getComment()
                        + "'\n");
            }
            sb.append(",\n");
        }
        // 建主键
        if (pkColumn != null) {
            sb.append("    CONSTRAINT PK_").append(model.getTableName())
                    .append(" PRIMARY KEY (").append(pkColumn).append(")");
        } else {
            sb = new StringBuffer(sb.substring(0, sb.length() - ",\n".length()));
        }

        // 建表结束
        sb.append("\n)");
        // 表注释
        jdbcTemplate.execute(sb.toString());
        if (model.getComment() != null && model.getComment().length() > 0) {
            jdbcTemplate.execute("COMMENT ON TABLE " + model.getTableName()
                    + " IS '" + model.getComment() + "'\n");
        }
        for (String columnComment : columnCommentList) {
            jdbcTemplate.execute(columnComment);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#getColumnType(org.minxc.emp.common
     * .api.table.model.Column)
     */
    @Override
    public String getColumnType(Column column) {
        return getColumnType(column.getColumnType(), column.getCharLen(),
                column.getIntLen(), column.getDecimalLen());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#getColumnType(java.lang.String
     * , int, int, int)
     */
    @Override
    public String getColumnType(String columnType, int charLen, int intLen,
                                int decimalLen) {
        if (Column.COLUMN_TYPE_VARCHAR.equals(columnType)) {
            return "VARCHAR(" + charLen + ')';
        } else if (Column.COLUMN_TYPE_NUMBER.equals(columnType)) {
            return "DECIMAL(" + (intLen + decimalLen) + "," + decimalLen + ")";
        } else if (Column.COLUMN_TYPE_DATE.equals(columnType)) {
            return "DATE";
        } else if (Column.COLUMN_TYPE_INT.equals(columnType)) {
            if (intLen > 0 && intLen <= 5) {
                return "SMALLINT";
            } else if (intLen > 5 && intLen <= 10) {
                return "INTEGER";
            } else {
                return "BIGINT";
            }
        } else if (Column.COLUMN_TYPE_CLOB.equals(columnType)) {
            return "CLOB";
        } else {
            return "VARCHAR(50)";
        }
    }

    /*
     *
     * 删除表
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#dropTable(java.lang.String)
     */
    @Override
    public void dropTable(String tableName) throws SQLException {
        String selSql = "" + "SELECT " + "COUNT(*) AMOUNT " + "FROM "
                + "INFORMATION_SCHEMA.TABLES  " + "WHERE "
                + "TABLE_SCHEMA = SCHEMA() " + "AND TABLE_NAME = UPPER('"
                + tableName + "')";
        int rtn = jdbcTemplate.queryForObject(selSql, Integer.class);
        if (rtn > 0) {
            String sql = "DROP TABLE " + tableName;
            jdbcTemplate.execute(sql);
        }

    }

    /*
     * 修改表的注释
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#updateTableComment(java.lang
     * .String, java.lang.String)
     */
    @Override
    public void updateTableComment(String tableName, String comment)
            throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("COMMENT ON TABLE ");
        sb.append(tableName);
        sb.append(" IS '");
        sb.append(comment);
        sb.append("'\n");
        jdbcTemplate.execute(sb.toString());
    }

    /*
     * 添加列
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#addColumn(java.lang.String,
     * org.minxc.emp.common.api.table.model.Column)
     */
    @Override
    public void addColumn(String tableName, Column model) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("ALTER TABLE ").append(tableName);
        sb.append(" ADD ");
        sb.append(model.getFieldName()).append(" ");
        sb.append(getColumnType(model.getColumnType(), model.getCharLen(),
                model.getIntLen(), model.getDecimalLen()));

        // 添加默认值
        // 添加默认值
        String defVal = getDefaultValueSQL(model);
        if (StringUtils.isNotEmpty(defVal)) {
            sb.append(defVal);
        }
        sb.append("\n");
        jdbcTemplate.execute(sb.toString());
        if (model.getComment() != null && model.getComment().length() > 0) {
            jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "."
                    + model.getFieldName() + " IS '" + model.getComment() + "'");
        }

    }

    /*
     * 修改列名
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#updateColumn(java.lang.String,
     * java.lang.String, org.minxc.emp.common.api.table.model.Column)
     */
    @Override
    public void updateColumn(String tableName, String columnName, Column model)
            throws SQLException {
        // 修改列名
        if (!columnName.equals(model.getFieldName())) {
            StringBuffer modifyName = new StringBuffer("ALTER TABLE ")
                    .append(tableName);
            modifyName.append(" ALTER COLUMN ").append(columnName)
                    .append(" RENAME TO ").append(model.getFieldName());
            jdbcTemplate.execute(modifyName.toString());
        }
        StringBuffer sb = new StringBuffer();
        sb.append("ALTER TABLE ").append(tableName);
        sb.append(" ALTER COLUMN ").append(model.getFieldName());
        sb.append(getColumnType(model.getColumnType(), model.getCharLen(),
                model.getIntLen(), model.getDecimalLen()));
        if (!model.getIsNull()) {
            sb.append(" NOT NULL ");
        }
        jdbcTemplate.execute(sb.toString());
        // 修改注释
        if (model.getComment() != null && model.getComment().length() > 0) {
            jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "."
                    + model.getFieldName() + " IS'" + model.getComment() + "'");
        }
    }

    /*
     *
     * 添加外键。
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#addForeignKey(java.lang.String
     * , java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void addForeignKey(String pkTableName, String fkTableName,
                              String pkField, String fkField) {
        String shortTableName = fkTableName.replaceFirst("(?im)W_", "");
        String sql = " ALTER TABLE " + fkTableName + " ADD CONSTRAINT FK_"
                + shortTableName + " FOREIGN KEY (" + fkField + ") REFERENCES "
                + pkTableName + " (" + pkField + ") ON DELETE CASCADE";
        jdbcTemplate.execute(sql);
    }

    /*
     * 刪除外鍵
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#dropForeignKey(java.lang.String
     * , java.lang.String)
     */
    @Override
    public void dropForeignKey(String tableName, String keyName) {
        String sql = "ALTER   TABLE   " + tableName + "   DROP   CONSTRAINT  "
                + keyName;
        jdbcTemplate.execute(sql);
    }

    /*
     * // TODO 原来版本没有 需要测试 (non-Javadoc)
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#getPKColumns(java.lang.String)
     */
    @Override
    public List<String> getPKColumns(String tableName) throws SQLException {
        String sql = "";
        List<String> columns = jdbcTemplate.query(sql, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                String column = rs.getString(1);
                return column;
            }
        });
        return columns;
    }

    /*
     * // TODO 原来版本没有 需要测试 (non-Javadoc)
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#getPKColumns(java.util.List)
     */
    @Override
    public Map<String, List<String>> getPKColumns(List<String> tableNames)
            throws SQLException {
        StringBuffer sb = new StringBuffer();
        for (String name : tableNames) {
            sb.append("'");
            sb.append(name);
            sb.append("',");
        }
        sb.deleteCharAt(sb.length() - 1);

        String sql = "";

        Map<String, List<String>> columnsMap = new HashMap<String, List<String>>();

        List<Map<String, String>> maps = jdbcTemplate.query(sql,
                new RowMapper<Map<String, String>>() {
                    @Override
                    public Map<String, String> mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        String table = rs.getString(1);
                        String column = rs.getString(2);
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("name", table);
                        map.put("column", column);
                        return map;
                    }
                });

        for (Map<String, String> map : maps) {
            if (columnsMap.containsKey(map.get("name"))) {
                columnsMap.get(map.get("name")).add(map.get("column"));
            } else {
                List<String> cols = new ArrayList<String>();
                cols.add(map.get("column"));
                columnsMap.put(map.get("name"), cols);
            }
        }

        return columnsMap;
    }

    /*
     * 检查表是否存在
     *
     * @see
     * org.minxc.emp.common.db.table.BaseTableOperator#isTableExist(java.lang.String)
     */
    @Override
    public boolean isTableExist(String tableName) {
        String sql = "" + "SELECT " + "COUNT(*) AMOUNT " + "FROM "
                + "INFORMATION_SCHEMA.TABLES  " + "WHERE "
                + "TABLE_SCHEMA = SCHEMA() " + "AND TABLE_NAME = UPPER('"
                + tableName + "')";
        return jdbcTemplate.queryForObject(sql, Integer.class) > 0 ? true
                : false;
    }

    /**
     * 获取默认
     *
     * @param cm
     * @return
     */
    private String getDefaultValueSQL(Column cm) {
        String sql = null;
        if (StringUtils.isNotEmpty(cm.getDefaultValue())) {
            if (Column.COLUMN_TYPE_INT.equalsIgnoreCase(cm.getColumnType())) {
                sql = " DEFAULT " + cm.getDefaultValue() + " ";
            } else if (Column.COLUMN_TYPE_NUMBER.equalsIgnoreCase(cm
                    .getColumnType())) {
                sql = " DEFAULT " + cm.getDefaultValue() + " ";
            } else if (Column.COLUMN_TYPE_VARCHAR.equalsIgnoreCase(cm
                    .getColumnType())) {
                sql = " DEFAULT '" + cm.getDefaultValue() + "' ";
            } else if (Column.COLUMN_TYPE_CLOB.equalsIgnoreCase(cm
                    .getColumnType())) {
                sql = " DEFAULT '" + cm.getDefaultValue() + "' ";
            } else if (Column.COLUMN_TYPE_DATE.equalsIgnoreCase(cm
                    .getColumnType())) {
                sql = " DEFAULT " + cm.getDefaultValue() + " ";
            } else {
                sql = " DEFAULT " + cm.getDefaultValue() + " ";
            }
        }
        return sql;
    }

    @Override
    public boolean isExsitPartition(String tableName, String partition) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void createPartition(String tableName, String partition) {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean supportPartition(String tableName) {
        // TODO Auto-generated method stub
        return false;
    }
}
