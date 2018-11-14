package org.minxc.emp.biz.core.manager;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import org.minxc.emp.biz.core.model.BusinessObjectImpl;
import org.minxc.emp.common.manager.Manager;

public interface BusinessObjectManager extends Manager<String, BusinessObjectImpl> {
	public BusinessObjectImpl getByKey(String key);

	public List<JSONObject> boTreeData(String var1);

	public BusinessObjectImpl getFilledByKey(String var1);
}