package org.minxc.emp.biz.rest.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.biz.core.manager.BusinessTableManager;
import org.minxc.emp.biz.core.model.BusinessTable;
import org.minxc.emp.biz.core.util.BusinessTableCacheUtil;
import org.minxc.emp.common.db.tableoper.TableOperator;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * businessTable层的controller
 *
 * @author aschs
 */
@RestController
@RequestMapping("/bus/businessTable/")
public class BusinessTableController extends CommonController<BusinessTable> {
	
	
	@Resource
	BusinessTableManager businessTableManager;

	/**
	 * 
	 * businessTableEdit.html的save后端
	 * 
	 *
	 * @param request
	 * @param response
	 * @param businessTable
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Override
	@ErrorCatching(value = "保存业务表失败")
	public ResultMessage<String> save(@RequestBody BusinessTable businessTable) throws Exception {
		businessTableManager.save(businessTable);
		return getSuccessResult("保存业务表成功");
	}

	/**
	 * 
	 * 获取BusinessTable的后端
	 * 目前支持根据id,key 获取BusinessTable
	 * 
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getObject")
	@ErrorCatching(writeErrorToResponse = true, value = "获取BusinessTable异常")
	public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getString(request, "id");
		String key = RequestUtil.getString(request, "key");
		boolean fill = RequestUtil.getBoolean(request, "fill");// 是否要填充table
		BusinessTable table = null;
		if (StringUtils.isNotEmpty(id)) {
			table = businessTableManager.get(id);
		} else if (StringUtils.isNotEmpty(key)) {
			table = businessTableManager.getByKey(key);
		}
		if (fill && table != null) {
			table = businessTableManager.getFilledByKey(table.getKey());
		}
		
		writeSuccessData(response, table);
	}


	/**
	 * 
	 * 新建表
	 * 
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("createTable")
	@ErrorCatching(writeErrorToResponse = true, value = "建表失败")
	public void createTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getString(request, "id");
		BusinessTable businessTable = businessTableManager.get(id);
		businessTable = businessTableManager.getFilledByKey(businessTable.getKey());
		TableOperator tableOperator = businessTableManager.newTableOperator(businessTable);
		tableOperator.createTable();
		businessTable.setCreatedTable(true);
		BusinessTableCacheUtil.put(businessTable);//入缓存
		writeSuccessResult(response, "建表成功");
	}

	@Override
	protected String getModelDesc() {
		return "业务表";
	}
}
