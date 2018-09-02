package org.minxc.emp.form.core.manager.impl;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.form.core.dao.FormBusSetDao;
import org.minxc.emp.form.core.manager.FormBusSetManager;
import org.minxc.emp.form.core.model.FormBusSet;
import org.springframework.stereotype.Service;


/**
 * <pre>
 * 描述：form_bus_set 处理实现类
 * </pre>
 */
@Service("formBusSetManager")
public class FormBusSetManagerImpl extends CommonManager<String, FormBusSet> implements FormBusSetManager {
    @Resource
    FormBusSetDao formBusSetDao;

    @Override
    public FormBusSet getByFormKey(String formKey) {
        return formBusSetDao.getByFormKey(formKey);
    }
}
