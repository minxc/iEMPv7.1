package org.minxc.emp.form.core.manager.impl;

import java.io.File;

import javax.annotation.Resource;

import org.minxc.emp.form.core.dao.FormDefDao;
import org.minxc.emp.form.core.manager.FormDefManager;
import org.minxc.emp.form.core.model.FormDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.system.api.model.SystemTreeNode;
import org.minxc.emp.system.api.service.ISysTreeNodeService;

import com.minxc.emp.core.util.FileUtil;
import com.minxc.emp.core.util.PropertiesUtil;
import com.minxc.emp.core.util.StringUtil;
/**
 * 表单 Manager处理实现类
 *
 * @author aschs
 * @email aschs@qq.com
 * @time 2018-03-19 20:30:46
 */
@Service("formDefManager")
public class FormDefManagerImpl extends CommonManager<String, FormDef> implements FormDefManager {
	@Resource
	FormDefDao formDefDao;
	@Autowired
	ISysTreeNodeService sysTreeNodeService;

	@Override
	public FormDef getByKey(String key) {
		return formDefDao.getByKey(key);
	}

	@Override
	public void saveBackupHtml(FormDef formDef) {
		String formDefPath = PropertiesUtil.getFormDefBackupPath();
		if (StringUtil.isEmpty(formDefPath)) {
			return;
		}

		SystemTreeNode node = sysTreeNodeService.getById(formDef.getGroupId());
		String fileName = formDefPath + File.separator + node.getKey() + File.separator + formDef.getKey() + ".html";
		FileUtil.writeFile(fileName, formDef.getHtml());
	}

	@Override
	public String getBackupHtml(FormDef formDef) {
		String formDefPath = PropertiesUtil.getFormDefBackupPath();
		if (StringUtil.isNotEmpty(formDefPath)) {
			SystemTreeNode node = sysTreeNodeService.getById(formDef.getGroupId());
			String fileName = formDefPath + File.separator + node.getKey() + File.separator + formDef.getKey() + ".html";
			formDef.setHtml(FileUtil.readFile(fileName));
		}

		return formDef.getHtml();
	}

//	public static void main(String[] args) {
//		String str = FileUtil.readFile("D:\\projects\\dream\\agile-bpm\\modules\\agile-bpm-platform-new\\src\\main\\webapp\\form\\mrfl\\nh1.html");
//		System.out.println(str);
//	}
}
