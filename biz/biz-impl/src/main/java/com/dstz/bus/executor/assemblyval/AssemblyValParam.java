package com.dstz.bus.executor.assemblyval;

import com.alibaba.fastjson.JSONObject;
import com.dstz.bus.model.BusinessData;

public class AssemblyValParam {
	private JSONObject data;
	private BusinessData businessData;

	public AssemblyValParam(JSONObject data, BusinessData businessData) {
		this.data = data;
		this.businessData = businessData;
	}

	public JSONObject getData() {
		return this.data;
	}

	public BusinessData getBusinessData() {
		return this.businessData;
	}
}