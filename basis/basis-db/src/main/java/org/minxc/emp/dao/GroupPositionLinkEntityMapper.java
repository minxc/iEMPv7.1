package org.minxc.emp.dao;

import org.minxc.emp.model.GroupPositionLinkEntity;

public interface GroupPositionLinkEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupPositionLinkEntity record);

    int insertSelective(GroupPositionLinkEntity record);

    GroupPositionLinkEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupPositionLinkEntity record);

    int updateByPrimaryKey(GroupPositionLinkEntity record);
}