package org.minxc.emp.biz.api.model;

import java.util.Map;
import org.minxc.emp.biz.api.model.permission.IBusObjPermission;
import com.alibaba.fastjson.JSONObject;

public interface IBusinessPermission {

	String getObjType();

	String getObjVal();

	Map<String, ? extends IBusObjPermission> getBusObjMap();

	IBusObjPermission getBusObj(String boKey);
	
	public JSONObject getTablePermission(boolean isReadOnly);
	
	public JSONObject getPermission(boolean isReadOnly) ;
}
