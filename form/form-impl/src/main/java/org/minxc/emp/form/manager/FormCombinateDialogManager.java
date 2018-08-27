package org.minxc.emp.form.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.form.model.FormCombinateDialog;

/**
 * <pre>
 * 描述：combinate_dialog 处理接口
 * </pre>
 */
public interface FormCombinateDialogManager extends Manager<String, FormCombinateDialog> {
    FormCombinateDialog getByAlias(String alias);
}
