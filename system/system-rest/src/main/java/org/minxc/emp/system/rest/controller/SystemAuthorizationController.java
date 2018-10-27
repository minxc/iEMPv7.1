package org.minxc.emp.system.rest.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.basis.api.constant.RightsObjectConstants;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.util.JacksonUtil;
import org.minxc.emp.system.impl.manager.SystemAuthorizationManager;
import org.minxc.emp.system.impl.model.BizAuthorizationEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
 
@RestController
@RequestMapping("/sys/authorization")
public class SystemAuthorizationController extends GenericController{
	@Resource
	SystemAuthorizationManager sysAuthorizationManager;
	
	/**
	 * 保存授权结果
	 * @param request
	 * @param response
	 * @param commonAuthorization
	 * @throws Exception
	 */
	@ErrorCatching("对通用资源授权配置操作失败")
	@RequestMapping("saveAuthorization")
	public void saveAuthorization(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String targetId = RequestUtil.getString(request, "rightsTarget");
		String targetObject = RequestUtil.getString(request, "rightsObject");
		String authorizationJson = RequestUtil.getString(request, "authorizationJson");
			
		RightsObjectConstants.getByKey(targetObject);
		
		List<BizAuthorizationEntity> sysAuthorizationList = JSON.parseArray(authorizationJson, BizAuthorizationEntity.class);
//		List<BizAuthorizationEntity> sysAuthorizationList = JacksonUtil.jsonArray2PojoList(authorizationJson, BizAuthorizationEntity.class);
		
		sysAuthorizationManager.createAll(sysAuthorizationList,targetId,targetObject);
		
		writeSuccessResult(response, "授权成功");
	}
	
	/**
	 * 获取授权结果用来初始化
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAuthorizations")
	public void getAuthorizations(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String rightsTarget = request.getParameter("rightsTarget");
		String rightsTargetObject = RequestUtil.getString(request, "rightsObject");
		
		List<BizAuthorizationEntity> list = sysAuthorizationManager.getByTarget(RightsObjectConstants.valueOf(rightsTargetObject), rightsTarget);
		 
		writeSuccessData(response, list);
	}
}
