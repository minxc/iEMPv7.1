package org.minxc.emp.dao;

import org.minxc.emp.model.BpmTaskOpinionEntity;

public interface BpmTaskOpinionEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BpmTaskOpinionEntity record);

    int insertSelective(BpmTaskOpinionEntity record);

    BpmTaskOpinionEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BpmTaskOpinionEntity record);

    int updateByPrimaryKey(BpmTaskOpinionEntity record);
}