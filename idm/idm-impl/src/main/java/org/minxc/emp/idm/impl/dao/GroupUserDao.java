package org.minxc.emp.idm.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.idm.impl.model.GroupUserEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;

/**
 * 描述：用户组织关系 DAO接口
 */
@Mapper
public interface GroupUserDao extends CommonDao<String, GroupUserEntity> {

    int updateUserPost(@Param("id") String id, @Param("relId") String relId);

    GroupUserEntity getGroupUser(@Param("groupId") String groupId, @Param("userId") String userId, @Param("relId") String relId);

    int removeByGroupIdUserId(@Param("groupId") String groupId, @Param("userId") String userId);

    List<GroupUserEntity> getListByGroupIdUserId(@Param("groupId") String groupId, @Param("userId") String userId);

    /**
     * 获取用户的主岗位组织关系
     * @return
     */
    GroupUserEntity getGroupUserMaster(String userId);

    /**
     * 设置主部门
     *
     * @param id
     * @return
     */
    Integer setMaster(String id);

    /**
     * 取消主部门。
     *
     * @param userId
     * @return
     */
    Integer cancelUserMasterGroup(String userId);

    /**
     * 根据组织和关系id获取用户列表。
     *
     * @param queryFilter
     * @return
     */
    List getUserByGroup(QueryFilter queryFilter);
}
