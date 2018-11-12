package org.minxc.emp.form.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.form.core.dao.FormCombinateDialogDao;
import org.minxc.emp.form.core.manager.FormCombinateDialogManager;
import org.minxc.emp.form.core.model.FormCombinateDialog;
import org.springframework.stereotype.Service;


/**
 * 
 * combinate_dialog 处理实现类
 * 
 */
@Service("combinateDialogManager")
public class FormCombinateDialogManagerImpl extends CommonManager<String, FormCombinateDialog> implements FormCombinateDialogManager {
    @Resource
    FormCombinateDialogDao combinateDialogDao;

    @Override
    public FormCombinateDialog getByAlias(String alias) {
        QueryFilter queryFilter = new DefaultQueryFilter();
        queryFilter.addFilter("alias_", alias, QueryOperator.EQUAL);
        List<FormCombinateDialog> combinateDialogs = query(queryFilter);
        if (combinateDialogs == null || combinateDialogs.isEmpty())
            return null;
        return combinateDialogs.get(0);
    }
}
