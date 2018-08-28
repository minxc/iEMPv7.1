package com.dstz.bus.manager;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusinessObject;
import java.util.List;

public interface BusinessObjectManager extends Manager<String, BusinessObject> {
	public BusinessObject getByKey(String key);

	public List<JSONObject> boTreeData(String var1);

	public BusinessObject getFilledByKey(String var1);
}