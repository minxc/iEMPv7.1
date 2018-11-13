package org.minxc.emp.biz.core.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.minxc.emp.biz.api.constant.BusTableRelFkType;
import org.minxc.emp.biz.api.constant.BusTableRelType;
import org.minxc.emp.biz.api.constant.BusinessObjectPersistenceType;
import org.minxc.emp.biz.api.model.IBusTableRel;
import org.minxc.emp.biz.api.model.BizTableRelationForeignKey;
import org.minxc.emp.biz.api.model.IBusinessColumn;
import org.minxc.emp.biz.api.model.IBusinessData;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.manager.BusinessTableManager;
import org.minxc.emp.biz.core.model.BusTableRel;
import org.minxc.emp.biz.core.model.BusinessColumn;
import org.minxc.emp.biz.core.model.BusinessData;
import org.minxc.emp.biz.core.model.BusinessObject;
import org.minxc.emp.biz.core.model.BusinessTable;
import org.minxc.emp.biz.core.service.BusinessDataPersistenceService;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.tableoper.TableOperator;
import org.minxc.emp.core.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*
 * 
*    
* 项目名称：bus-core   
* 类名称：BusinessDataPersistenceDbService   
* 类   TODO:BusinessDataPersistenceDbService內部方法需要大規模的進行重新的梳理，各种匿名的方法和接口之间的转换需进行处理
* 创建人：Xianchang.min   
* 创建时间：2018年8月11日 下午6:18:25   
* 修改人：Xianchang.min   
* 修改时间：2018年8月11日 下午6:18:25   
* 修改备注：   
* @version  1.0  
*
 */
@Service
@Transactional
public class BusinessDataPersistenceDbService implements BusinessDataPersistenceService {
	@Autowired
	private BusinessTableManager businessTableManager;
	@Autowired
	private BusinessObjectManager k;

	public String type() {
		return BusinessObjectPersistenceType.DB.getKey();
	}

	public void saveData(BusinessData businessData) {
		Object id;
		TableOperator tableOperator = this.businessTableManager.newTableOperator((BusinessTable) businessData.getBusTableRel().getTable());
		String busTableRelType = businessData.getBusTableRel().getType();
		if (!businessData.getBusTableRel().getBusObj()
				.haveTableDbEditRights(businessData.getBusTableRel().getTableKey())) {
			return;
		}
		if (BusTableRelType.MAIN.equalsWithKey(busTableRelType)) {
			id = businessData.getPk();
			if (BeanUtils.isEmpty((Object) id)) {
				businessData.setPk((Object) UniqueIdUtil.getSuid());
				tableOperator.insertData(businessData.getDbData());
			} else {
				tableOperator.updateData(businessData.getDbData());
			}
		}
		if (BusTableRelType.ONE_TO_ONE.equalsWithKey(busTableRelType)
				|| BusTableRelType.ONE_TO_MANY.equalsWithKey(busTableRelType)) {
			id = businessData.getPk();
			if (BeanUtils.isEmpty((Object) id)) {
				businessData.setPk((Object) UniqueIdUtil.getSuid());
				BusinessData parBusinessData = businessData.getParent();
				for (BizTableRelationForeignKey fk : businessData.getBusTableRel().getFks()) {
					if (BusTableRelFkType.FIXED_VALUE.equalsWithKey(fk.getType())) {
						businessData.put(fk.getFrom(), (Object) fk.getValue());
						continue;
					}
					if (BusTableRelFkType.PARENT_FIELD.equalsWithKey(fk.getType())) {
						businessData.put(fk.getFrom(), parBusinessData.get(fk.getValue()));
						continue;
					}
					if (!BusTableRelFkType.CHILD_FIELD.equalsWithKey(fk.getType()))
						continue;
					parBusinessData.put(fk.getValue(), businessData.get(fk.getFrom()));
					this.businessTableManager.newTableOperator((BusinessTable) parBusinessData.getBusTableRel().getTable())
							.updateData(parBusinessData.getDbData());
				}
				tableOperator.insertData(businessData.getDbData());
			} else {
				tableOperator.updateData(businessData.getDbData());
			}
		}
		this.c(businessData);
	}

	private void c(BusinessData businessData) {
		for (IBusTableRel rel : businessData.getBusTableRel().getChildren()) {
			if (!rel.getBusObj().haveTableDbEditRights(rel.getTableKey()))
				continue;
			TableOperator tableOperator = this.businessTableManager.newTableOperator((BusinessTable)rel.getTable());
			if (!BusTableRelType.ONE_TO_MANY.equalsWithKey(rel.getType())
					&& !BusTableRelType.ONE_TO_ONE.equalsWithKey(rel.getType()))
				continue;
			HashMap<String, Object> param = new HashMap<String, Object>();
			for (BizTableRelationForeignKey fk : rel.getFks()) {
				if (BusTableRelFkType.FIXED_VALUE.equalsWithKey(fk.getType())) {
					param.put(fk.getFrom(), fk.getValue());
					continue;
				}
				if (BusTableRelFkType.PARENT_FIELD.equalsWithKey(fk.getType())) {
					param.put(fk.getFrom(), businessData.get(fk.getValue()));
					continue;
				}
				if (!BusTableRelFkType.CHILD_FIELD.equalsWithKey(fk.getType()))
					continue;
			}
			List<Map<String, Object>> oldDatas = new ArrayList<Map<String, Object>>();
			if (!param.isEmpty()) {
				oldDatas = tableOperator.selectData(this.a((BusinessTable)rel.getTable(), param));
			}
			List<IBusinessData> children = businessData.getChildren().computeIfAbsent(rel.getTableKey(), k -> new ArrayList());
			block2 : for (Map oldData : oldDatas) {
				Object id = oldData.get(((BusinessTable)rel.getTable()).getPkName());
				for (IBusinessData data : children) {
					if (!id.equals(data.getPk()))
						continue;
					continue block2;
				}
				this.a(oldData, (BusTableRel)rel);
				tableOperator.deleteData(id);
			}
			for (IBusinessData data : children) {
				this.saveData((BusinessData) data);
			}
		}
	}

	public BusinessData loadData(BusinessObject businessObject, Object id) {
		BusinessData businessData = new BusinessData();
		BusTableRel busTableRel = businessObject.getRelation();
		businessData.setBusTableRel(busTableRel);
		BusinessTable businessTable = (BusinessTable) busTableRel.getTable();
		if (BeanUtils.isEmpty((Object) id)) {
			return businessData;
		}
		if (!businessObject.haveTableDbReadRights(busTableRel.getTableKey())) {
			return businessData;
		}
		Map data = this.businessTableManager.newTableOperator(businessTable).selectData(this.b(busTableRel), id);
		businessData.setDbData(data);
		this.a(businessData, busTableRel);
		return businessData;
	}

	private void a(BusinessData businessData, BusTableRel busTableRel) {
		for (IBusTableRel rel : busTableRel.getChildren()) {
//			Object fk2;
			BusinessTable table = (BusinessTable) rel.getTable();
			if (!rel.getBusObj().haveTableDbReadRights(rel.getTableKey())) {
				return;
			}
			HashMap<String, Object> param = new HashMap<String, Object>();
			for (BizTableRelationForeignKey fk2 : rel.getFks()) {
				if (BusTableRelFkType.FIXED_VALUE.equalsWithKey(fk2.getType())) {
					param.put(fk2.getFrom(), fk2.getValue());
					continue;
				}
				if (BusTableRelFkType.PARENT_FIELD.equalsWithKey(fk2.getType())) {
					param.put(fk2.getFrom(), businessData.get(fk2.getValue()));
					continue;
				}
				if (!BusTableRelFkType.CHILD_FIELD.equalsWithKey(fk2.getType()))
					continue;
				param.put(fk2.getFrom(), businessData.get(fk2.getValue()));
			}
			List<Map<String, Object>> dataMapList = this.businessTableManager.newTableOperator(table).selectData(this.b((BusTableRel)rel), this.a(table, param));
			
			Iterator<Map<String, Object>> iter2 = dataMapList.iterator();
			while (iter2.hasNext()) {
				Map<String, Object> dataMap = iter2.next();
				BusinessData data = new BusinessData();
				data.setBusTableRel(rel);
				data.setParent(businessData);
				data.setDbData(dataMap);
				businessData.a(data);
				this.a(data, (BusTableRel)rel);
			}
		}
	}

	public void removeData(BusinessObject businessObject, Object id) {
		BusTableRel busTableRel = businessObject.getRelation();
		if (!busTableRel.getBusObj().haveTableDbEditRights(busTableRel.getTableKey())) {
			return;
		}
		Map data = this.businessTableManager.newTableOperator((BusinessTable)busTableRel.getTable()).selectData(id);
		this.businessTableManager.newTableOperator((BusinessTable)busTableRel.getTable()).deleteData(data.get(((BusinessTable)busTableRel.getTable()).getPkName()));
		this.a(data, busTableRel);
	}

	private void a(Map<String, Object> dbData, BusTableRel busTableRel) {
		for (IBusTableRel rel : busTableRel.getChildren()) {
//			Object fk2;
			if (!rel.getBusObj().haveTableDbEditRights(rel.getTableKey()))
				continue;
			HashMap<String, Object> param = new HashMap<String, Object>();
			Map<String, Object> data = this.b((BusinessTable)busTableRel.getTable(), dbData);
			for (BizTableRelationForeignKey fk2 : rel.getFks()) {
				if (BusTableRelFkType.FIXED_VALUE.equalsWithKey(fk2.getType())) {
					param.put(fk2.getFrom(), fk2.getValue());
					continue;
				}
				if (!BusTableRelFkType.PARENT_FIELD.equalsWithKey(fk2.getType()))
					continue;
				param.put(fk2.getFrom(), data.get(fk2.getValue()));
			}
			if (rel.getChildren().isEmpty()) {
				this.businessTableManager.newTableOperator((BusinessTable)rel.getTable()).deleteData(this.a((BusinessTable)rel.getTable(), param));
				continue;
			}
			List<Map<String, Object>> dataMapList = this.businessTableManager.newTableOperator((BusinessTable)rel.getTable()).selectData(this.a((BusinessTable)rel.getTable(), param));
			Iterator<Map<String, Object>> iter = dataMapList.iterator();
			while (iter.hasNext()) {
				Map<String, Object> dataMap = iter.next();
				this.a(dataMap, (BusTableRel)rel);
			}
		}
	}

	private List<String> b(BusTableRel busTableRel) {
		ArrayList<String> columnName = new ArrayList<String>();
		for (IBusinessColumn column : busTableRel.getTable().getColumns()) {
			if (!busTableRel.getBusObj().haveColumnDbReadRights(busTableRel.getTableKey(), column.getKey()))
				continue;
			columnName.add(column.getName());
		}
		return columnName;
	}

	private Map<String, Object> a(BusinessTable table, Map<String, Object> map) {
		HashMap<String, Object> dbData = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String columnName = table.getColumnByKey(entry.getKey()).getName();
			dbData.put(columnName, entry.getValue());
		}
		return dbData;
	}

	private Map<String, Object> b(BusinessTable table, Map<String, Object> map) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String columnKey = ((BusinessColumn) table.getColumn(entry.getKey())).getKey();
			data.put(columnKey, entry.getValue());
		}
		return data;
	}
}