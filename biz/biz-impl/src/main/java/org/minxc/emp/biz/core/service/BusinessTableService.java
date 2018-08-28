package org.minxc.emp.biz.core.service;

import org.minxc.emp.biz.core.api.model.IBusinessTable;
import org.minxc.emp.biz.core.api.service.IBusinessTableService;
import org.minxc.emp.biz.core.manager.BusinessTableManager;
import org.minxc.emp.biz.core.model.BusinessTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessTableService implements IBusinessTableService {
	@Autowired
	BusinessTableManager businessTableManager;

	public IBusinessTable getByKey(String key) {
		return this.businessTableManager.getByKey(key);
	}

	public IBusinessTable getFilledByKey(String key) {
		return this.businessTableManager.getFilledByKey(key);
	}
}