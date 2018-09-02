package org.minxc.emp.form.core.manager.impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.minxc.emp.biz.api.constant.BusTableRelType;
import org.minxc.emp.biz.api.model.IBusTableRel;
import org.minxc.emp.biz.api.model.IBusinessObject;
import org.minxc.emp.biz.api.service.IBusinessObjectService;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.form.core.dao.FormTemplateDao;
import org.minxc.emp.form.core.manager.FormTemplateManager;
import org.minxc.emp.form.core.model.FormTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minxc.emp.core.util.Dom4jUtil;
import com.minxc.emp.core.util.FileUtil;


/**
 * <pre>
 * 描述：FormTemplateManager的实现
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018年3月8日 下午3:16:32
 * 版权:summer
 * </pre>
 */
@Service
public class FormTemplateManagerImpl extends CommonManager<String, FormTemplate> implements FormTemplateManager{
	@Resource
	FormTemplateDao formTemplateDao;
	@Autowired
	IBusinessObjectService businessObjectService;
	
	/**
	 * 返回模版物理的路径。
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getFormTemplatePath() {
		try {
			return FileUtil.getClassesPath() + File.separator + "template" + File.separator + "form" + File.separator;
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public FormTemplate getByKey(String key) {
		QueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("key_", key, QueryOperator.EQUAL);
		return this.queryOne(filter);
	}

	@Override
	public void initAllTemplate() {
		// 删除不可编辑的（其实就是系统的）
		QueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("editable_", false, QueryOperator.EQUAL);
		for (FormTemplate template : this.query(filter)) {
			this.remove(template.getId());
		}

		initTemplate();
	}

	@Override
	// bean初始化时调用
	//@PostConstruct
	public void init() {
		if (this.getAll().isEmpty()) {
			initTemplate();
		}
	}

	private void initTemplate() {
		try {
			String templatePath = "/template/formDef/";
			InputStream instream = this.getClass().getResourceAsStream(templatePath+"templates.xml");
			String xml = IOUtils.toString(instream,"UTF-8");
			Document document = Dom4jUtil.loadXml(xml);
			Element root = document.getRootElement();
			List<Element> list = root.elements();
			for (Element element : list) {
				String key = element.attributeValue("key");
				String name = element.attributeValue("name");
				String type = element.attributeValue("type");
				String desc = element.attributeValue("desc");
				String dir = element.attributeValue("dir");

				String fileName = templatePath + dir + "/" + key + ".ftl";
				String html = IOUtils.toString(this.getClass().getResourceAsStream(fileName),"UTF-8");

				FormTemplate formTemplate = new FormTemplate();
				formTemplate.setId(UniqueIdUtil.getSuid());
				formTemplate.setHtml(html);
				formTemplate.setName(name);
				formTemplate.setKey(key);
				formTemplate.setEditable(false);
				formTemplate.setType(type);
				formTemplate.setFormType(dir);
				formTemplate.setDesc(desc);
				formTemplateDao.create(formTemplate);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	@Override
	public boolean isExist(String key) {
		return getByKey(key) != null;
	}

	@Override
	public void backUpTemplate(String id) {
		FormTemplate formTemplate = formTemplateDao.get(id);
		String alias = formTemplate.getKey();
		String name = formTemplate.getName();
		String desc = formTemplate.getDesc();
		String html = formTemplate.getHtml();
		String type = formTemplate.getType();

		String templatePath = getFormTemplatePath();

		String xmlPath = templatePath + "templates.xml";
		String xml = FileUtil.readFile(xmlPath);

		Document document = Dom4jUtil.loadXml(xml);
		Element root = document.getRootElement();

		Element e = root.addElement("template");
		e.addAttribute("alias", alias);
		e.addAttribute("name", name);
		e.addAttribute("type", type);
		e.addAttribute("templateDesc", desc);
		String content = document.asXML();

		FileUtil.writeFile(xmlPath, content);
		FileUtil.writeFile(templatePath + alias + ".ftl", html);

		formTemplate.setEditable(false);
		formTemplateDao.update(formTemplate);
	}

	public List<FormTemplate> getByType(String type, String formType) {
		QueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("type_", type, QueryOperator.IN);
		filter.addFilter("form_type_", formType, QueryOperator.EQUAL);
		return this.query(filter);
	}
	
	@Override
	public JSONArray templateData(String boKey,String type) {
		IBusinessObject bo = businessObjectService.getByKey(boKey);
		List<IBusTableRel> rels = (List<IBusTableRel>) bo.getRelation().list();
		List<FormTemplate> mainTemplates = getByType("main",type);
		List<FormTemplate> subTableTemplates = getByType("subTable",type);
		for (FormTemplate template : mainTemplates) {
			template.setHtml(null);
		}
		for (FormTemplate template : subTableTemplates) {
			template.setHtml(null);
		}

		JSONArray jsonArray = new JSONArray();
		for (IBusTableRel rel : rels) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("tableKey", rel.getTableKey());
			jsonObject.put("tableComment", rel.getTableComment());
			jsonObject.put("typeDesc", BusTableRelType.getByKey(rel.getType()).getDesc());
			if (BusTableRelType.MAIN.equalsWithKey(rel.getType())) {
				jsonObject.put("templates", JSON.toJSON(mainTemplates));
			} else {
				jsonObject.put("templates", JSON.toJSON(subTableTemplates));
			}
			jsonArray.add(jsonObject);
		}
		
		return jsonArray;
	}
	
}
