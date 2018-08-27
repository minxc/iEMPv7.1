package org.minxc.emp.idm.impl.manager;

import org.minxc.emp.idm.impl.model.UserEntity;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.QueryFilter;

import java.util.List;

/**
 * <pre>
 * 描述：用户表 处理接口
 * </pre>
 */
public interface UserManager extends Manager<String, UserEntity> {
    /**
     * 根据Account取定义对象。
     *
     * @param account
     * @return
     */
    UserEntity getByAccount(String account);

    /**
     * 不含用户组织关系
     */
    List<UserEntity> getUserListByOrgId(String orgId);

    /**
     * 不含用户组织关系
     *
     * @param queryFilter
     * @return
     */
    List<UserEntity> queryOrgUser(QueryFilter queryFilter);

    /**
     * 含组织用户关系表数据
     *
     * @param queryFilter
     * @return
     */
    List queryUserGroupRel(QueryFilter queryFilter);

    /**
     * 根据岗位编码获取用户列表
     *
     * @param relCode
     * @return
     */
    List<UserEntity> getListByRelCode(String relCode);

    /**
     * 根据岗位ID获取用户列表
     *
     * @param relCode
     * @return
     */
    List<UserEntity> getListByRelId(String relId);

    /**
     * 根据角色ID获取用户列表
     *
     * @param roleId
     * @return
     */
    List<UserEntity> getUserListByRoleId(String roleId);

    /**
     * 根据角色Code获取用户列表
     *
     * @param roleId
     * @return
     */
    List<UserEntity> getUserListByRoleCode(String roleCode);

    /**
     * 判断系统中用户是否存在。
     *
     * @param user
     * @return
     */
    boolean isUserExist(UserEntity user);
}
