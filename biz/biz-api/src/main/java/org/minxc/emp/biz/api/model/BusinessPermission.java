package org.minxc.emp.biz.api.model;

import java.util.Map;
import org.minxc.emp.biz.api.model.permission.BusinessObjectPermission;
import com.alibaba.fastjson.JSONObject;

public interface BusinessPermission {

	String getObjectType();

	String getObjectValue();

	Map<String, ? extends BusinessObjectPermission> getBusinessObjectMap();

	BusinessObjectPermission getBusObj(String boKey);
	
	public JSONObject getTablePermission(boolean isReadOnly);
	
	public JSONObject getPermission(boolean isReadOnly) ;
}
