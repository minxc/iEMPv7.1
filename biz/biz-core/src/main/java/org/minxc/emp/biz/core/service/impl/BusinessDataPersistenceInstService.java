package org.minxc.emp.biz.core.service.impl;

import org.minxc.emp.biz.api.constant.BusinessObjectPersistenceType;
import org.minxc.emp.biz.core.model.BusinessDataImpl;
import org.minxc.emp.biz.core.model.BusinessObjectImpl;
import org.minxc.emp.biz.core.service.BusinessDataPersistenceService;
import org.springframework.stereotype.Service;

@Service
public class BusinessDataPersistenceInstService implements BusinessDataPersistenceService {
	public String type() {
		return BusinessObjectPersistenceType.INST.getKey();
	}

	public void saveData(BusinessDataImpl businessData) {
	}

	public BusinessDataImpl loadData(BusinessObjectImpl businessObject, Object id) {
		return null;
	}

	public void removeData(BusinessObjectImpl businessObject, Object id) {
	}
}