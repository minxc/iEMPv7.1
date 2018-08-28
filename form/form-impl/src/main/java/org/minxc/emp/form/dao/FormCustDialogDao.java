package org.minxc.emp.form.dao;

import org.minxc.emp.form.model.FormCustDialog;
import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;

/**
 * form_cust_dialog DAO接口
 *
 * @author min.xianchang
 * @email xianchangmin@126.com
 * @time 2018-01-18 19:30:51
 */
@Mapper
public interface FormCustDialogDao extends CommonDao<String, FormCustDialog> {

}
