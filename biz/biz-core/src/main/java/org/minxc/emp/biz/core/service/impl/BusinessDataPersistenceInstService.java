package org.minxc.emp.biz.core.service.impl;

import org.minxc.emp.biz.api.constant.BusinessObjectPersistenceType;
import org.minxc.emp.biz.core.model.BusinessData;
import org.minxc.emp.biz.core.model.BusinessObject;
import org.minxc.emp.biz.core.service.BusinessDataPersistenceService;
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