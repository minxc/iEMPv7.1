package org.minxc.emp.form.manager.impl;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minxc.emp.core.util.FileUtil;
import com.minxc.emp.core.util.PropertiesUtil;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.form.dao.FormDefDao;
import org.minxc.emp.form.manager.FormDefManager;
import org.minxc.emp.form.model.FormDef;
import org.minxc.emp.system.api.model.ISysTreeNode;
import org.minxc.emp.system.api.service.ISysTreeNodeService;

/**
 * 表单 Manager处理实现类
 *
 * @author min.xianchang
 * @email xianchangmin@126.com
 * @time 2018-03-19 20:30:46
 */
@Service("formDefManager")
public class FormDefManagerImpl extends CommonManager<String, FormDef> implements FormDefManager {
	@Resource
	private FormDefDao formDefDao;
	@Autowired
	private ISysTreeNodeService sysTreeNodeService;

	@Override
	public FormDef getByKey(String key) {
		return formDefDao.getByKey(key);
	}

	@Override
	public void saveBackupHtml(FormDef formDef) {
		String formDefPath = PropertiesUtil.getFormDefBackupPath();
		if (StringUtils.isEmpty(formDefPath)) {
			return;
		}

		ISysTreeNode node = sysTreeNodeService.getById(formDef.getGroupId());
		String fileName = formDefPath + File.separator + node.getKey() + File.separator + formDef.getKey() + ".html";
		FileUtil.writeFile(fileName, formDef.getHtml());
	}

	@Override
	public String getBackupHtml(FormDef formDef) {
		String formDefPath = PropertiesUtil.getFormDefBackupPath();
		if (StringUtils.isNotEmpty(formDefPath)) {
			ISysTreeNode node = sysTreeNodeService.getById(formDef.getGroupId());
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
