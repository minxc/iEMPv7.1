package org.minxc.emp.idm.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.idm.impl.model.RoleEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;

/**
 * 角色管理 DAO接口
 */
@Mapper
public interface RoleDao extends CommonDao<String, RoleEntity> {
    RoleEntity getByAlias(String alias);

    List<RoleEntity> getList(@Param("userId") String userId, @Param("account") String account);

    /**
     * 判断角色系统中是否存在。
     *
     * @param role
     * @return
     */
    Integer isRoleExist(RoleEntity role);
}
