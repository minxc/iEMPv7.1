package com.dstz.bus.manager;

import com.dstz.base.db.tableoper.TableOperator;
import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusinessTable;

public interface BusinessTableManager extends Manager<String, BusinessTable> {
	
	
	void save(BusinessTable businessTable);

	BusinessTable getByKey(String key);

	BusinessTable getFilledByKey(String key);

	TableOperator newTableOperator(BusinessTable businessTable);
}