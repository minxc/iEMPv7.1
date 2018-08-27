package org.minxc.emp.form.dao;

import org.minxc.emp.form.model.FormCustDialog;
import org.mybatis.spring.annotation.MapperScan;

import org.minxc.emp.base.dao.BaseDao;

/**
 * form_cust_dialog DAO接口
 *
 * @author min.xianchang
 * @email xianchangmin@126.com
 * @time 2018-01-18 19:30:51
 */
@MapperScan
public interface FormCustDialogDao extends BaseDao<String, FormCustDialog> {

}
