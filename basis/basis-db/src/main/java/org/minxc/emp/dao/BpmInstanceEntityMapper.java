package org.minxc.emp.dao;

import org.minxc.emp.model.BpmInstanceEntity;

public interface BpmInstanceEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BpmInstanceEntity record);

    int insertSelective(BpmInstanceEntity record);

    BpmInstanceEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BpmInstanceEntity record);

    int updateByPrimaryKey(BpmInstanceEntity record);
}