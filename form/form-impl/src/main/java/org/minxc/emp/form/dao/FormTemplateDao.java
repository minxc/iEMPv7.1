package org.minxc.emp.form.dao;

import org.minxc.emp.form.model.FormTemplate;
import org.mybatis.spring.annotation.MapperScan;

import org.minxc.emp.base.dao.BaseDao;

@MapperScan
public interface FormTemplateDao extends BaseDao<String, FormTemplate> {
}
