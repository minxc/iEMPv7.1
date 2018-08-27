package org.minxc.emp.dao;

import org.minxc.emp.model.SystemLogErrorEntity;
import org.minxc.emp.model.SystemLogErrorEntityWithBLOBs;

public interface SystemLogErrorEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemLogErrorEntityWithBLOBs record);

    int insertSelective(SystemLogErrorEntityWithBLOBs record);

    SystemLogErrorEntityWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemLogErrorEntityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SystemLogErrorEntityWithBLOBs record);

    int updateByPrimaryKey(SystemLogErrorEntity record);
}