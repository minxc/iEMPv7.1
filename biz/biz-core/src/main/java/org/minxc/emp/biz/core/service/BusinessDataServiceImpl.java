package org.minxc.emp.biz.core.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.minxc.emp.biz.api.constant.BusinessTableRelationType;
import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.biz.api.model.BusinessTableRelation;
import org.minxc.emp.biz.api.model.BusinessObject;
import org.minxc.emp.biz.api.model.BusinessPermission;
import org.minxc.emp.biz.api.service.BusinessDataService;
import org.minxc.emp.biz.core.executor.assemblyval.AssemblyValueExecuteChain;
import org.minxc.emp.biz.core.executor.assemblyval.AssemblyValueParam;
import org.minxc.emp.biz.core.executor.parseval.ParseValueExecuteChain;
import org.minxc.emp.biz.core.executor.parseval.ParseValueParam;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.model.BusinessTableRelationImpl;
import org.minxc.emp.biz.core.model.BusinessDataImpl;
import org.minxc.emp.biz.core.model.BusinessObjectImpl;
import org.minxc.emp.core.impl.executor.ExecutorFactory;
import org.minxc.emp.core.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessDataServiceImpl implements BusinessDataService {
	
	
	@Autowired
	private BusinessObjectManager businessObjectManager;

	public void saveFormDefData(JSONObject data, BusinessPermission businessPermission) {
		
		Set<Entry<String, Object>> sets= data.entrySet();
		Iterator<Entry<String, Object>> iter = sets.iterator();

		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			String boKey = entry.getKey();
			JSONObject boData = (JSONObject) entry.getValue();
			BusinessDataImpl businessData = (BusinessDataImpl) this.parseBusinessData(boData, boKey);
			businessData.getBusTableRel().getBusObj().setPermission(businessPermission.getBusObj(boKey));
			BusinessDataPersistenceServiceFactory.saveData(businessData);
		}

	}

	public JSONObject getFormDefData(BusinessObject businessObject, Object id) {
		BusinessDataImpl businessData = BusinessDataPersistenceServiceFactory.loadData((BusinessObjectImpl) businessObject, id);
		if (BeanUtils.isEmpty(id)) {
			this.b(businessData);
		}

		JSONObject data = new JSONObject();
		this.a((JSONObject) data, (BusinessData) businessData);
		return data;
	}

	private void b(BusinessDataImpl businessData) {
		BusinessTableRelation busTableRel = businessData.getBusTableRel();
		businessData.setDbData(busTableRel.getTable().initDbData());
		Iterator var3 = busTableRel.getChildren().iterator();

		while (var3.hasNext()) {
			BusinessTableRelationImpl rel = (BusinessTableRelationImpl) var3.next();
			if (BusinessTableRelationType.ONE_TO_ONE.equalsWithKey(rel.getType())) {
				BusinessDataImpl childData = new BusinessDataImpl();
				childData.setBusTableRel(rel);
				this.b(childData);
				businessData.a(childData);
			}
		}

	}

	public JSONObject assemblyFormDefData(BusinessData businessData) {
		JSONObject data = new JSONObject();
		this.a(data, businessData);
		return data;
	}

	private void a(JSONObject data, BusinessData ibusinessData) {
		BusinessDataImpl businessData = (BusinessDataImpl) ibusinessData;
		AssemblyValueParam param = new AssemblyValueParam(data, businessData);
		ExecutorFactory.execute(AssemblyValueExecuteChain.class, param);
		Iterator var5 = businessData.getChildren().entrySet().iterator();

		while (true) {
			while (var5.hasNext()) {
				Entry<String, List<BusinessDataImpl>> entry = (Entry) var5.next();
				String tableKey = (String) entry.getKey();
				List<BusinessDataImpl> children = (List) entry.getValue();
				if (BusinessTableRelationType.ONE_TO_ONE
						.equalsWithKey(((BusinessDataImpl) children.get(0)).getBusTableRel().getType())) {
					JSONObject cData = new JSONObject();
					this.a(cData, (BusinessData) children.get(0));
					data.put(tableKey, cData);
				} else {
					JSONArray dataList = new JSONArray();
					Iterator var10 = children.iterator();

					while (var10.hasNext()) {
						BusinessDataImpl bd = (BusinessDataImpl) var10.next();
						JSONObject cData = new JSONObject();
						this.a((JSONObject) cData, (BusinessData) bd);
						dataList.add(cData);
					}

					data.put(tableKey + "List", dataList);
				}
			}

			return;
		}
	}

	private BusinessDataImpl a(Object object, BusinessTableRelationImpl relation) {
		BusinessDataImpl businessData = new BusinessDataImpl();
		businessData.setBusTableRel(relation);
		if (object instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) object;
			Map<String, Object> bdData = new HashMap();
			Iterator var6 = jsonObject.entrySet().iterator();

			while (var6.hasNext()) {
				Entry<String, Object> enty = (Entry) var6.next();
				if (!(enty.getValue() instanceof JSONArray) && !(enty.getValue() instanceof JSONObject)) {
					ParseValueParam param = new ParseValueParam((String) enty.getKey(), enty.getValue(), bdData, relation);
					ExecutorFactory.execute(ParseValueExecuteChain.class, param);
				}

				if (enty.getValue() instanceof JSONArray) {
					String tableKey = ((String) enty.getKey()).substring(0, ((String) enty.getKey()).length() - 4);
					BusinessTableRelationImpl rel = (BusinessTableRelationImpl) relation.find(tableKey);
					Iterator var10 = ((JSONArray) enty.getValue()).iterator();

					while (var10.hasNext()) {
						Object obj = var10.next();
						businessData.a(this.a(obj, rel));
					}
				}

				if (enty.getValue() instanceof JSONObject) {
					BusinessTableRelationImpl rel = (BusinessTableRelationImpl) relation.find(enty.getKey());
					businessData.a(this.a(enty.getValue(), rel));
				}
			}

			businessData.setData(bdData);
		}

		return businessData;
	}

	public void removeData(BusinessObject businessObject, Object id) {
		BusinessDataPersistenceServiceFactory.removeData((BusinessObjectImpl) businessObject, id);
	}

	public void saveData(BusinessData businessData) {
		BusinessDataPersistenceServiceFactory.saveData((BusinessDataImpl) businessData);
	}

	public BusinessData loadData(BusinessObject businessObject, Object id) {
		return BusinessDataPersistenceServiceFactory.loadData((BusinessObjectImpl) businessObject, id);
	}

	public BusinessData parseBusinessData(JSONObject jsonObject, String boKey) {
		BusinessObjectImpl businessObject = this.businessObjectManager.getFilledByKey(boKey);
		return this.a((Object) jsonObject, (BusinessTableRelationImpl) businessObject.getRelation());
	}
}