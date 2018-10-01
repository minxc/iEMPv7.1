package org.minxc.emp.biz.core.model;

import com.alibaba.fastjson.JSONObject;
import com.minxc.emp.core.impl.model.AbstractCommonModel;
import com.minxc.emp.core.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.biz.api.constant.RightsType;
import org.minxc.emp.biz.api.model.IBusinessPermission;
import org.minxc.emp.biz.api.model.permission.IBusColumnPermission;
import org.minxc.emp.biz.api.model.permission.IBusObjPermission;
import org.minxc.emp.biz.api.model.permission.IBusTablePermission;
import org.minxc.emp.biz.core.model.permission.BusObjPermission;

public class BusinessPermission extends AbstractCommonModel implements IBusinessPermission {
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 3626757861344616318L;
	
	
	private String objType;
	private String objVal;
	private String busObjMapJson;
	private Map<String, BusObjPermission> busObjMap = new HashMap<String, BusObjPermission>();
	private JSONObject K;
	private JSONObject L = null;

	public String getObjType() {
		return this.objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public String getObjVal() {
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
		this.busObjMap = new HashMap<String, BusObjPermission>();
		Map<String, String> map = JSONObject.parseObject(busObjMapJson, Map.class);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			this.busObjMap.put(entry.getKey(), JSONObject.parseObject(entry.getValue(), BusObjPermission.class));
		}
	}

	public Map<String, BusObjPermission> getBusObjMap() {
		return this.busObjMap;
	}

	public void setBusObjMap(Map<String, BusObjPermission> busObjMap) {
		this.busObjMap = busObjMap;
		this.busObjMapJson = JsonUtil.toJSONString(busObjMap);
	}

	public BusObjPermission c(String boKey) {
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
		for (Map.Entry<String, BusObjPermission> entry : this.getBusObjMap().entrySet()) {
			IBusObjPermission busObjPermission = (IBusObjPermission) entry.getValue();
			this.L.put(busObjPermission.getKey(), (Object) new JSONObject());
			this.K.put(busObjPermission.getKey(), (Object) new JSONObject());
			for (Map.Entry etry : busObjPermission.getTableMap().entrySet()) {
				IBusTablePermission busTablePermission = (IBusTablePermission) etry.getValue();
				this.L.getJSONObject(busObjPermission.getKey()).put(busTablePermission.getKey(),
						(Object) new JSONObject());
				this.K.getJSONObject(busObjPermission.getKey()).put(busTablePermission.getKey(),
						(Object) this.a(busTablePermission.getResult(), isReadonly));
				for (Map.Entry ery : busTablePermission.getColumnMap().entrySet()) {
					IBusColumnPermission busColumnPermission = (IBusColumnPermission) ery.getValue();
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

	public IBusObjPermission getBusObj(String string) {
		return this.c(string);
	}
}