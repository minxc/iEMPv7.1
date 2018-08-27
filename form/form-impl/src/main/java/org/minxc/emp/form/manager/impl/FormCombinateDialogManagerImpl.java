package org.minxc.emp.form.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.db.model.query.DefaultQueryFilter;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.form.dao.FormCombinateDialogDao;
import org.minxc.emp.form.manager.FormCombinateDialogManager;
import org.minxc.emp.form.model.FormCombinateDialog;

/**
 * <pre>
 * 描述：combinate_dialog 处理实现类
 * </pre>
 */
@Service("combinateDialogManager")
public class FormCombinateDialogManagerImpl extends BaseManager<String, FormCombinateDialog> implements FormCombinateDialogManager {
    @Resource
    FormCombinateDialogDao combinateDialogDao;

    @Override
    public FormCombinateDialog getByAlias(String alias) {
        QueryFilter queryFilter = new DefaultQueryFilter();
        queryFilter.addFilter("alias_", alias, QueryOperation.EQUAL);
        List<FormCombinateDialog> combinateDialogs = query(queryFilter);
        if (combinateDialogs == null || combinateDialogs.isEmpty())
            return null;
        return combinateDialogs.get(0);
    }
}
