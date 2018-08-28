package org.minxc.emp.biz.core.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import org.minxc.emp.biz.core.api.model.IBusinessObject;
import org.minxc.emp.biz.core.api.service.IBusinessObjectService;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.model.BusinessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessObjectService implements IBusinessObjectService {
	@Autowired
	BusinessObjectManager k;

	public IBusinessObject getByKey(String key) {
		return this.k.getByKey(key);
	}

	public IBusinessObject getFilledByKey(String key) {
		return this.k.getFilledByKey(key);
	}

	public List<JSONObject> boTreeData(String key) {
		return this.k.boTreeData(key);
	}
}