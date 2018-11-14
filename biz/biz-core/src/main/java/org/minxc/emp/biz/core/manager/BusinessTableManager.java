package org.minxc.emp.biz.core.manager;

import org.minxc.emp.biz.core.model.BusinessTableImpl;
import org.minxc.emp.common.db.tableoper.TableOperator;
import org.minxc.emp.common.manager.Manager;


public interface BusinessTableManager extends Manager<String, BusinessTableImpl> {
	
	
	void save(BusinessTableImpl businessTable);

	BusinessTableImpl getByKey(String key);

	BusinessTableImpl getFilledByKey(String key);

	TableOperator newTableOperator(BusinessTableImpl businessTable);
}