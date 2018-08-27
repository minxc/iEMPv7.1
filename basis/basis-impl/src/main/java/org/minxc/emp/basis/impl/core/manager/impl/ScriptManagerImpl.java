package org.minxc.emp.basis.impl.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.basis.impl.core.dao.ScriptDao;
import org.minxc.emp.basis.impl.core.manager.ScriptManager;
import org.minxc.emp.basis.impl.core.model.Script;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;


@Service("scriptManager")
public class ScriptManagerImpl extends CommonManager<String, Script> implements ScriptManager {
	
    @Resource
    private ScriptDao scriptDao;

    @Override
    public List<String> getDistinctCategory() {
        return scriptDao.getDistinctCategory();
    }

    @Override
    public Integer isNameExists(String name) {
        return scriptDao.isNameExists(name);
    }

}
