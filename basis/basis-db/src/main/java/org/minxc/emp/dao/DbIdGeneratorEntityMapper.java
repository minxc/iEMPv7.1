package org.minxc.emp.dao;

import org.minxc.emp.model.DbIdGeneratorEntity;

public interface DbIdGeneratorEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(DbIdGeneratorEntity record);

    int insertSelective(DbIdGeneratorEntity record);

    DbIdGeneratorEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DbIdGeneratorEntity record);

    int updateByPrimaryKey(DbIdGeneratorEntity record);
}