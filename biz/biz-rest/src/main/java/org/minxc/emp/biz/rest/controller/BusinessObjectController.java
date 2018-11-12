package org.minxc.emp.biz.rest.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.model.BusinessObject;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * businessObject层的controller
 * 
 * 
 * 日期:下午5:11:06
 * 
 * 
 */
@RestController
@RequestMapping("/bus/businessObject/")
public class BusinessObjectController extends CommonController<BusinessObject> {
	@Resource
	BusinessObjectManager businessObjectManager;

	/**
	 * 
	 * 获取businessObject的后端
	 * 目前支持根据id,key 获取businessObject
	 * 
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getObject")
	@ErrorCatching(writeErrorToResponse = true, value = "获取businessObject异常")
	public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getString(request, "id");
		String key = RequestUtil.getString(request, "key");
		boolean fill = RequestUtil.getBoolean(request, "fill");// 是否要填充table
		BusinessObject object = null;
		if (StringUtils.isNotEmpty(id)) {
			object = businessObjectManager.get(id);
		} else if (StringUtils.isNotEmpty(key)) {
			object = businessObjectManager.getByKey(key);
		}
		if (fill && object != null) {
			object = businessObjectManager.getFilledByKey(object.getKey());
		}

		writeSuccessData(response, object);
	}

	@Override
	protected String getModelDesc() {
		return "业务对象";
	}

}
