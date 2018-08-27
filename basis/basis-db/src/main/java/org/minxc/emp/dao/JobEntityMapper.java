package org.minxc.emp.dao;

import org.minxc.emp.model.JobEntity;

public interface JobEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(JobEntity record);

    int insertSelective(JobEntity record);

    JobEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JobEntity record);

    int updateByPrimaryKey(JobEntity record);
}