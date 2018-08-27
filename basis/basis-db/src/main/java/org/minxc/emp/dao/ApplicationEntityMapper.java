package org.minxc.emp.dao;

import org.minxc.emp.model.ApplicationEntity;

public interface ApplicationEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(ApplicationEntity record);

    int insertSelective(ApplicationEntity record);

    ApplicationEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ApplicationEntity record);

    int updateByPrimaryKey(ApplicationEntity record);
}