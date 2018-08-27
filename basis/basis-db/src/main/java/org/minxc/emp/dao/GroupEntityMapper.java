package org.minxc.emp.dao;

import org.minxc.emp.model.GroupEntity;

public interface GroupEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupEntity record);

    int insertSelective(GroupEntity record);

    GroupEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupEntity record);

    int updateByPrimaryKey(GroupEntity record);
}