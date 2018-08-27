package org.minxc.emp.dao;

import org.minxc.emp.model.SystemScheduleJobEntity;

public interface SystemScheduleJobEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemScheduleJobEntity record);

    int insertSelective(SystemScheduleJobEntity record);

    SystemScheduleJobEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemScheduleJobEntity record);

    int updateByPrimaryKey(SystemScheduleJobEntity record);
}