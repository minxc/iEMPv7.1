package org.minxc.emp.biz.core.service;

import org.minxc.emp.biz.core.model.BusinessDataImpl;
import org.minxc.emp.biz.core.model.BusinessObjectImpl;

public interface BusinessDataPersistenceService {
	String type();

	void saveData(BusinessDataImpl var1);

	BusinessDataImpl loadData(BusinessObjectImpl var1, Object var2);

	void removeData(BusinessObjectImpl var1, Object var2);
}