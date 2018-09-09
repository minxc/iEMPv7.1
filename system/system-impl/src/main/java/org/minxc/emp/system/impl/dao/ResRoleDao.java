package org.minxc.emp.system.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.RoleResouceLinkEntity;


/**
 * 角色资源分配 DAO接口
 */
@Mapper
public interface ResRoleDao extends CommonDao<String, RoleResouceLinkEntity> {

    List<RoleResouceLinkEntity> getByRoleId(String roleId);

    void removeByRoleAndSystem(@Param("roleId")String roleId,@Param("systemId")String systemId);

    /**
     * 根据系统id获取关联的角色和URL资源。
     *
     * @param systemId
     * @return
     */
    List<RoleResouceLinkEntity> getUrlRoleBySystemId(String systemId);

    /**
     * 根据系统Id获取资源和角色的映射关系。
     *
     * @param systemId
     * @return
     */
    List<RoleResouceLinkEntity> getResRoleBySystemId(String systemId);
}
