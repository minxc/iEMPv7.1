package org.minxc.emp.form.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.form.core.model.FormTemplate;

@Mapper
public interface FormTemplateDao extends CommonDao<String, FormTemplate> {
}
