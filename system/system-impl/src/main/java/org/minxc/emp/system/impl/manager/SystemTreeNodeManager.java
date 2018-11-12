package org.minxc.emp.system.impl.manager;

import java.util.List;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.SystemTreeNodeEntity;

/**
 * 系统树节点 Manager处理接口
 */
public interface SystemTreeNodeManager extends Manager<String, SystemTreeNodeEntity> {

    /**
     * 
     * 根据树id获取节点
     * 根据sn字段升序
     * 
     *
     * @param treeId
     * @return
     */
    List<SystemTreeNodeEntity> getByTreeId(String treeId);

    /**
     * 
     * 获取指定树下的指定节点
     * 
     *
     * @param treeId
     * @param key
     * @return
     */
    SystemTreeNodeEntity getByTreeIdAndKey(String treeId, String key);

    /**
     * 
     * 根据父节点获取其子节点
     * 不会进行递归查询，只获取第一层
     * 
     *
     * @param parentId
     * @return
     */
    List<SystemTreeNodeEntity> getByParentId(String parentId);

    /**
     * 
     * 获取以path开始的路径
     * 
     *
     * @param path
     * @return
     */
    List<SystemTreeNodeEntity> getStartWithPath(String path);

    /**
     * 
     * 根据树id删除节点
     * 
     *
     * @param treeId
     */
    void removeByTreeId(String treeId);
    
    /**
     * 
     * 删除path下的全部节点
     * 
     *
     * @param path
     */
	void removeByPath(String path);
}
