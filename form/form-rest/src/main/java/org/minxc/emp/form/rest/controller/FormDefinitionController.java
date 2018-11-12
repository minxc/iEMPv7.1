package org.minxc.emp.form.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.basis.api.freemarker.FreeMarkerEngine;
import org.minxc.emp.biz.api.model.IBusTableRel;
import org.minxc.emp.biz.api.model.IBusinessObject;
import org.minxc.emp.biz.api.service.BusinessObjectService;
import org.minxc.emp.biz.api.service.BusinessTableService;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.PropertiesUtil;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.form.api.constant.FormStatusCode;
import org.minxc.emp.form.core.generator.AbsFormElementGenerator;
import org.minxc.emp.form.core.generator.PcFormElementGenerator;
import org.minxc.emp.form.core.generator.MobileFormElementGenerator;
import org.minxc.emp.form.core.manager.FormDefinitionManager;
import org.minxc.emp.form.core.manager.FormTemplateManager;
import org.minxc.emp.form.core.model.FormDefinitionImpl;
import org.minxc.emp.form.core.model.FormTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

import lombok.extern.slf4j.Slf4j;

/**
 * 表单管理
 * 
 */
@RestController
@RequestMapping("/form/formDef/")
@Slf4j
public class FormDefinitionController extends CommonController<FormDefinitionImpl> {
	
	@Autowired
	private FormDefinitionManager formDefManager;
	@Autowired
	private BusinessObjectService businessObjectService;
	@Autowired
	private BusinessTableService businessTableService;
	@Autowired
	private FormTemplateManager formTemplateManager;
	@Autowired
	private FreeMarkerEngine freemarkerEngine;

	
	@Override
	@RequestMapping("listJson")
	public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);
		queryFilter.addFilter("type_", "mobile", QueryOperator.NOT_EQUAL);
	    Page<FormDefinitionImpl> pageList = (Page<FormDefinitionImpl>) formDefManager.query(queryFilter);
	      
        return new PageJson(pageList);
	}
	
	
	@RequestMapping("mobileListJson")
	public PageJson mobileListJson(HttpServletRequest request) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);
		queryFilter.addFilter("type_", "mobile", QueryOperator.EQUAL);
	    Page<FormDefinitionImpl> pageList = (Page<FormDefinitionImpl>) formDefManager.query(queryFilter);
	      
        return new PageJson(pageList);
	}
	
	/**
	 * formDefEdit.html的save后端
	 */
	@RequestMapping("save")
	@Override
	@ErrorCatching(writeErrorToResponse = true, value = "保存表单失败")
	public ResultMessage<String> save(@RequestBody FormDefinitionImpl formDef) throws Exception {
		ResultMessage<String> msg =super.save( formDef);
		formDefManager.saveBackupHtml(formDef);
		return msg;
	}

	/**
	 * 
	 * 获取formDef的后端
	 * 目前支持根据id和key 获取formDef
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getObject")
	@ErrorCatching(writeErrorToResponse = true, value = "获取formDef异常")
	public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getString(request, "id");
		String key = RequestUtil.getString(request, "key");
		FormDefinitionImpl formDef = null;
		if (StringUtil.isNotEmpty(id)) {
			formDef = formDefManager.get(id);
		} else if (StringUtil.isNotEmpty(key)) {
			formDef = formDefManager.getByKey(key);
		}

		JSONObject json = formDef == null ? new JSONObject() : (JSONObject) JSON.toJSON(formDef);
		// 配置了备份路径则是开发者
		json.put("isDeveloper", StringUtil.isNotEmpty(PropertiesUtil.getFormDefBackupPath()));

		writeSuccessData(response, json);
	}

	/**
	 * 
	 * 获取开发者备份html
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getBackupHtml")
	@ErrorCatching(writeErrorToResponse = true, value = "获取开发者备份html异常")
	public void getBackupHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getString(request, "id");
		String key = RequestUtil.getString(request, "key");
		FormDefinitionImpl formDef = null;
		if (StringUtil.isNotEmpty(id)) {
			formDef = formDefManager.get(id);
		} else if (StringUtil.isNotEmpty(key)) {
			formDef = formDefManager.getByKey(key);
		}
		writeSuccessData(response, formDefManager.getBackupHtml(formDef));
	}

	/**
	 * 
	 * boTree数据树
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("boTreeData")
	@ResponseBody
	public Object boTreeData(@RequestParam String boKey) throws Exception {
		return businessObjectService.boTreeData(boKey);
	}

	/**
	 * 
	 * 根据bo获取表单模板信息
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("templateData")
	@ErrorCatching(writeErrorToResponse = true, value = "根据bo获取表单模板信息异常")
	public ResultMessage<JSONArray> templateData(@RequestParam String boKey, @RequestParam String type) throws Exception {
		
		JSONArray array = formTemplateManager.templateData(boKey,type);
		
		return new ResultMessage<JSONArray>(array);
	}

	/**
	 * 
	 * 根据表单选择的模板生成html
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param jsonObject
	 * @throws Exception
	 */
	@RequestMapping("createHtml")
	@ErrorCatching(writeErrorToResponse = true, value = "生成html异常")
	public void createHtml(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONArray jsonArray) throws Exception {
		String boKey = RequestUtil.getString(request, "boKey");
		IBusinessObject businessObject = businessObjectService.getFilledByKey(boKey);
		StringBuilder sb = new StringBuilder();
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject) object;
			IBusTableRel relation = businessObject.getRelation().find(jsonObject.getString("tableKey"));
			FormTemplate template = formTemplateManager.getByKey(jsonObject.getString("templateKey"));
			if (template == null) {
				continue;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("relation", relation);
			
			//将所有表单生成器的实现类注入到模板引擎中
			for(AbsFormElementGenerator generator : AppContextUtil.getImplInstanceArray(AbsFormElementGenerator.class)) {
				map.put(generator.getGeneratorName(), generator);
			}
			
			String html ;
			try {
				 html = freemarkerEngine.parseByString(template.getHtml(), map);
			} catch (Exception e) {
				log.error(String.format( "业务表【%s】模板【%s】解析失败：%s", relation.getTableComment(),template.getDesc(),e.getMessage()));
				throw new BusinessException(String.format( "业务表【%s】模板【%s】解析失败：%s", relation.getTableComment(),template.getDesc(),e.getMessage()),FormStatusCode.FORM_ELEMENT_GENERATOR_ERROR,e);
			}
			
			sb.append(html);
			sb.insert(0, "<div>");
			sb.append("</div>");
		}
		writeSuccessData(response, sb.toString());
	}

	@Override
	protected String getModelDesc() {
		return "自定义表单";
	}

}
