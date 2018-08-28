package org.minxc.emp.form.dao;

import org.minxc.emp.form.model.FormDef;
import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;

/**
 * 表单 DAO接口
 *
 * @author min.xianchang
 * @email xianchangmin@126.com
 * @time 2018-03-19 20:30:46
 */
@Mapper
public interface FormDefDao extends CommonDao<String, FormDef> {

	FormDef getByKey(String key);

}
