package org.minxc.emp.basis.impl.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.basis.impl.core.model.ResRole;
import org.minxc.emp.common.db.dao.CommonDao;


/**
 * 角色资源分配 DAO接口
 */
@Mapper
public interface ResRoleDao extends CommonDao<String, ResRole> {

    List<ResRole> getByRoleId(String roleId);

    void removeByRoleAndSystem(@Param("roleId")String roleId,@Param("systemId")String systemId);

    /**
     * 根据系统id获取关联的角色和URL资源。
     *
     * @param systemId
     * @return
     */
    List<ResRole> getUrlRoleBySystemId(String systemId);

    /**
     * 根据系统Id获取资源和角色的映射关系。
     *
     * @param systemId
     * @return
     */
    List<ResRole> getResRoleBySystemId(String systemId);
}
