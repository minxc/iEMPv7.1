package org.minxc.emp.dao;

import org.minxc.emp.model.SystemScheduleJobLogEntity;

public interface SystemScheduleJobLogEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemScheduleJobLogEntity record);

    int insertSelective(SystemScheduleJobLogEntity record);

    SystemScheduleJobLogEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemScheduleJobLogEntity record);

    int updateByPrimaryKeyWithBLOBs(SystemScheduleJobLogEntity record);

    int updateByPrimaryKey(SystemScheduleJobLogEntity record);
}