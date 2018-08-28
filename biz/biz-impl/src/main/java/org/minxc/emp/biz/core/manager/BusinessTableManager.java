package org.minxc.emp.biz.core.manager;

import org.minxc.emp.biz.core.model.BusinessTable;

import com.dstz.base.db.tableoper.TableOperator;
import com.dstz.base.manager.Manager;

public interface BusinessTableManager extends Manager<String, BusinessTable> {
	
	
	void save(BusinessTable businessTable);

	BusinessTable getByKey(String key);

	BusinessTable getFilledByKey(String key);

	TableOperator newTableOperator(BusinessTable businessTable);
}