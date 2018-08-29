package org.minxc.emp.biz.core.manager.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;

import org.minxc.emp.biz.api.constant.RightsType;
import org.minxc.emp.biz.api.model.IBusTableRel;
import org.minxc.emp.biz.api.model.IBusinessColumn;
import org.minxc.emp.biz.core.dao.BusinessPermissionDao;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.manager.BusinessPermissionManager;
import org.minxc.emp.biz.core.model.BusTableRel;
import org.minxc.emp.biz.core.model.BusinessColumn;
import org.minxc.emp.biz.core.model.BusinessObject;
import org.minxc.emp.biz.core.model.BusinessPermission;
import org.minxc.emp.biz.core.model.BusinessTable;
import org.minxc.emp.biz.core.model.permission.AbstractPermission;
import org.minxc.emp.biz.core.model.permission.BusColumnPermission;
import org.minxc.emp.biz.core.model.permission.BusObjPermission;
import org.minxc.emp.biz.core.model.permission.BusTablePermission;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "busObjPermissionManager")
public class BusinessPermissionManagerImpl extends CommonManager<String, BusinessPermission>
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