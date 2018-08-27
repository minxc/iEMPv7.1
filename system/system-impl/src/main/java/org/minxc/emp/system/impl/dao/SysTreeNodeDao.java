package org.minxc.emp.system.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.SysTreeNode;

/**
 * 系统树节点 DAO接口
 */
@Mapper
public interface SysTreeNodeDao extends CommonDao<String, SysTreeNode> {

    /**
     * <pre>
     * 根据树id删除节点
     * </pre>
     *
     * @param treeId
     */
    void removeByTreeId(String treeId);
    
    /**
     * <pre>
     * 删除path下的全部节点
     * </pre>
     *
     * @param path
     */
    void removeByPath(String path);

}
