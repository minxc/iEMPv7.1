package org.minxc.emp.biz.core.manager.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Map;

import org.minxc.emp.biz.api.constant.RightsType;
import org.minxc.emp.biz.api.model.BusinessTableRelation;
import org.minxc.emp.biz.api.model.BusinessColumn;
import org.minxc.emp.biz.api.model.permission.BusinessObjectPermission;
import org.minxc.emp.biz.core.dao.BusinessPermissionDao;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.manager.BusinessPermissionManager;
import org.minxc.emp.biz.core.model.BusinessObjectImpl;
import org.minxc.emp.biz.core.model.BusinessPermissionImpl;
import org.minxc.emp.biz.core.model.permission.AbstractPermission;
import org.minxc.emp.biz.core.model.permission.BusinessColumnPermissionImpl;
import org.minxc.emp.biz.core.model.permission.BusinessObjectPermissionImpl;
import org.minxc.emp.biz.core.model.permission.BusinessTablePermissionImpl;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "busObjPermissionManager")
public class BusinessPermissionManagerImpl extends CommonManager<String, BusinessPermissionImpl>
		implements
			BusinessPermissionManager {
	@Autowired
	private BusinessPermissionDao businessPermissionDao;
	@Autowired
	private BusinessObjectManager businessObjectManager;

	public BusinessPermissionImpl getByObjTypeAndObjVal(String objType, String objVal) {
		return this.businessPermissionDao.getByObjTypeAndObjVal(objType, objVal);
	}

	public BusinessPermissionImpl getByObjTypeAndObjVal(String objType, String objVal, String defaultBoKeys) {
		
		
		BusinessPermissionImpl oldPermission = this.getByObjTypeAndObjVal(objType, objVal);
		if (oldPermission == null) {
			oldPermission = new BusinessPermissionImpl();
		}
		BusinessPermissionImpl businessPermission = new BusinessPermissionImpl();
		businessPermission.setObjectType(objType);
		businessPermission.setObjectValue(objVal);
		for (String boKey : defaultBoKeys.split(",")) {
			BusinessObjectImpl bo = this.businessObjectManager.getFilledByKey(boKey);
			if (bo == null) {
				throw new BusinessException(boKey + " 业务对象丢失！");
			}
			BusinessObjectPermissionImpl busObjPermission = (BusinessObjectPermissionImpl)oldPermission.getBusObj(boKey);
			if (busObjPermission == null) {
				busObjPermission = new BusinessObjectPermissionImpl();
				busObjPermission.setKey(boKey);
				busObjPermission.setName(bo.getName());
				this.a((AbstractPermission) busObjPermission);
			}
			businessPermission.getBusinessObjectMap().put(boKey, busObjPermission);
			for (BusinessTableRelation rel : bo.getRelation().list()) {
				BusinessTablePermissionImpl busTablePermission = (BusinessTablePermissionImpl) busObjPermission.getTableMap()
						.get(rel.getTableKey());
				if (busTablePermission == null) {
					busTablePermission = new BusinessTablePermissionImpl();
					busTablePermission.setKey(rel.getTableKey());
					busTablePermission.setComment(rel.getTableComment());
				}
				busObjPermission.getTableMap().put(rel.getTableKey(), busTablePermission);
				for (BusinessColumn column : rel.getTable().getColumnsWithoutPk()) {
					BusinessColumnPermissionImpl busColumnPermission = (BusinessColumnPermissionImpl) busTablePermission.getColumnMap()
							.get(column.getKey());
					if (busColumnPermission == null) {
						busColumnPermission = new BusinessColumnPermissionImpl();
						busColumnPermission.setKey(column.getKey());
						busColumnPermission.setComment(column.getComment());
					}
					busTablePermission.getColumnMap().put(column.getKey(), busColumnPermission);
				}
				Iterator<Map.Entry<String, BusinessColumnPermissionImpl>> it = busTablePermission.getColumnMap().entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, BusinessColumnPermissionImpl> entry = it.next();
					if (rel.getTable().getColumnByKey(entry.getKey()) != null)
						continue;
					it.remove();
				}
			}
			Iterator<Map.Entry<String, BusinessTablePermissionImpl>>  it = busObjPermission.getTableMap().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, BusinessTablePermissionImpl> entry = it.next();
				
				if (bo.getRelation().find(entry.getKey()) != null)
					continue;
				it.remove();
			}
		}
		return businessPermission;
	}

	private void a(AbstractPermission permission) {
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("type",  "everyone");
		json.put("desc",  "所有人");
		jsonArray.add(json);
		permission.getRights().put(RightsType.getDefault().getKey(), jsonArray);
	}
}