package org.minxc.emp.dao;

import org.minxc.emp.model.BpmTaskEntity;

public interface BpmTaskEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BpmTaskEntity record);

    int insertSelective(BpmTaskEntity record);

    BpmTaskEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BpmTaskEntity record);

    int updateByPrimaryKey(BpmTaskEntity record);
}