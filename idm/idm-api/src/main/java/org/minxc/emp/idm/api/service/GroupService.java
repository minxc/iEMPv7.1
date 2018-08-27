package org.minxc.emp.idm.api.service;

import java.util.List;
import java.util.Map;

import org.minxc.emp.idm.api.model.GroupType;
import org.minxc.emp.idm.api.model.Group;

/**
 * 描述：用户与组服务接口
 */
public interface GroupService {


    /**
     * 根据用户ID和组类别获取相关的组。
     *
     * @param groupType 用户组类型
     * @param userId    用户ID
     * @return
     */
    List<Group> getGroupsByGroupTypeUserId(String groupType, String userId);

    /**
     * 根据用户账号获取用户当前所在的组。
     *
     * @param account 用户帐号
     * @return 返回一个Map，键为维度类型，值为组列表。
     */
    Map<String, List<Group>> getAllGroupByAccount(String account);


    /**
     * 获取用户所在的所有组织。
     *
     * @param userId 用户ID
     * @return 返回一个Map，键为维度类型，值为组列表。
     */
    Map<String, List<Group>> getAllGroupByUserId(String userId);


    /**
     * 根据用户获取用户所属的组。
     *
     * @param userId
     * @return
     */
    List<Group> getGroupsByUserId(String userId);


    /**
     * 根据组织ID和类型获取组织对象。
     *
     * @param groupType
     * @param groupId
     * @return
     */
    Group getById(String groupType, String groupId);


    /**
     * 根据组织ID和类型获取组织对象。
     *
     * @param groupType
     * @param code
     * @return
     */
    Group getByCode(String groupType, String code);

    /**
     * 返回当前的组织类型。
     *
     * @return
     */
    List<GroupType> getGroupTypes();


    Group getMainGroup(String userId);


}
