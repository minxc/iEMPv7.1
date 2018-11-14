package org.minxc.emp.biz.core.model;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.biz.api.constant.RightsType;
import org.minxc.emp.biz.api.model.BusinessPermission;
import org.minxc.emp.biz.api.model.permission.BusinessColumnPermission;
import org.minxc.emp.biz.api.model.permission.BusinessObjectPermission;
import org.minxc.emp.biz.api.model.permission.BusinessTablePermission;
import org.minxc.emp.biz.core.model.permission.BusinessObjectPermissionImpl;
import org.minxc.emp.core.impl.model.AbstractCommonModel;
import org.minxc.emp.core.util.JsonUtil;

public class BusinessPermissionImpl extends AbstractCommonModel implements BusinessPermission {
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 3626757861344616318L;
	
	
	private String objType;
	private String objVal;
	private String busObjMapJson;
	private Map<String, BusinessObjectPermissionImpl> busObjMap = new HashMap<String, BusinessObjectPermissionImpl>();
	private JSONObject K;
	private JSONObject L = null;

	public String getObjectType() {
		return this.objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public String getObjectValue() {
		return this.objVal;
	}

	public void setObjVal(String objVal) {
		this.objVal = objVal;
	}

	public String getBusObjMapJson() {
		return this.busObjMapJson;
	}

	public void setBusObjMapJson(String busObjMapJson) {
		this.busObjMapJson = busObjMapJson;
		if (StringUtils.isEmpty(busObjMapJson)) {
			this.busObjMap = null;
			return;
		}
		this.busObjMap = new HashMap<String, BusinessObjectPermission>();
		Map<String, String> map = JSONObject.parseObject(busObjMapJson, Map.class);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			this.busObjMap.put(entry.getKey(), JSONObject.parseObject(entry.getValue(), BusinessObjectPermission.class));
		}
	}

	public Map<String, BusinessObjectPermission> getBusinessObjectMap() {
		return this.busObjMap;
	}

	public void setBusObjMap(Map<String, BusinessObjectPermission> busObjMap) {
		this.busObjMap = busObjMap;
		this.busObjMapJson = JsonUtil.toJSONString(busObjMap);
	}

	public BusinessObjectPermissionImpl c(String boKey) {
		return this.busObjMap.get(boKey);
	}

	public JSONObject getTablePermission(boolean isReadonly) {
		this.a(isReadonly);
		return this.K;
	}

	public JSONObject getPermission(boolean isReadonly) {
		this.a(isReadonly);
		return this.L;
	}

	private synchronized void a(Boolean isReadonly) {
		if (this.L != null) {
			return;
		}
		this.K = new JSONObject();
		this.L = new JSONObject();
		for (Map.Entry<String, BusinessObjectPermission> entry : this.getBusinessObjectMap().entrySet()) {
			BusinessObjectPermission busObjPermission = (BusinessObjectPermission) entry.getValue();
			this.L.put(busObjPermission.getKey(), (Object) new JSONObject());
			this.K.put(busObjPermission.getKey(), (Object) new JSONObject());
			for (Map.Entry etry : busObjPermission.getTableMap().entrySet()) {
				BusinessTablePermission busTablePermission = (BusinessTablePermission) etry.getValue();
				this.L.getJSONObject(busObjPermission.getKey()).put(busTablePermission.getKey(),
						(Object) new JSONObject());
				this.K.getJSONObject(busObjPermission.getKey()).put(busTablePermission.getKey(),
						(Object) this.a(busTablePermission.getResult(), isReadonly));
				for (Map.Entry ery : busTablePermission.getColumnMap().entrySet()) {
					BusinessColumnPermission busColumnPermission = (BusinessColumnPermission) ery.getValue();
					this.L.getJSONObject(busObjPermission.getKey()).getJSONObject(busTablePermission.getKey()).put(
							busColumnPermission.getKey(), (Object) this.a(busColumnPermission.getResult(), isReadonly));
				}
			}
		}
	}

	private String a(String result, Boolean isReadonly) {
		if (!isReadonly.booleanValue()) {
			return result;
		}
		if (RightsType.REQUIRED.getKey().equals(result) || RightsType.WRITE.getKey().equals(result)) {
			return RightsType.READ.getKey();
		}
		return result;
	}

	public BusinessObjectPermission getBusObj(String string) {
		return this.c(string);
	}
}