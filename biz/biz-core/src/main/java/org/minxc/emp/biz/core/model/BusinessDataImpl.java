package org.minxc.emp.biz.core.model;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.minxc.emp.biz.api.constant.BusinessTableRelationType;
import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.biz.api.model.BusinessTableRelation;
import org.minxc.emp.biz.api.model.BusinessColumn;

public class BusinessDataImpl implements BusinessData {
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -6272407461993770532L;
	
	
	private BusinessTableRelation busTableRel;
	private Map<String, Object> data = new HashMap<String, Object>();
	private Map<String, List<BusinessData>> children = new HashMap<String, List<BusinessData>>();
	private BusinessDataImpl parent;

	public BusinessTableRelation getBusTableRel() {
		return this.busTableRel;
	}

	public void setBusTableRel(BusinessTableRelation busTableRel) {
		this.busTableRel = busTableRel;
	}
	
	@Override
	public Map<String, Object> getData() {
		return this.data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Map<String, List<BusinessData>> getChildren() {
		return this.children;
	}

	public void setChildren(Map<String, List<BusinessData>> children) {
		this.children = children;
	}

	public BusinessDataImpl getParent() {
		return this.parent;
	}

	public void setParent(BusinessDataImpl parent) {
		this.parent = parent;
	}

	public void setPk(Object id) {
		BusinessTableImpl businessTable = (BusinessTableImpl)this.busTableRel.getTable();
		this.data.put(businessTable.getPkKey(), id);
	}
	
	@Override
	public Object getPk() {
		BusinessTableImpl businessTable = (BusinessTableImpl)this.busTableRel.getTable();
		return this.data.get(businessTable.getPkKey());
	}

	public void put(String key, Object value) {
		this.data.put(key, value);
	}

	public Object get(String key) {
		return this.data.get(key);
	}

	public String getString(String key) {
		Object obj = this.data.get(key);
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public Map<String, Object> getDbData() {
		HashMap<String, Object> dbData = new HashMap<String, Object>();
		for (BusinessColumn column : this.busTableRel.getTable().getColumns()) {
			if (!column.isPrimary()
					&& !this.busTableRel.getBusObj().haveColumnDbEditRights(this.busTableRel.getTableKey(), column.getKey()))
				continue;
			Object val = this.data.get(column.getKey());
			dbData.put(column.getName(), val);
		}
		return dbData;
	}

	public void setDbData(Map<String, Object> dbData) {
		for (BusinessColumn column : this.busTableRel.getTable().getColumns()) {
			if (!this.busTableRel.getBusObj().haveColumnDbReadRights(this.busTableRel.getTableKey(), column.getKey()))
				continue;
			this.data.put(column.getKey(), dbData.get(column.getName()));
		}
	}

	
	
	//TODO:推测该方法的作用
	public void a(BusinessDataImpl businessData) {
		String tableKey = businessData.getBusTableRel().getTable().getKey();
		List list = this.children.computeIfAbsent(tableKey, k -> new ArrayList());
		businessData.setParent(this);
		list.add(businessData);
	}

	public Map<String, List<BusinessData>> getChilds() {
		HashMap<String, List<BusinessData>> map = new HashMap<String, List<BusinessData>>();
		for (Map.Entry<String, List<BusinessData>> entry : this.children.entrySet()) {
			ArrayList<BusinessData> list = new ArrayList<BusinessData>();
			list.addAll(entry.getValue());
			map.put(entry.getKey(), list);
		}
		return map;
	}

	
	/**
	 * TODO:填充的下级数据，需要继续猜测其使用方法
	 */
	@Override
	public JSONObject fullBusDataInitData(JSONObject initData) {
		if (initData == null) {
			initData = new JSONObject();
		}
		JSONObject initTables = new JSONObject();
		for (BusinessTableRelation rel : this.getBusTableRel().getChildren()) {
			initTables.put(rel.getTableKey(), (Object) this.a(rel));
		}
		initData.put(this.getBusTableRel().getBusObj().getKey(), (Object) initTables);
		return initData;
	}

	private JSONObject a(BusinessTableRelation busTableRel) {
		JSONObject table = new JSONObject();
		table.putAll(busTableRel.getTable().initData());
		for (BusinessTableRelation rel : busTableRel.getChildren()) {
			if (!BusinessTableRelationType.ONE_TO_ONE.equalsWithKey(rel.getType()))
				continue;
			table.put(rel.getTableKey(), (Object) this.a(rel));
		}
		return table;
	}
}