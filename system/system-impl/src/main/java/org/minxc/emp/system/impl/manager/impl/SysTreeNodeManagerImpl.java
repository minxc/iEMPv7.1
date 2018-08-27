package org.minxc.emp.system.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.api.query.SortDirection;
import org.minxc.emp.system.impl.dao.SysTreeNodeDao;
import org.minxc.emp.system.impl.manager.SysTreeNodeManager;
import org.minxc.emp.system.impl.model.SysTreeNode;
import org.springframework.stereotype.Service;


/**
 * 系统树节点 Manager处理实现类
 * @time 2018-03-13 20:02:33
 */
@Service("sysTreeNodeManager")
public class SysTreeNodeManagerImpl extends CommonManager<String, SysTreeNode> implements SysTreeNodeManager {
    @Resource
    SysTreeNodeDao sysTreeNodeDao;

    @Override
    public List<SysTreeNode> getByTreeId(String treeId) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("tree_id_", treeId, QueryOperator.EQUAL);
        filter.addFieldSort("sn_", SortDirection.ASC.getKey());
        return this.query(filter);
    }

    @Override
    public SysTreeNode getByTreeIdAndKey(String treeId, String key) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("tree_id_", treeId, QueryOperator.EQUAL);
        filter.addFilter("key_", key, QueryOperator.EQUAL);
        return this.queryOne(filter);
    }

    @Override
    public List<SysTreeNode> getByParentId(String parentId) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("parent_id_", parentId, QueryOperator.EQUAL);
        return this.query(filter);
    }

    @Override
    public List<SysTreeNode> getStartWithPath(String path) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("path_", path , QueryOperator.RIGHT_LIKE);
        return this.query(filter);
    }

    @Override
    public void removeByTreeId(String treeId) {
        sysTreeNodeDao.removeByTreeId(treeId);
    }
    
    @Override
    public void removeByPath(String path) {
    	sysTreeNodeDao.removeByPath(path);
    }
}
