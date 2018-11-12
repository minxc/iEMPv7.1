package org.minxc.emp.form.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.form.core.model.FormCustomDialog;


/**
 * form_cust_dialog DAO接口
 *
 * @author aschs
 * @email aschs@qq.com
 * @time 2018-01-18 19:30:51
 */
@Mapper
public interface FormCustDialogDao extends CommonDao<String, FormCustomDialog> {

}
