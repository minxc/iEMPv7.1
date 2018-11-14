package org.minxc.emp.biz.core.executor.assemblyval;

import org.minxc.emp.biz.core.model.BusinessDataImpl;

import com.alibaba.fastjson.JSONObject;

public class AssemblyValParam {
	private JSONObject data;
	private BusinessDataImpl businessData;

	public AssemblyValParam(JSONObject data, BusinessDataImpl businessData) {
		this.data = data;
		this.businessData = businessData;
	}

	public JSONObject getData() {
		return this.data;
	}

	public BusinessDataImpl getBusinessData() {
		return this.businessData;
	}
}