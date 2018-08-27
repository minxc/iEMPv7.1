package org.minxc.emp.dao;

import org.minxc.emp.model.PositionEntity;

public interface PositionEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(PositionEntity record);

    int insertSelective(PositionEntity record);

    PositionEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PositionEntity record);

    int updateByPrimaryKey(PositionEntity record);
}