package org.minxc.emp.biz.core.util;


import org.minxc.emp.biz.core.model.BusinessTableImpl;
import org.minxc.emp.common.db.api.table.DbTypeEnum;
import org.minxc.emp.common.db.api.table.ITableOperator;
import org.minxc.emp.common.db.datasource.DataSourceUtil;
import org.minxc.emp.common.db.tableoper.MysqlTableOperator;
import org.springframework.jdbc.core.JdbcTemplate;

public class TableOperatorUtil {
	public static ITableOperator newOperator(BusinessTableImpl table) {
		JdbcTemplate jdbcTemplate = DataSourceUtil.getJdbcTempByDsAlias((String) table.getDsKey());
		if (jdbcTemplate == null) {
			throw new RuntimeException("当前系统不存在的数据源:" + table.getDsKey());
		}
		String type = DbTypeEnum.MYSQL.getKey();
		if (DbTypeEnum.MYSQL.equalsWithKey(type)) {
			return (ITableOperator) new MysqlTableOperator(table, jdbcTemplate);
		}
		throw new RuntimeException("找不到类型[" + type + "]的数据库处理者(TableOperator)");
	}
}