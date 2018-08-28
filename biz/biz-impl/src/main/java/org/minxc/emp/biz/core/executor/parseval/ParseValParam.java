package org.minxc.emp.biz.core.executor.parseval;

import java.util.Map;

import org.minxc.emp.biz.core.model.BusTableRel;

public class ParseValParam {
	private String key;
	private Object value;
	private Map<String, Object> data;
	private BusTableRel busTableRel;

	public ParseValParam(String key, Object value, Map<String, Object> data, BusTableRel busTableRel) {
		this.key = key;
		this.value = value;
		this.data = data;
		this.busTableRel = busTableRel;
	}

	public String getKey() {
		return this.key;
	}

	public Object getValue() {
		return this.value;
	}

	public Map<String, Object> getData() {
		return this.data;
	}

	public BusTableRel getBusTableRel() {
		return this.busTableRel;
	}
}