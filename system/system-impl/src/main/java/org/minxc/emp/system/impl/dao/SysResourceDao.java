package org.minxc.emp.system.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.SystemResourceEntity;

/**
 * 子系统资源 DAO接口
 */
@Mapper
public interface SysResourceDao extends CommonDao<String, SystemResourceEntity> {
    /**
     * 根据子系统ID取定义对象。
     *
     * @param id
     * @return
     */
    List<SystemResourceEntity> getBySystemId(String systemId);

    /**
     * 根据角色和系统id获取资源。
     *
     * @param systemId
     * @param roleId
     * @return
     */
    List<SystemResourceEntity> getBySystemAndRole(@Param("systemId")String systemId, @Param("roleId")String roleId);

    /**
     * 判断资源是否存在。
     *
     * @param resource
     * @return
     */
    Integer isExist(SystemResourceEntity resource);

    /**
     * 根据父ID获取下级节点。
     *
     * @param parentId
     * @return
     */
    List<SystemResourceEntity> getByParentId(String parentId);

    /**
     * 根据系统id和用户id获取资源列表。
     *
     * @param systemId 系统id
     * @param userId   用户id
     * @return
     */
    List<SystemResourceEntity> getBySystemAndUser(@Param("systemId")String systemId, @Param("userId")String userId);

}
