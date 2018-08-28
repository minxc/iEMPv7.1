package org.minxc.emp.form.manager;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.form.model.FormCombinateDialog;

/**
 * combinate_dialog 处理接口
 */
public interface FormCombinateDialogManager extends Manager<String, FormCombinateDialog> {
    FormCombinateDialog getByAlias(String alias);
}
