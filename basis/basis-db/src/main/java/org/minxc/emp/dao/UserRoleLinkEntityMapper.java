package org.minxc.emp.dao;

import org.minxc.emp.model.UserRoleLinkEntity;

public interface UserRoleLinkEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRoleLinkEntity record);

    int insertSelective(UserRoleLinkEntity record);

    UserRoleLinkEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRoleLinkEntity record);

    int updateByPrimaryKey(UserRoleLinkEntity record);
}