package com.dstz.bus.service;

import com.dstz.bus.model.BusinessData;
import com.dstz.bus.model.BusinessObject;

public interface BusinessDataPersistenceService {
	String type();

	void saveData(BusinessData var1);

	BusinessData loadData(BusinessObject var1, Object var2);

	void removeData(BusinessObject var1, Object var2);
}