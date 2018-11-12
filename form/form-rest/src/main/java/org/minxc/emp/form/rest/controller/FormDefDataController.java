package org.minxc.emp.form.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.biz.api.constant.BusinessPermissionObjType;
import org.minxc.emp.biz.api.model.IBusinessObject;
import org.minxc.emp.biz.api.model.IBusinessPermission;
import org.minxc.emp.biz.api.model.IBusinessTable;
import org.minxc.emp.biz.api.service.IBusinessDataService;
import org.minxc.emp.biz.api.service.IBusinessObjectService;
import org.minxc.emp.biz.api.service.IBusinessPermissionService;
import org.minxc.emp.common.db.dao.BasicDao;
import org.minxc.emp.common.db.datasource.DbContextHolder;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.form.core.manager.FormDefinitionManager;
import org.minxc.emp.form.core.model.FormDefinitionImpl;
import org.minxc.emp.form.core.model.FormDefinitionData;
import org.minxc.emp.form.core.service.FormDefDataServiceImpl;
import org.minxc.emp.system.api.model.SystemDataSource;
import org.minxc.emp.system.api.service.SystemDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


/**
 * 表单数据的controller
 * 日期:2018年5月17日
 */
@RestController
@RequestMapping("/form/formDefData/")
public class FormDefDataController extends GenericController {
	@Autowired
	FormDefDataServiceImpl formDefDataService;
	@Autowired
	IBusinessDataService businessDataService;
	@Autowired
	IBusinessObjectService businessObjectService;
	@Autowired
	SystemDataSourceService sysDataSourceService;
	@Autowired
	BasicDao<?> commonDao;
	@Autowired
	IBusinessPermissionService businessPermissionService;
	@Autowired
	FormDefinitionManager formDefManager;

	/**
	 * 
	 * 获取FormDefData的后端
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getData")
	@ErrorCatching(writeErrorToResponse = true, value = "获取FormDefData异常")
	public void getFormDefData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String key = RequestUtil.getString(request, "key");
		String id = RequestUtil.getString(request, "id");
		FormDefinitionData formDefData = formDefDataService.getByFormDefKey(key, id);
		writeSuccessData(response, formDefData);
	}

	/**
	 * 
	 * 保存formDef中的data数据
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param data
	 * @throws Exception
	 */
	@RequestMapping("saveData")
	@ErrorCatching(writeErrorToResponse = true, value = "保存formDef中的data数据异常")
	public void saveData(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject data) throws Exception {
		String key = RequestUtil.getString(request, "key");
		FormDefinitionImpl formDef = formDefManager.getByKey(key);
		IBusinessPermission permission = businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjType.FORM.getKey(), key, formDef.getBoKey(), true);
		businessDataService.saveFormDefData(data, permission);
		writeSuccessResult(response, "保存数据成功");
	}

	/**
	 * 
	 * 获取bo的数据列表
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getList_{boKey}")
	@ErrorCatching(writeErrorToResponse = true, value = "获取对话框的列表数据失败")
	public PageJson getList(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "boKey") String boKey) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);
		// 页面来的参数
		IBusinessObject businessObject = businessObjectService.getFilledByKey(boKey);
		IBusinessTable businessTable = businessObject.getRelation().getTable();
		SystemDataSource sysDataSource = sysDataSourceService.getByKey(businessTable.getDsKey());
		// 切换数据源
		DbContextHolder.setDataSource(sysDataSource.getKey(), sysDataSource.getDbType());
		String sql = "select * from " + businessTable.getName();
		List<?> list = commonDao.queryForListPage(sql, queryFilter);
		return new PageJson(list);
	}

	@RequestMapping("removeData")
	@ErrorCatching(writeErrorToResponse = true, value = "删除formDef中的data数据异常")
	public void removeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String boKey = RequestUtil.getString(request, "boKey");
		String key = RequestUtil.getString(request, "key");
		String id = RequestUtil.getString(request, "id");
		FormDefinitionImpl formDef = formDefManager.getByKey(key);
		IBusinessPermission permission = businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjType.FORM.getKey(), key,formDef.getBoKey(), true);
		IBusinessObject businessObject = businessObjectService.getFilledByKey(boKey);
		businessObject.setPermission(permission.getBusObj(boKey));
		businessDataService.removeData(businessObject, id);
		writeSuccessResult(response, "删除数据成功");
	}

}
