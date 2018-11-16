package org.minxc.emp.form.core.manager;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.form.core.model.FormBusinessSet;


/**
 * form_bus_set 处理接口
 */
public interface FormBusinessSetManager extends Manager<String, FormBusinessSet> {

    FormBusinessSet getByFormKey(String formKey);

}
