package com.dstz.bus.service.impl;

import com.dstz.bus.api.constant.BusinessObjectPersistenceType;
import com.dstz.bus.model.BusinessData;
import com.dstz.bus.model.BusinessObject;
import com.dstz.bus.service.BusinessDataPersistenceService;
import org.springframework.stereotype.Service;

@Service
public class BusinessDataPersistenceInstService implements BusinessDataPersistenceService {
	public String type() {
		return BusinessObjectPersistenceType.INST.getKey();
	}

	public void saveData(BusinessData businessData) {
	}

	public BusinessData loadData(BusinessObject businessObject, Object id) {
		return null;
	}

	public void removeData(BusinessObject businessObject, Object id) {
	}
}