package org.minxc.emp.form.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.dao.CommonDao;
import org.minxc.emp.base.db.datasource.DbContextHolder;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.business.api.constant.BusinessPermissionObjectType;
import org.minxc.emp.business.api.model.BusinessObject;
import org.minxc.emp.business.api.model.BusinessPermission;
import org.minxc.emp.business.api.model.BusinessTable;
import org.minxc.emp.business.api.service.BusinessDataService;
import org.minxc.emp.business.api.service.BusinessObjectService;
import org.minxc.emp.business.api.service.BusinessPermissionService;
import org.minxc.emp.form.manager.FormDefManager;
import org.minxc.emp.form.model.FormDef;
import org.minxc.emp.form.model.FormDefData;
import org.minxc.emp.form.service.FormDefDataServiceImpl;
import org.minxc.emp.system.api2.model.SystemDataSource;
import org.minxc.emp.system.api2.service.SystemDataSourceService;

/**
 * <pre>
 * 描述：表单数据的controller
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年5月17日
 * 版权:summer
 * </pre>
 */
@RestController
@RequestMapping("/form/formDefData/")
public class FormDefDataController extends GenericController {

	@Autowired
	private FormDefDataServiceImpl formDefDataService;
	@Autowired
	private BusinessDataService businessDataService;
	@Autowired
	private BusinessObjectService businessObjectService;
	@Autowired
	private SystemDataSourceService sysDataSourceService;
	@Autowired
	private CommonDao<?> commonDao;
	@Autowired
	private BusinessPermissionService businessPermissionService;
	@Autowired
	private FormDefManager formDefManager;

	/**
	 * <pre>
	 * 获取FormDefData的后端
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getData")
	@CatchError(write2response = true, value = "获取FormDefData异常")
	public void getFormDefData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String key = RequestUtil.getString(request, "key");
		String id = RequestUtil.getString(request, "id");
		FormDefData formDefData = formDefDataService.getByFormDefKey(key, id);
		writeSuccessData(response, formDefData);
	}

	/**
	 * <pre>
	 * 保存formDef中的data数据
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @param data
	 * @throws Exception
	 */
	@RequestMapping("saveData")
	@CatchError(write2response = true, value = "保存formDef中的data数据异常")
	public void saveData(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject data) throws Exception {
		String key = RequestUtil.getString(request, "key");
		FormDef formDef = formDefManager.getByKey(key);
		BusinessPermission permission = businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjectType.FORM.getKey(), key, formDef.getBoKey(), true);
		businessDataService.saveFormDefData(data, permission);
		writeSuccessResult(response, "保存数据成功");
	}

	/**
	 * <pre>
	 * 获取bo的数据列表
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @param boKey
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getList_{boKey}")
	@CatchError(write2response = true, value = "获取对话框的列表数据失败")
	public PageJson getList(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "boKey") String boKey) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);
		// 页面来的参数
		BusinessObject businessObject = businessObjectService.getFilledByKey(boKey);
		BusinessTable businessTable = businessObject.getRelation().getTable();
		SystemDataSource sysDataSource = sysDataSourceService.getByKey(businessTable.getDsKey());
		// 切换数据源
		DbContextHolder.setDataSource(sysDataSource.getKey(), sysDataSource.getDbType());
		String sql = "select * from " + businessTable.getName();
		List<?> list = commonDao.queryForListPage(sql, queryFilter);
		return new PageJson(list);
	}

	@RequestMapping("removeData")
	@CatchError(write2response = true, value = "删除formDef中的data数据异常")
	public void removeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String boKey = RequestUtil.getString(request, "boKey");
		String key = RequestUtil.getString(request, "key");
		String id = RequestUtil.getString(request, "id");
		FormDef formDef = formDefManager.getByKey(key);
		BusinessPermission permission = businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjectType.FORM.getKey(), key,formDef.getBoKey(), true);
		BusinessObject businessObject = businessObjectService.getFilledByKey(boKey);
		businessObject.setPermission(permission.getBusObj(boKey));
		businessDataService.removeData(businessObject, id);
		writeSuccessResult(response, "删除数据成功");
	}

}
