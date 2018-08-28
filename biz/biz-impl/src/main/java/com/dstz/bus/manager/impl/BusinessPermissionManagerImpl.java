package com.dstz.bus.manager.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bus.api.constant.RightsType;
import com.dstz.bus.api.model.IBusTableRel;
import com.dstz.bus.api.model.IBusinessColumn;
import com.dstz.bus.dao.BusinessPermissionDao;
import com.dstz.bus.manager.BusinessObjectManager;
import com.dstz.bus.manager.BusinessPermissionManager;
import com.dstz.bus.model.BusTableRel;
import com.dstz.bus.model.BusinessColumn;
import com.dstz.bus.model.BusinessObject;
import com.dstz.bus.model.BusinessPermission;
import com.dstz.bus.model.BusinessTable;
import com.dstz.bus.model.permission.AbstractPermission;
import com.dstz.bus.model.permission.BusColumnPermission;
import com.dstz.bus.model.permission.BusObjPermission;
import com.dstz.bus.model.permission.BusTablePermission;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "busObjPermissionManager")
public class BusinessPermissionManagerImpl extends BaseManager<String, BusinessPermission>
		implements
			BusinessPermissionManager {
	@Resource
	private BusinessPermissionDao businessPermissionDao;
	@Autowired
	private BusinessObjectManager businessObjectManager;

	public BusinessPermission getByObjTypeAndObjVal(String objType, String objVal) {
		return this.businessPermissionDao.getByObjTypeAndObjVal(objType, objVal);
	}

	public BusinessPermission getByObjTypeAndObjVal(String objType, String objVal, String defaultBoKeys) {
		
		
		BusinessPermission oldPermission = this.getByObjTypeAndObjVal(objType, objVal);
		if (oldPermission == null) {
			oldPermission = new BusinessPermission();
		}
		BusinessPermission businessPermission = new BusinessPermission();
		businessPermission.setObjType(objType);
		businessPermission.setObjVal(objVal);
		for (String boKey : defaultBoKeys.split(",")) {
			BusinessObject bo = this.businessObjectManager.getFilledByKey(boKey);
			if (bo == null) {
				throw new BusinessException(boKey + " 业务对象丢失！");
			}
			BusObjPermission busObjPermission = oldPermission.c(boKey);
			if (busObjPermission == null) {
				busObjPermission = new BusObjPermission();
				busObjPermission.setKey(boKey);
				busObjPermission.setName(bo.getName());
				this.a((AbstractPermission) busObjPermission);
			}
			businessPermission.getBusObjMap().put(boKey, busObjPermission);
			for (IBusTableRel rel : bo.getRelation().list()) {
				BusTablePermission busTablePermission = (BusTablePermission) busObjPermission.getTableMap()
						.get(rel.getTableKey());
				if (busTablePermission == null) {
					busTablePermission = new BusTablePermission();
					busTablePermission.setKey(rel.getTableKey());
					busTablePermission.setComment(rel.getTableComment());
				}
				busObjPermission.getTableMap().put(rel.getTableKey(), busTablePermission);
				for (IBusinessColumn column : rel.getTable().getColumnsWithoutPk()) {
					BusColumnPermission busColumnPermission = (BusColumnPermission) busTablePermission.getColumnMap()
							.get(column.getKey());
					if (busColumnPermission == null) {
						busColumnPermission = new BusColumnPermission();
						busColumnPermission.setKey(column.getKey());
						busColumnPermission.setComment(column.getComment());
					}
					busTablePermission.getColumnMap().put(column.getKey(), busColumnPermission);
				}
				Iterator<Map.Entry<String, BusColumnPermission>> it = busTablePermission.getColumnMap().entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, BusColumnPermission> entry = it.next();
					if (rel.getTable().getColumnByKey(entry.getKey()) != null)
						continue;
					it.remove();
				}
			}
			Iterator<Map.Entry<String, BusTablePermission>>  it = busObjPermission.getTableMap().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, BusTablePermission> entry = it.next();
				
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
		permission.getRights().put(RightsType.getDefalut().getKey(), jsonArray);
	}
}