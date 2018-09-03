package org.minxc.emp.common.db.tableoper;

import org.minxc.emp.common.db.model.table.TableEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import org.minxc.emp.common.db.api.table.DbTypeEnum;

/**
 * TableOperator的工厂类
 *
 */
public class TableOperatorFactory {
	private TableOperatorFactory() {

	}

	/**
	 * <pre>
	 * 构建一个TableOperator
	 * </pre>
	 *
	 * @param type
	 * @param table
	 * @param jdbcTemplate
	 * @return
	 */
	public static TableOperator newOperator(String type, TableEntity<?> table, JdbcTemplate jdbcTemplate) {
		if (DbTypeEnum.MYSQL.equalsWithKey(type)) {
			return new MysqlTableOperator(table, jdbcTemplate);
		}
		throw new RuntimeException("找不到类型[" + type + "]的数据库处理者(TableOperator)");
	}
	
}
