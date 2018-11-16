package org.minxc.emp.form.core.manager.impl;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.form.core.dao.FormBusinessSetDao;
import org.minxc.emp.form.core.manager.FormBusinessSetManager;
import org.minxc.emp.form.core.model.FormBusinessSet;
import org.springframework.stereotype.Service;


/**
 * 
 * form_bus_set 处理实现类
 * 
 */
@Service("formBusinessSetManager")
public class FormBusinessSetManagerImpl extends CommonManager<String, FormBusinessSet> implements FormBusinessSetManager {
    @Resource
    FormBusinessSetDao formBusinessSetDao;

    @Override
    public FormBusinessSet getByFormKey(String formKey) {
        return formBusinessSetDao.getByFormKey(formKey);
    }
}
