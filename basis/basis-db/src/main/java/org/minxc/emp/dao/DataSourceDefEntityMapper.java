package org.minxc.emp.dao;

import org.minxc.emp.model.DataSourceDefEntity;

public interface DataSourceDefEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(DataSourceDefEntity record);

    int insertSelective(DataSourceDefEntity record);

    DataSourceDefEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DataSourceDefEntity record);

    int updateByPrimaryKeyWithBLOBs(DataSourceDefEntity record);

    int updateByPrimaryKey(DataSourceDefEntity record);
}