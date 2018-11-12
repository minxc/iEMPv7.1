package org.minxc.emp.biz.core.service;

import org.minxc.emp.biz.api.model.IBusinessTable;
import org.minxc.emp.biz.api.service.BusinessTableService;
import org.minxc.emp.biz.core.manager.BusinessTableManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessTableServiceImpl implements BusinessTableService {
	
	@Autowired
	private BusinessTableManager businessTableManager;

	public IBusinessTable getByKey(String key) {
		return this.businessTableManager.getByKey(key);
	}

	public IBusinessTable getFilledByKey(String key) {
		return this.businessTableManager.getFilledByKey(key);
	}
}