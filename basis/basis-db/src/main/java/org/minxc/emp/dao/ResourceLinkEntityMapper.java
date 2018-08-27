package org.minxc.emp.dao;

import org.minxc.emp.model.ResourceLinkEntity;

public interface ResourceLinkEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResourceLinkEntity record);

    int insertSelective(ResourceLinkEntity record);

    ResourceLinkEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResourceLinkEntity record);

    int updateByPrimaryKey(ResourceLinkEntity record);
}