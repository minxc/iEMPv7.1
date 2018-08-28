package org.minxc.emp.form.dao;

import org.minxc.emp.form.model.FormBusSet;
import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;

/**
 * 描述：表单业务数据保存设置 DAO
 */
@Mapper
public interface FormBusSetDao extends CommonDao<String, FormBusSet> {

    FormBusSet getByFormKey(String formKey);

    /**
     * 判断业务数据保存设置是否存在。
     *
     * @param formSet
     * @return
     */
    Integer isExist(FormBusSet formSet);

    /**
     * 根据表单键删除业务数据设置。
     *
     * @param formKey
     */
    void removeByFormKey(String formKey);
}
