package org.minxc.emp.idm.api.service;

import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.model.UserRole;

import java.util.List;

/**
 * 用户服务接口类
 */
public interface UserService {

    /**
     * 根据用户ID获取用户的对象。
     *
     * @param userId 用户ID
     * @return
     */
    User getUserById(String userId);


    /**
     * 根据用户帐号获取用户对象。
     *
     * @param account
     * @return
     */
    User getUserByAccount(String account);


    /**
     * 根据组织id和组织类型获取用户列表。
     *
     * @param groupId   组织列表
     * @param groupType 组织类型
     * @return
     */
    List<User> getUserListByGroup(String groupType, String groupId);
    
    /**
     * 获取用户的角色关系
     * @param userId
     * @return
     */
	List<UserRole> getUserRole(String userId);


}
