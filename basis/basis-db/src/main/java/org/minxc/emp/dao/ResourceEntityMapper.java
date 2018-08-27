package org.minxc.emp.dao;

import org.minxc.emp.model.ResourceEntity;

public interface ResourceEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResourceEntity record);

    int insertSelective(ResourceEntity record);

    ResourceEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResourceEntity record);

    int updateByPrimaryKey(ResourceEntity record);
}