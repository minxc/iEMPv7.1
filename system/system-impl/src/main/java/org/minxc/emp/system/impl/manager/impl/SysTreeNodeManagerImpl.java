package org.minxc.emp.system.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.api.query.SortDirection;
import org.minxc.emp.system.impl.dao.SysTreeNodeDao;
import org.minxc.emp.system.impl.manager.SystemTreeNodeManager;
import org.minxc.emp.system.impl.model.SystemTreeNodeEntity;
import org.springframework.stereotype.Service;


/**
 * 
* 项目名称：system-impl   
* 类名称：SysTreeNodeManagerImpl   
* 类描述： 系统树节点 Manager处理实现类  
* 创建人：Xianchang.min   
* 创建时间：2018年9月2日 下午4:07:05   
* 修改人：Xianchang.min   
* 修改时间：2018年9月2日 下午4:07:05   
* 修改备注：   
* @version  1.0  
*
 */
@Service("sysTreeNodeManager")
public class SysTreeNodeManagerImpl extends CommonManager<String, SystemTreeNodeEntity> implements SystemTreeNodeManager {
    @Resource
    SysTreeNodeDao sysTreeNodeDao;

    @Override
    public List<SystemTreeNodeEntity> getByTreeId(String treeId) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("tree_id_", treeId, QueryOperator.EQUAL);
        filter.addFieldSort("sn_", SortDirection.ASC.getKey());
        return this.query(filter);
    }

    @Override
    public SystemTreeNodeEntity getByTreeIdAndKey(String treeId, String key) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("tree_id_", treeId, QueryOperator.EQUAL);
        filter.addFilter("key_", key, QueryOperator.EQUAL);
        return this.queryOne(filter);
    }

    @Override
    public List<SystemTreeNodeEntity> getByParentId(String parentId) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("parent_id_", parentId, QueryOperator.EQUAL);
        return this.query(filter);
    }

    @Override
    public List<SystemTreeNodeEntity> getStartWithPath(String path) {
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
