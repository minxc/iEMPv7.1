package org.minxc.emp.dao;

import org.minxc.emp.model.BpmTaskIdentityLinkEntity;

public interface BpmTaskIdentityLinkEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BpmTaskIdentityLinkEntity record);

    int insertSelective(BpmTaskIdentityLinkEntity record);

    BpmTaskIdentityLinkEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BpmTaskIdentityLinkEntity record);

    int updateByPrimaryKey(BpmTaskIdentityLinkEntity record);
}