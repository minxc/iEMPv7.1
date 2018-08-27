package org.minxc.emp.dao;

import org.minxc.emp.model.BpmTaskStackEntity;

public interface BpmTaskStackEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BpmTaskStackEntity record);

    int insertSelective(BpmTaskStackEntity record);

    BpmTaskStackEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BpmTaskStackEntity record);

    int updateByPrimaryKey(BpmTaskStackEntity record);
}