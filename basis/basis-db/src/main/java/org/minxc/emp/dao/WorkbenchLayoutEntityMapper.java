package org.minxc.emp.dao;

import org.minxc.emp.model.WorkbenchLayoutEntity;

public interface WorkbenchLayoutEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkbenchLayoutEntity record);

    int insertSelective(WorkbenchLayoutEntity record);

    WorkbenchLayoutEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkbenchLayoutEntity record);

    int updateByPrimaryKey(WorkbenchLayoutEntity record);
}