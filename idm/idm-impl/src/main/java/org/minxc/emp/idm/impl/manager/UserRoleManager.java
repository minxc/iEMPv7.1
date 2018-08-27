package org.minxc.emp.idm.impl.manager;

import org.minxc.emp.idm.impl.model.UserRoleEntity;
import org.minxc.emp.common.manager.Manager;

import java.util.List;

/**
 * <pre>
 * 描述：用户角色管理 处理接口
 * </pre>
 */
public interface UserRoleManager extends Manager<String, UserRoleEntity> {

    /**
     * 根据用户和角色id 查询 关联关系。
     *
     * @param roleId
     * @param userId
     * @return
     */
    UserRoleEntity getByRoleIdUserId(String roleId, String userId);

    /**
     * 获取用户的角色。
     *
     * @param userId
     * @return
     */
    List<UserRoleEntity> getListByUserId(String userId);

    /**
     * 根据角色ID查询关联的用户。
     *
     * @param roleId
     * @return
     */
    List<UserRoleEntity> getListByRoleId(String roleId);

    /**
     * 根据角色别名查询关联的用户。
     *
     * @param roleId
     * @return
     */
    List<UserRoleEntity> getListByAlias(String alias);
}
