package org.minxc.emp.biz.core.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minxc.emp.core.impl.executor.ExecutorFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.minxc.emp.biz.api.constant.BusTableRelType;
import org.minxc.emp.biz.api.model.IBusTableRel;
import org.minxc.emp.biz.api.model.IBusinessData;
import org.minxc.emp.biz.api.model.IBusinessObject;
import org.minxc.emp.biz.api.model.IBusinessPermission;
import org.minxc.emp.biz.api.service.IBusinessDataService;
import org.minxc.emp.biz.core.executor.assemblyval.AssemblyValExecuteChain;
import org.minxc.emp.biz.core.executor.assemblyval.AssemblyValParam;
import org.minxc.emp.biz.core.executor.parseval.ParseValExecuteChain;
import org.minxc.emp.biz.core.executor.parseval.ParseValParam;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.model.BusTableRel;
import org.minxc.emp.biz.core.model.BusinessData;
import org.minxc.emp.biz.core.model.BusinessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessDataService implements IBusinessDataService {
	
	
	@Autowired
	private BusinessObjectManager businessObjectManager;

	public void saveFormDefData(JSONObject data, IBusinessPermission businessPermission) {
		
		Set<Entry<String, Object>> sets= data.entrySet();
		Iterator<Entry<String, Object>> iter = sets.iterator();

		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			String boKey = entry.getKey();
			JSONObject boData = (JSONObject) entry.getValue();
			BusinessData businessData = (BusinessData) this.parseBusinessData(boData, boKey);
			businessData.getBusTableRel().getBusObj().setPermission(businessPermission.getBusObj(boKey));
			BusinessDataPersistenceServiceFactory.saveData(businessData);
		}

	}

	public JSONObject getFormDefData(IBusinessObject businessObject, Object id) {
		BusinessData businessData = BusinessDataPersistenceServiceFactory.loadData((BusinessObject) businessObject, id);
		if (BeanUtils.isEmpty(id)) {
			this.b(businessData);
		}

		JSONObject data = new JSONObject();
		this.a((JSONObject) data, (IBusinessData) businessData);
		return data;
	}

	private void b(BusinessData businessData) {
		IBusTableRel busTableRel = businessData.getBusTableRel();
		businessData.setDbData(busTableRel.getTable().initDbData());
		Iterator var3 = busTableRel.getChildren().iterator();

		while (var3.hasNext()) {
			BusTableRel rel = (BusTableRel) var3.next();
			if (BusTableRelType.ONE_TO_ONE.equalsWithKey(rel.getType())) {
				BusinessData childData = new BusinessData();
				childData.setBusTableRel(rel);
				this.b(childData);
				businessData.a(childData);
			}
		}

	}

	public JSONObject assemblyFormDefData(IBusinessData businessData) {
		JSONObject data = new JSONObject();
		this.a(data, businessData);
		return data;
	}

	private void a(JSONObject data, IBusinessData ibusinessData) {
		BusinessData businessData = (BusinessData) ibusinessData;
		AssemblyValParam param = new AssemblyValParam(data, businessData);
		ExecutorFactory.execute(AssemblyValExecuteChain.class, param);
		Iterator var5 = businessData.getChildren().entrySet().iterator();

		while (true) {
			while (var5.hasNext()) {
				Entry<String, List<BusinessData>> entry = (Entry) var5.next();
				String tableKey = (String) entry.getKey();
				List<BusinessData> children = (List) entry.getValue();
				if (BusTableRelType.ONE_TO_ONE
						.equalsWithKey(((BusinessData) children.get(0)).getBusTableRel().getType())) {
					JSONObject cData = new JSONObject();
					this.a(cData, (IBusinessData) children.get(0));
					data.put(tableKey, cData);
				} else {
					JSONArray dataList = new JSONArray();
					Iterator var10 = children.iterator();

					while (var10.hasNext()) {
						BusinessData bd = (BusinessData) var10.next();
						JSONObject cData = new JSONObject();
						this.a((JSONObject) cData, (IBusinessData) bd);
						dataList.add(cData);
					}

					data.put(tableKey + "List", dataList);
				}
			}

			return;
		}
	}

	private BusinessData a(Object object, BusTableRel relation) {
		BusinessData businessData = new BusinessData();
		businessData.setBusTableRel(relation);
		if (object instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) object;
			Map<String, Object> bdData = new HashMap();
			Iterator var6 = jsonObject.entrySet().iterator();

			while (var6.hasNext()) {
				Entry<String, Object> enty = (Entry) var6.next();
				if (!(enty.getValue() instanceof JSONArray) && !(enty.getValue() instanceof JSONObject)) {
					ParseValParam param = new ParseValParam((String) enty.getKey(), enty.getValue(), bdData, relation);
					ExecutorFactory.execute(ParseValExecuteChain.class, param);
				}

				if (enty.getValue() instanceof JSONArray) {
					String tableKey = ((String) enty.getKey()).substring(0, ((String) enty.getKey()).length() - 4);
					BusTableRel rel = (BusTableRel) relation.find(tableKey);
					Iterator var10 = ((JSONArray) enty.getValue()).iterator();

					while (var10.hasNext()) {
						Object obj = var10.next();
						businessData.a(this.a(obj, rel));
					}
				}

				if (enty.getValue() instanceof JSONObject) {
					BusTableRel rel = (BusTableRel) relation.find(enty.getKey());
					businessData.a(this.a(enty.getValue(), rel));
				}
			}

			businessData.setData(bdData);
		}

		return businessData;
	}

	public void removeData(IBusinessObject businessObject, Object id) {
		BusinessDataPersistenceServiceFactory.removeData((BusinessObject) businessObject, id);
	}

	public void saveData(IBusinessData businessData) {
		BusinessDataPersistenceServiceFactory.saveData((BusinessData) businessData);
	}

	public IBusinessData loadData(IBusinessObject businessObject, Object id) {
		return BusinessDataPersistenceServiceFactory.loadData((BusinessObject) businessObject, id);
	}

	public IBusinessData parseBusinessData(JSONObject jsonObject, String boKey) {
		BusinessObject businessObject = this.businessObjectManager.getFilledByKey(boKey);
		return this.a((Object) jsonObject, (BusTableRel) businessObject.getRelation());
	}
}