package com.dstz.bus.util;

import com.dstz.base.db.api.table.DbType;
import com.dstz.base.db.datasource.DataSourceUtil;
import com.dstz.base.db.model.table.Table;
import com.dstz.base.db.tableoper.MysqlTableOperator;
import com.dstz.base.db.tableoper.TableOperator;
import com.dstz.bus.model.BusinessTable;
import org.springframework.jdbc.core.JdbcTemplate;

public class TableOperatorUtil {
	public static TableOperator newOperator(BusinessTable table) {
		JdbcTemplate jdbcTemplate = DataSourceUtil.getJdbcTempByDsAlias((String) table.getDsKey());
		if (jdbcTemplate == null) {
			throw new RuntimeException("当前系统不存在的数据源:" + table.getDsKey());
		}
		String type = DbType.MYSQL.getKey();
		if (DbType.MYSQL.equalsWithKey(type)) {
			return new MysqlTableOperator((Table) table, jdbcTemplate);
		}
		throw new RuntimeException("找不到类型[" + type + "]的数据库处理者(TableOperator)");
	}
}