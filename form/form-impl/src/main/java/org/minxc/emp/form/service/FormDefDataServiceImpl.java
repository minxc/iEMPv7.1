package org.minxc.emp.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.business.api.constant.BusinessTableRelType;
import org.minxc.emp.business.api.constant.BusinessPermissionObjectType;
import org.minxc.emp.business.api.model.BusinessTableRel;
import org.minxc.emp.business.api.model.BusinessObject;
import org.minxc.emp.business.api.model.BusinessPermission;
import org.minxc.emp.business.api.service.BusinessDataService;
import org.minxc.emp.business.api.service.BusinessObjectService;
import org.minxc.emp.business.api.service.BusinessPermissionService;
import org.minxc.emp.business.api.service.BusinessTableService;
import org.minxc.emp.form.api.service.IFormDefDataService;
import org.minxc.emp.form.manager.FormDefManager;
import org.minxc.emp.form.model.FormDef;
import org.minxc.emp.form.model.FormDefData;

/**
 * <pre>
 * 描述：FormDefData的服务类
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年4月17日 下午4:43:41
 * 版权:summer
 * </pre>
 */
@Service
public class FormDefDataServiceImpl implements IFormDefDataService {
	@Autowired
	FormDefManager formDefManager;
	@Autowired
	BusinessObjectService businessObjectService;
	@Autowired
	BusinessTableService businessTableService;
	@Autowired
	BusinessPermissionService businessPermissionService;
	@Autowired
	BusinessDataService businessDataService;

	/**
	 * <pre>
	 * 根据表单key和表单数据主表的id
	 * </pre>
	 * 
	 * @param formDefKey
	 * @param id
	 * @return
	 */
	public FormDefData getByFormDefKey(String formDefKey, String id) {
		FormDefData formDefData = new FormDefData();
		FormDef formDef = formDefManager.getByKey(formDefKey);
		formDefData.setHtml(formDef.getHtml());
		
		BusinessPermission businessPermission = businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjectType.FORM.getKey(), formDef.getKey(), formDef.getBoKey(), true);
		
		formDefData.setPermission(businessPermission.getPermission(false));
		formDefData.setTablePermission(businessPermission.getTablePermission(false));
		
		handleInitData(formDef, formDefData);
		handleData(formDef, id, formDefData,businessPermission);

		return formDefData;
	}

	

	/**
	 * <pre>
	 * 处理boKey的初始化数据
	 * </pre>
	 * 
	 * @param formDef
	 * @param formDefData
	 */
	private void handleInitData(FormDef formDef, FormDefData formDefData) {
		if (formDefData.getInitData() == null) {
			formDefData.setInitData(new JSONObject());
		}
		JSONObject initData = formDefData.getInitData();
		BusinessObject businessObject = businessObjectService.getFilledByKey(formDef.getBoKey());

		initData.put(formDef.getBoKey(), new JSONObject());

		for (BusinessTableRel rel : businessObject.getRelation().list()) {
			initData.getJSONObject(formDef.getBoKey()).put(rel.getTableKey(), getInitData(rel));
		}
	}
	
	/**
	 * <pre>
	 * 获取初始化数据
	 * 
	 * </pre>
	 * 
	 * @param busTableRel
	 * @return
	 */
	private JSONObject getInitData(BusinessTableRel busTableRel) {
		JSONObject table = new JSONObject();
		table.putAll(busTableRel.getTable().initData());
		for (BusinessTableRel rel : busTableRel.getChildren()) {
			if (BusinessTableRelType.ONE_TO_ONE.equalsWithKey(rel.getType())) {
				// 递归处理一对一
				table.put(rel.getTableKey(), getInitData(rel));
			}
		}
		return table;
	}

	/**
	 * <pre>
	 * 处理initdata初始化数据和data数据
	 * </pre>
	 * 
	 * @param formDef
	 * @param id
	 *            bo对应数据的主键
	 * @param formDefData
	 *  businessPermission2
	 */
	private void handleData(FormDef formDef, String id, FormDefData formDefData, BusinessPermission businessPermission) {
		if (formDefData.getData() == null) {
			formDefData.setData(new JSONObject());
		}

		JSONObject data = formDefData.getData();
		BusinessObject businessObject = businessObjectService.getFilledByKey(formDef.getBoKey());
		businessObject.setPermission(businessPermission.getBusObjMap().get(formDef.getBoKey()));
		data.put(formDef.getBoKey(), businessDataService.getFormDefData(businessObject, id));
	}

}
