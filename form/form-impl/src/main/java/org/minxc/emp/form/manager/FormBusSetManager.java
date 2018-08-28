package org.minxc.emp.form.manager;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.form.model.FormBusSet;

/**
 * 描述：form_bus_set 处理接口
 */
public interface FormBusSetManager extends Manager<String, FormBusSet> {

    FormBusSet getByFormKey(String formKey);

}
