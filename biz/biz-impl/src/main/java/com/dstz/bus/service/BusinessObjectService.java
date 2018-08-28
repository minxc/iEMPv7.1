package com.dstz.bus.service;

import com.alibaba.fastjson.JSONObject;
import com.dstz.bus.api.model.IBusinessObject;
import com.dstz.bus.api.service.IBusinessObjectService;
import com.dstz.bus.manager.BusinessObjectManager;
import com.dstz.bus.model.BusinessObject;
import java.util.List;
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