package org.minxc.emp.dao;

import org.minxc.emp.model.SystemSerialNOEntity;

public interface SystemSerialNOEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemSerialNOEntity record);

    int insertSelective(SystemSerialNOEntity record);

    SystemSerialNOEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemSerialNOEntity record);

    int updateByPrimaryKey(SystemSerialNOEntity record);
}