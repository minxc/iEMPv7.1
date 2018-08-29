package org.minxc.emp.biz.core.manager;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import org.minxc.emp.biz.core.model.BusinessObject;
import org.minxc.emp.common.manager.Manager;

public interface BusinessObjectManager extends Manager<String, BusinessObject> {
	public BusinessObject getByKey(String key);

	public List<JSONObject> boTreeData(String var1);

	public BusinessObject getFilledByKey(String var1);
}