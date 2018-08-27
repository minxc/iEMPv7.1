package org.minxc.emp.form.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.AppUtil;
import org.minxc.emp.base.core.util.PropertyUtil;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.business.api.model.BusinessTableRel;
import org.minxc.emp.business.api.model.BusinessObject;
import org.minxc.emp.business.api.service.BusinessObjectService;
import org.minxc.emp.business.api.service.BusinessTableService;
import org.minxc.emp.form.api.constant.FormStatusCode;
import org.minxc.emp.form.generator.AbsFormElementGenerator;
import org.minxc.emp.form.manager.FormDefManager;
import org.minxc.emp.form.manager.FormTemplateManager;
import org.minxc.emp.form.model.FormDef;
import org.minxc.emp.form.model.FormTemplate;
import org.minxc.emp.system.api.freemark.FreemarkEngine;
import com.github.pagehelper.Page;


/**
 * 表单管理
 * 
 */
@Slf4j
@RestController
@RequestMapping("/form/formDef/")
public class FormDefController extends BaseController<FormDef> {


	@Autowired
	private FormDefManager formDefManager;
	@Autowired
	private BusinessObjectService businessObjectService;
	@Autowired
	private BusinessTableService businessTableService;
	@Autowired
	private FormTemplateManager formTemplateManager;
	@Autowired
	private FreemarkEngine freemarkEngine;

	
	@Override
	@RequestMapping("listJson")
	public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);
		queryFilter.addFilter("type_", "mobile", QueryOperation.NOT_EQUAL);
	    Page<FormDef> pageList = (Page<FormDef>) formDefManager.query(queryFilter);
	      
        return new PageJson(pageList);
	}
	
	
	@RequestMapping("mobileListJson")
	public PageJson mobileListJson(HttpServletRequest request) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);
		queryFilter.addFilter("type_", "mobile", QueryOperation.EQUAL);
	    Page<FormDef> pageList = (Page<FormDef>) formDefManager.query(queryFilter);
	      
        return new PageJson(pageList);
	}
	
	/**
	 * formDefEdit.html的save后端
	 */
	@RequestMapping("save")
	@Override
	@CatchError(write2response = true, value = "保存表单失败")
	public ResultMsg<String> save(@RequestBody FormDef formDef) throws Exception {
		ResultMsg<String> msg =super.save( formDef);
		formDefManager.saveBackupHtml(formDef);
		return msg;
	}

	/**
	 * <pre>
	 * 获取formDef的后端
	 * 目前支持根据id和key 获取formDef
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getObject")
	@CatchError(write2response = true, value = "获取formDef异常")
	public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getString(request, "id");
		String key = RequestUtil.getString(request, "key");
		FormDef formDef = null;
		if (StringUtil.isNotEmpty(id)) {
			formDef = formDefManager.get(id);
		} else if (StringUtil.isNotEmpty(key)) {
			formDef = formDefManager.getByKey(key);
		}

		JSONObject json = formDef == null ? new JSONObject() : (JSONObject) JSON.toJSON(formDef);
		// 配置了备份路径则是开发者
		json.put("isDeveloper", StringUtil.isNotEmpty(PropertyUtil.getFormDefBackupPath()));

		writeSuccessData(response, json);
	}

	/**
	 * <pre>
	 * 获取开发者备份html
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getBackupHtml")
	@CatchError(write2response = true, value = "获取开发者备份html异常")
	public void getBackupHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getString(request, "id");
		String key = RequestUtil.getString(request, "key");
		FormDef formDef = null;
		if (StringUtil.isNotEmpty(id)) {
			formDef = formDefManager.get(id);
		} else if (StringUtil.isNotEmpty(key)) {
			formDef = formDefManager.getByKey(key);
		}
		writeSuccessData(response, formDefManager.getBackupHtml(formDef));
	}

	/**
	 * <pre>
	 * boTree数据树
	 * </pre>
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("boTreeData")
	@ResponseBody
	public Object boTreeData(@RequestParam String boKey) throws Exception {
		return businessObjectService.boTreeData(boKey);
	}

	/**
	 * <pre>
	 * 根据bo获取表单模板信息
	 * </pre>
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("templateData")
	@CatchError(write2response = true, value = "根据bo获取表单模板信息异常")
	public ResultMsg<JSONArray> templateData(@RequestParam String boKey, @RequestParam String type) throws Exception {
		
		JSONArray array = formTemplateManager.templateData(boKey,type);
		
		return new ResultMsg<JSONArray>(array);
	}

	/**
	 * <pre>
	 * 根据表单选择的模板生成html
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @param jsonArray
	 * @throws Exception
	 */
	@RequestMapping("createHtml")
	@CatchError(write2response = true, value = "生成html异常")
	public void createHtml(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONArray jsonArray) throws Exception {
		String boKey = RequestUtil.getString(request, "boKey");
		BusinessObject businessObject = businessObjectService.getFilledByKey(boKey);
		StringBuilder sb = new StringBuilder();
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject) object;
			BusinessTableRel relation = businessObject.getRelation().find(jsonObject.getString("tableKey"));
			FormTemplate template = formTemplateManager.getByKey(jsonObject.getString("templateKey"));
			if (template == null) {
				continue;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("relation", relation);
			
			//将所有表单生成器的实现类注入到模板引擎中
			for(AbsFormElementGenerator generator : AppUtil.getImplInstanceArray(AbsFormElementGenerator.class)) {
				map.put(generator.getGeneratorName(), generator);
			}
			
			String html ;
			try {
				 html = freemarkEngine.parseByString(template.getHtml(), map);
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
