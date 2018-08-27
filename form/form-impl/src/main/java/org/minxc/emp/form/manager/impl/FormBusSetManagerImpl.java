package org.minxc.emp.form.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.form.dao.FormBusSetDao;
import org.minxc.emp.form.manager.FormBusSetManager;
import org.minxc.emp.form.model.FormBusSet;

/**
 * <pre>
 * 描述：form_bus_set 处理实现类
 * </pre>
 */
@Service("formBusSetManager")
public class FormBusSetManagerImpl extends BaseManager<String, FormBusSet> implements FormBusSetManager {
    @Resource
    FormBusSetDao formBusSetDao;

    @Override
    public FormBusSet getByFormKey(String formKey) {
        return formBusSetDao.getByFormKey(formKey);
    }
}
