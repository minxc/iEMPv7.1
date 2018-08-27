package org.minxc.emp.form.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.form.model.FormBusSet;

/**
 * <pre>
 * 描述：form_bus_set 处理接口
 * </pre>
 */
public interface FormBusSetManager extends Manager<String, FormBusSet> {

    FormBusSet getByFormKey(String formKey);

}
