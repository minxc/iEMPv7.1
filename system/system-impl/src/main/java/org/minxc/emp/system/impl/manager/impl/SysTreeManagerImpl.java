package org.minxc.emp.system.impl.manager.impl;

import javax.annotation.Resource;

import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.system.impl.dao.SysTreeDao;
import org.minxc.emp.system.impl.manager.SysTreeManager;
import org.minxc.emp.system.impl.manager.SysTreeNodeManager;
import org.minxc.emp.system.impl.model.SysTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 系统树 Manager处理实现类
 */
@Service("sysTreeManager")
public class SysTreeManagerImpl extends CommonManager<String, SysTree> implements SysTreeManager {
    @Resource
    SysTreeDao sysTreeDao;
    @Autowired
    SysTreeNodeManager sysTreeNodeManager;

    @Override
    public SysTree getByKey(String key) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("key_", key, QueryOperator.EQUAL);
        return this.queryOne(filter);
    }

    @Override
    public void removeContainNode(String id) {
        this.remove(id);
        sysTreeNodeManager.removeByTreeId(id);
    }
}
