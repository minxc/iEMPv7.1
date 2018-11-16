package org.minxc.emp.form.core.service;

import org.minxc.emp.biz.api.constant.BusinessTableRelationType;
import org.minxc.emp.biz.api.constant.BusinessPermissionObjectType;
import org.minxc.emp.biz.api.model.BusinessTableRelation;
import org.minxc.emp.biz.api.model.BusinessObject;
import org.minxc.emp.biz.api.model.BusinessPermission;
import org.minxc.emp.biz.api.service.BusinessDataService;
import org.minxc.emp.biz.api.service.BusinessObjectService;
import org.minxc.emp.biz.api.service.BusinessPermissionService;
import org.minxc.emp.biz.api.service.BusinessTableService;
import org.minxc.emp.form.api.service.FormDefinitionDataService;
import org.minxc.emp.form.core.manager.FormDefinitionManager;
import org.minxc.emp.form.core.model.FormDefinitionImpl;
import org.minxc.emp.form.core.model.FormDefinitionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;


/**
 * FormDefData的服务类
 */
@Service
public class FormDefinitionDataServiceImpl implements FormDefinitionDataService {
	
	@Autowired
	FormDefinitionManager formDefManager;
	@Autowired
	BusinessObjectService businessObjectService;
	@Autowired
	BusinessTableService businessTableService;
	@Autowired
	BusinessPermissionService businessPermissionService;
	@Autowired
	BusinessDataService businessDataService;

	/**
	 * 
	 * 根据表单key和表单数据主表的id
	 * 
	 * 
	 * @param formDefKey
	 * @param id
	 * @return
	 */
	public FormDefinitionData getByFormDefKey(String formDefKey, String id) {
		FormDefinitionData formDefData = new FormDefinitionData();
		FormDefinitionImpl formDef = formDefManager.getByKey(formDefKey);
		formDefData.setHtml(formDef.getHtml());
		
		BusinessPermission businessPermission = businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjectType.FORM.getKey(), formDef.getKey(), formDef.getBoKey(), true);
		
		formDefData.setPermission(businessPermission.getPermission(false));
		formDefData.setTablePermission(businessPermission.getTablePermission(false));
		
		handleInitData(formDef, formDefData);
		handleData(formDef, id, formDefData,businessPermission);

		return formDefData;
	}

	

	/**
	 * 
	 * 处理boKey的初始化数据
	 * 
	 * 
	 * @param formDef
	 * @param formDefData
	 */
	private void handleInitData(FormDefinitionImpl formDef, FormDefinitionData formDefData) {
		if (formDefData.getInitData() == null) {
			formDefData.setInitData(new JSONObject());
		}
		JSONObject initData = formDefData.getInitData();
		BusinessObject businessObject = businessObjectService.getFilledByKey(formDef.getBoKey());

		initData.put(formDef.getBoKey(), new JSONObject());

		for (BusinessTableRelation rel : businessObject.getRelation().list()) {
			initData.getJSONObject(formDef.getBoKey()).put(rel.getTableKey(), getInitData(rel));
		}
	}
	
	/**
	 * 
	 * 获取初始化数据
	 * 
	 * 
	 * 
	 * @param busTableRel
	 * @return
	 */
	private JSONObject getInitData(BusinessTableRelation busTableRel) {
		JSONObject table = new JSONObject();
		table.putAll(busTableRel.getTable().initData());
		for (BusinessTableRelation rel : busTableRel.getChildren()) {
			if (BusinessTableRelationType.ONE_TO_ONE.equalsWithKey(rel.getType())) {
				// 递归处理一对一
				table.put(rel.getTableKey(), getInitData(rel));
			}
		}
		return table;
	}

	/**
	 * 
	 * 处理initdata初始化数据和data数据
	 * 
	 * 
	 * @param formDef
	 * @param id
	 *            bo对应数据的主键
	 * @param formDefData
	 * @param businessPermission2 
	 */
	private void handleData(FormDefinitionImpl formDef, String id, FormDefinitionData formDefData, BusinessPermission businessPermission) {
		if (formDefData.getData() == null) {
			formDefData.setData(new JSONObject());
		}

		JSONObject data = formDefData.getData();
		BusinessObject businessObject = businessObjectService.getFilledByKey(formDef.getBoKey());
		businessObject.setPermission(businessPermission.getBusinessObjectMap().get(formDef.getBoKey()));
		data.put(formDef.getBoKey(), businessDataService.getFormDefData(businessObject, id));
	}

}
