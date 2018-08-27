package org.minxc.emp.form.dao;

import org.minxc.emp.form.model.FormDef;
import org.mybatis.spring.annotation.MapperScan;

import org.minxc.emp.base.dao.BaseDao;

/**
 * 表单 DAO接口
 *
 * @author min.xianchang
 * @email xianchangmin@126.com
 * @time 2018-03-19 20:30:46
 */
@MapperScan
public interface FormDefDao extends BaseDao<String, FormDef> {

	FormDef getByKey(String key);

}
