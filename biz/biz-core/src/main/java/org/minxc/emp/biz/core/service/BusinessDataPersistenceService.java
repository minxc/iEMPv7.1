package org.minxc.emp.biz.core.service;

import org.minxc.emp.biz.core.model.BusinessData;
import org.minxc.emp.biz.core.model.BusinessObject;

public interface BusinessDataPersistenceService {
	String type();

	void saveData(BusinessData var1);

	BusinessData loadData(BusinessObject var1, Object var2);

	void removeData(BusinessObject var1, Object var2);
}