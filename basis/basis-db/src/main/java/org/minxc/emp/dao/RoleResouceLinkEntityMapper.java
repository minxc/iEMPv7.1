package org.minxc.emp.dao;

import org.minxc.emp.model.RoleResouceLinkEntity;

public interface RoleResouceLinkEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleResouceLinkEntity record);

    int insertSelective(RoleResouceLinkEntity record);

    RoleResouceLinkEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleResouceLinkEntity record);

    int updateByPrimaryKey(RoleResouceLinkEntity record);
}