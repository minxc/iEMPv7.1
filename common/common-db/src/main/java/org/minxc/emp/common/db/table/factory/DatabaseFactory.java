package org.minxc.emp.common.db.table.factory;

import org.minxc.emp.common.db.api.table.IndexOperator;
import org.minxc.emp.common.db.api.table.ITableOperator;
import org.minxc.emp.common.db.api.table.ViewOperator;
import org.minxc.emp.common.db.table.BaseTableMeta;
import org.minxc.emp.common.db.table.impl.db2.DB2IndexOperator;
import org.minxc.emp.common.db.table.impl.db2.DB2TableMeta;
import org.minxc.emp.common.db.table.impl.db2.DB2TableOperator;
import org.minxc.emp.common.db.table.impl.db2.DB2ViewOperator;
import org.minxc.emp.common.db.table.impl.dm.DmIndexOperator;
import org.minxc.emp.common.db.table.impl.dm.DmTableMetadata;
import org.minxc.emp.common.db.table.impl.dm.DmTableOperator;
import org.minxc.emp.common.db.table.impl.dm.DmViewOperator;
import org.minxc.emp.common.db.table.impl.h2.H2IndexOperator;
import org.minxc.emp.common.db.table.impl.h2.H2TableMetadata;
import org.minxc.emp.common.db.table.impl.h2.H2TableOperator;
import org.minxc.emp.common.db.table.impl.h2.H2ViewOperator;
import org.minxc.emp.common.db.table.impl.mysql.MySQLIndexOperator;
import org.minxc.emp.common.db.table.impl.mysql.MySQLTableMetadata;
import org.minxc.emp.common.db.table.impl.mysql.MySQLTableOperator;
import org.minxc.emp.common.db.table.impl.mysql.MySQLViewOperator;
import org.minxc.emp.common.db.table.impl.oracle.OracleIndexOperator;
import org.minxc.emp.common.db.table.impl.oracle.OracleTableMetadata;
import org.minxc.emp.common.db.table.impl.oracle.OracleTableOperator;
import org.minxc.emp.common.db.table.impl.oracle.OracleViewOperator;
import org.minxc.emp.common.db.table.impl.sqlserver.SQLServerIndexOperator;
import org.minxc.emp.common.db.table.impl.sqlserver.SQLServerTableMetadata;
import org.minxc.emp.common.db.table.impl.sqlserver.SQLServerTableOperator;
import org.minxc.emp.common.db.table.impl.sqlserver.SQLServerViewOperator;
import org.minxc.emp.common.db.table.util.SQLConst;

/**
 * 元数据读取工厂。
 */
public class DatabaseFactory {

	public static String EXCEPTION_MSG = "没有设置合适的数据库类型";

	/**
	 * 通过数据库类型获得表操作
	 *
	 * @param dbType 数据库类型
	 * @return
	 * @throws Exception
	 */
	public static ITableOperator getTableOperator(String dbType) throws Exception {
		ITableOperator tableOperator = null;
		if (dbType.equals(SQLConst.DB_ORACLE)) {
			tableOperator = new OracleTableOperator();
		} else if (dbType.equals(SQLConst.DB_MYSQL)) {
			tableOperator = new MySQLTableOperator();
		} else if (dbType.equals(SQLConst.DB_SQLSERVER)) {
			tableOperator = new SQLServerTableOperator();
		} else if (dbType.equals(SQLConst.DB_DB2)) {
			tableOperator = new DB2TableOperator();
		} else if (dbType.equals(SQLConst.DB_H2)) {
			tableOperator = new H2TableOperator();
		} else if (dbType.equals(SQLConst.DB_DM)) {
			tableOperator = new DmTableOperator();
		} else {
			throw new Exception(EXCEPTION_MSG);
		}
		return tableOperator;
	}

	/**
	 * 通过数据库类型获得表元操作
	 *
	 * @param dbType 数据库类型
	 * @return
	 * @throws Exception
	 */
	public static BaseTableMeta getTableMetaByDbType(String dbType) throws Exception {
		BaseTableMeta meta = null;
		if (dbType.equals(SQLConst.DB_ORACLE)) {
			meta = new OracleTableMetadata();
		} else if (dbType.equals(SQLConst.DB_MYSQL)) {
			meta = new MySQLTableMetadata();
		} else if (dbType.equals(SQLConst.DB_SQLSERVER)) {
			meta = new SQLServerTableMetadata();
		} else if (dbType.equals(SQLConst.DB_DB2)) {
			meta = new DB2TableMeta();
		} else if (dbType.equals(SQLConst.DB_H2)) {
			meta = new H2TableMetadata();
		} else if (dbType.equals(SQLConst.DB_DM)) {
			meta = new DmTableMetadata();
		} else {
			throw new Exception(EXCEPTION_MSG);
		}
		return meta;
	}

	/**
	 * 根据数据类型获取 视图操作
	 *
	 * @param dbType
	 * @return
	 * @throws Exception
	 */
	public static IndexOperator getIndexOperator(String dbType) throws Exception {
		IndexOperator indexOperator = null;
		if (dbType.equals(SQLConst.DB_ORACLE)) {
			indexOperator = new OracleIndexOperator();
		} else if (dbType.equals(SQLConst.DB_MYSQL)) {
			indexOperator = new MySQLIndexOperator();
		} else if (dbType.equals(SQLConst.DB_SQLSERVER)) {
			indexOperator = new SQLServerIndexOperator();
		} else if (dbType.equals(SQLConst.DB_DB2)) {
			indexOperator = new DB2IndexOperator();
		} else if (dbType.equals(SQLConst.DB_H2)) {
			indexOperator = new H2IndexOperator();
		} else if (dbType.equals(SQLConst.DB_DM)) {
			indexOperator = new DmIndexOperator();
		} else {
			throw new Exception(EXCEPTION_MSG);
		}
		return indexOperator;

	}

	/**
	 * 根据数据源获取 视图操作
	 *
	 * @param dbType
	 * @return
	 * @throws Exception
	 */
	public static ViewOperator getViewOperator(String dbType) throws Exception {

		ViewOperator view = null;
		if (dbType.equals(SQLConst.DB_ORACLE)) {
			view = new OracleViewOperator();
		} else if (dbType.equals(SQLConst.DB_MYSQL)) {
			view = new MySQLViewOperator();
		} else if (dbType.equals(SQLConst.DB_SQLSERVER)) {
			view = new SQLServerViewOperator();
		} else if (dbType.equals(SQLConst.DB_DB2)) {
			view = new DB2ViewOperator();
		} else if (dbType.equals(SQLConst.DB_H2)) {
			view = new H2ViewOperator();
		} else if (dbType.equals(SQLConst.DB_DM)) {
			view = new DmViewOperator();
		} else {
			throw new Exception(EXCEPTION_MSG);
		}

		return view;
	}

}
