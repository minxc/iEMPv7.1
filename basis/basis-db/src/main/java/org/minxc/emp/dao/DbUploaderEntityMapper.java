package org.minxc.emp.dao;

import org.minxc.emp.model.DbUploaderEntity;

public interface DbUploaderEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(DbUploaderEntity record);

    int insertSelective(DbUploaderEntity record);

    DbUploaderEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DbUploaderEntity record);

    int updateByPrimaryKeyWithBLOBs(DbUploaderEntity record);
}