package org.minxc.emp.dao;

import org.minxc.emp.model.DataSourceEntity;

public interface DataSourceEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(DataSourceEntity record);

    int insertSelective(DataSourceEntity record);

    DataSourceEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DataSourceEntity record);

    int updateByPrimaryKeyWithBLOBs(DataSourceEntity record);

    int updateByPrimaryKey(DataSourceEntity record);
}