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
	
	* @Fields serialVersionUID
	
	*/ 
	private static final long serialVersionUID = 3626757861344616318L;

	private String objectType;
	private String objectValue;
	private String busObjMapJson;
	private Map<String, BusinessObjectPermission> busObjMap = new HashMap<String, BusinessObjectPermission>();
	private JSONObject K;
	private JSONObject L = null;

	@Override
	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

    @Override
	public String getObjectValue() {
		return this.objectValue;
	}

	public void setObjectValue(String objectValue) {
		this.objectValue = objectValue;
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
			this.busObjMap.put(entry.getKey(), JSONObject.parseObject(entry.getValue(), BusinessObjectPermissionImpl.class));
		}
	}

	@Override
	public Map<String, BusinessObjectPermission> getBusinessObjectMap() {
		return this.busObjMap;
	}

	public void setBusObjMap(Map<String, BusinessObjectPermission> busObjMap) {
		this.busObjMap = busObjMap;
		this.busObjMapJson = JsonUtil.toJSONString(busObjMap);
	}

	@Override
	public BusinessObjectPermission getBusObj(String boKey) {
		return this.busObjMap.get(boKey);
	}


    @Override
	public JSONObject getTablePermission(boolean isReadonly) {
		this.a(isReadonly);
		return this.K;
	}

	@Override
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



	public static void main(String[] args){
		// {"Demo":{"key":"Demo","name":"Demo","rights":{"w":[{"type":"everyone","desc":"所有人"}]},"tableMap":{"Demo":{"columnMap":{"zjlx":{"comment":"证件类型","key":"zjlx","rights":{"b":[{"type":"everyone","desc":"所有人"}],"r":[{"type":"everyone","desc":"所有人"}],"w":[{"type":"everyone","desc":"所有人"}]}},"zd1":{"comment":"字段1","key":"zd1","rights":{}},"ah":{"comment":"爱好","key":"ah","rights":{"b":[{"type":"everyone","desc":"所有人"}]}},"bmId":{"comment":"部门ID","key":"bmId","rights":{}},"zd2":{"comment":"字段2","key":"zd2","rights":{}},"xb":{"comment":"性别","key":"xb","rights":{"b":[{"type":"everyone","desc":"所有人"}]}},"bm":{"comment":"部门","key":"bm","rights":{}},"mz":{"comment":"名字","key":"mz","rights":{}},"nl":{"comment":"年龄","key":"nl","rights":{"b":[{"type":"everyone","desc":"所有人"}]}}},"comment":"案例","key":"Demo","rights":{}},"DemoSub":{"columnMap":{"fk":{"comment":"外键","key":"fk","rights":{}},"ms":{"comment":"描述","key":"ms","rights":{}},"zd1":{"comment":"字段1","key":"zd1","rights":{}},"zd2":{"comment":"字段2","key":"zd2","rights":{}},"mz":{"comment":"名字","key":"mz","rights":{}}},"comment":"Demo子表","key":"DemoSub","rights":{}}}}}

		String permissionMapJson = "{\"Demo\":{\"key\":\"Demo\",\"name\":\"Demo\",\"rights\":{\"w\":[{\"type\":\"everyone\",\"desc\":\"所有人\"}]},\"tableMap\":{\"Demo\":{\"columnMap\":{\"zjlx\":{\"comment\":\"证件类型\",\"key\":\"zjlx\",\"rights\":{\"b\":[{\"type\":\"everyone\",\"desc\":\"所有人\"}],\"r\":[{\"type\":\"everyone\",\"desc\":\"所有人\"}],\"w\":[{\"type\":\"everyone\",\"desc\":\"所有人\"}]}},\"zd1\":{\"comment\":\"字段1\",\"key\":\"zd1\",\"rights\":{}},\"ah\":{\"comment\":\"爱好\",\"key\":\"ah\",\"rights\":{\"b\":[{\"type\":\"everyone\",\"desc\":\"所有人\"}]}},\"bmId\":{\"comment\":\"部门ID\",\"key\":\"bmId\",\"rights\":{}},\"zd2\":{\"comment\":\"字段2\",\"key\":\"zd2\",\"rights\":{}},\"xb\":{\"comment\":\"性别\",\"key\":\"xb\",\"rights\":{\"b\":[{\"type\":\"everyone\",\"desc\":\"所有人\"}]}},\"bm\":{\"comment\":\"部门\",\"key\":\"bm\",\"rights\":{}},\"mz\":{\"comment\":\"名字\",\"key\":\"mz\",\"rights\":{}},\"nl\":{\"comment\":\"年龄\",\"key\":\"nl\",\"rights\":{\"b\":[{\"type\":\"everyone\",\"desc\":\"所有人\"}]}}},\"comment\":\"案例\",\"key\":\"Demo\",\"rights\":{}},\"DemoSub\":{\"columnMap\":{\"fk\":{\"comment\":\"外键\",\"key\":\"fk\",\"rights\":{}},\"ms\":{\"comment\":\"描述\",\"key\":\"ms\",\"rights\":{}},\"zd1\":{\"comment\":\"字段1\",\"key\":\"zd1\",\"rights\":{}},\"zd2\":{\"comment\":\"字段2\",\"key\":\"zd2\",\"rights\":{}},\"mz\":{\"comment\":\"名字\",\"key\":\"mz\",\"rights\":{}}},\"comment\":\"Demo子表\",\"key\":\"DemoSub\",\"rights\":{}}}}}";
		Map<String, String> map = JSONObject.parseObject(permissionMapJson, Map.class);
	    System.out.println("Hello world");
    }

}