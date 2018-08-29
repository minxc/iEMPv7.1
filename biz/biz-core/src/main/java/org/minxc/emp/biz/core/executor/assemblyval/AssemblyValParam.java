package org.minxc.emp.biz.core.executor.assemblyval;

import org.minxc.emp.biz.core.model.BusinessData;

import com.alibaba.fastjson.JSONObject;

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