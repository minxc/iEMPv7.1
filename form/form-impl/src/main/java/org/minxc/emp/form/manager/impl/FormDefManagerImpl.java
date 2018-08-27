package org.minxc.emp.form.manager.impl;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.core.util.FileUtil;
import org.minxc.emp.base.core.util.PropertyUtil;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.model.query.DefaultQueryFilter;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.form.dao.FormDefDao;
import org.minxc.emp.form.manager.FormDefManager;
import org.minxc.emp.form.model.FormDef;
import org.minxc.emp.system.api2.model.SystemTreeNode;
import org.minxc.emp.system.api2.service.SystemTreeNodeService;

/**
 * 表单 Manager处理实现类
 *
 * @author min.xianchang
 * @email xianchangmin@126.com
 * @time 2018-03-19 20:30:46
 */
@Service("formDefManager")
public class FormDefManagerImpl extends BaseManager<String, FormDef> implements FormDefManager {
	@Resource
	private FormDefDao formDefDao;
	@Autowired
	private SystemTreeNodeService sysTreeNodeService;

	@Override
	public FormDef getByKey(String key) {
		return formDefDao.getByKey(key);
	}

	@Override
	public void saveBackupHtml(FormDef formDef) {
		String formDefPath = PropertyUtil.getFormDefBackupPath();
		if (StringUtil.isEmpty(formDefPath)) {
			return;
		}

		SystemTreeNode node = sysTreeNodeService.getById(formDef.getGroupId());
		String fileName = formDefPath + File.separator + node.getKey() + File.separator + formDef.getKey() + ".html";
		FileUtil.writeFile(fileName, formDef.getHtml());
	}

	@Override
	public String getBackupHtml(FormDef formDef) {
		String formDefPath = PropertyUtil.getFormDefBackupPath();
		if (StringUtil.isNotEmpty(formDefPath)) {
			SystemTreeNode node = sysTreeNodeService.getById(formDef.getGroupId());
			String fileName = formDefPath + File.separator + node.getKey() + File.separator + formDef.getKey() + ".html";
			formDef.setHtml(FileUtil.readFile(fileName));
		}

		return formDef.getHtml();
	}

	public static void main(String[] args) {
		String str = FileUtil.readFile("D:\\projects\\dream\\agile-bpm\\modules\\agile-bpm-platform-new\\src\\main\\webapp\\form\\mrfl\\nh1.html");
		System.out.println(str);
	}
}
