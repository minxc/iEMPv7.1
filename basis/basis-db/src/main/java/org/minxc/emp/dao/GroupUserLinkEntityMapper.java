package org.minxc.emp.dao;

import org.minxc.emp.model.GroupUserLinkEntity;

public interface GroupUserLinkEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupUserLinkEntity record);

    int insertSelective(GroupUserLinkEntity record);

    GroupUserLinkEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupUserLinkEntity record);

    int updateByPrimaryKey(GroupUserLinkEntity record);
}