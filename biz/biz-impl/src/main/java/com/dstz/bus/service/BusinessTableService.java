package com.dstz.bus.service;

import com.dstz.bus.api.model.IBusinessTable;
import com.dstz.bus.api.service.IBusinessTableService;
import com.dstz.bus.manager.BusinessTableManager;
import com.dstz.bus.model.BusinessTable;
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