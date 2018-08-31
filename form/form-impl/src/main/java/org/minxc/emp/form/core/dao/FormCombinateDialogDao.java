package org.minxc.emp.form.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.form.core.model.FormCombinateDialog;


/**
 * combinate_dialog DAO接口
 */
@Mapper
public interface FormCombinateDialogDao extends CommonDao<String, FormCombinateDialog> {
}
