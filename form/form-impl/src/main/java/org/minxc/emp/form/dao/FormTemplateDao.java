package org.minxc.emp.form.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.form.model.FormTemplate;


@Mapper
public interface FormTemplateDao extends CommonDao<String, FormTemplate> {
}
