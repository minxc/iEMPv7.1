package org.minxc.emp.biz.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.manager.BusinessPermissionManager;
import org.minxc.emp.biz.core.model.BusinessObjectImpl;
import org.minxc.emp.biz.core.model.BusinessPermissionImpl;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * businessPermission层的controller
 * 
 * 
 * 日期:下午5:11:06
 * 
 * 
 */
@RestController
@RequestMapping("/bus/businessPermission/")
public class BusinessPermissionController extends CommonController<BusinessPermissionImpl> {
	@Resource
	BusinessObjectManager businessObjectManager;
	@Autowired
	BusinessPermissionManager businessPermissionManager;

	/**
	 * 
	 * 获取businessPermission的后端
	 * 
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getObject")
	@ErrorCatching(writeErrorToResponse = true, value = "获取businessPermission异常")
	public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String objType = RequestUtil.getString(request, "objType");
		String objVal = RequestUtil.getString(request, "objVal");
		BusinessPermissionImpl businessPermission = businessPermissionManager.getByObjTypeAndObjVal(objType, objVal);
		writeSuccessData(response, businessPermission);
	}

	/**
	 * 
	 * 获取bo数据的后端
	 * 
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getBo")
	@ErrorCatching(writeErrorToResponse = true, value = "获取boo异常")
	public void getBo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] boKeys = RequestUtil.getStringAryByStr(request, "boKeys");
		
		Map<String, BusinessObjectImpl> boMap = new HashMap<>();
		for (String boKey : boKeys) {
			BusinessObjectImpl bo = businessObjectManager.getFilledByKey(boKey);
			boMap.put(boKey, bo);
		}
		writeSuccessData(response, boMap);
	}

	@Override
	protected String getModelDesc() {
		return "业务对象权限";
	}

}
