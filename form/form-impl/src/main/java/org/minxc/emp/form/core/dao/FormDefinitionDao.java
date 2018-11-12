package org.minxc.emp.form.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.form.core.model.FormDefinitionImpl;


/**
 * 表单 DAO接口
 *
 * @time 2018-03-19 20:30:46
 */
@Mapper
public interface FormDefinitionDao extends CommonDao<String, FormDefinitionImpl> {

	FormDefinitionImpl getByKey(String key);

}
