package org.minxc.emp.system.impl.manager;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.RoleResouceLinkEntity;

/**
 * 
 * 角色资源分配 处理接口
 * 
 */
public interface ResRoleManager extends Manager<String, RoleResouceLinkEntity> {

    List<RoleResouceLinkEntity> getAllByRoleId(String roleId);

    /**
     * 分配角色资源。
     *
     * @param resIds
     * @param systemId
     * @param roleId
     */
    void assignResByRoleSys(String resIds, String systemId, String roleId);

    /**
     * 根据系统id获取资源URL和角色的映射。
     *
     * @param systemId
     * @return
     */
    Map<String, Set<String>> getUrlRoleBySystem(String systemId);


    /**
     * 根据系统id获取资源和角色的映射。
     *
     * @param systemId
     * @return
     */
    Map<String, Set<String>> getResRoleBySystem(String systemId);

    /**
     * 清除缓存。
     *
     * @param systemId
     */
    void cleanResCache(String systemId);

}
