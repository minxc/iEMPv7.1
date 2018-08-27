package org.minxc.emp.dao;

import org.minxc.emp.model.DataDictEntity;

public interface DataDictEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(DataDictEntity record);

    int insertSelective(DataDictEntity record);

    DataDictEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DataDictEntity record);

    int updateByPrimaryKey(DataDictEntity record);
}