package org.minxc.emp.biz.core.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import org.minxc.emp.biz.api.model.BusinessObject;
import org.minxc.emp.biz.api.service.BusinessObjectService;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessObjectServiceImpl implements BusinessObjectService {
	@Autowired
	private BusinessObjectManager businessObjectManager;

	public BusinessObject getByKey(String key) {
		return this.businessObjectManager.getByKey(key);
	}

	public BusinessObject getFilledByKey(String key) {
		return this.businessObjectManager.getFilledByKey(key);
	}

	public List<JSONObject> boTreeData(String key) {
		return this.businessObjectManager.boTreeData(key);
	}
}