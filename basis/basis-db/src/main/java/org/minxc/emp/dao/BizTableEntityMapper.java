package org.minxc.emp.dao;

import org.minxc.emp.model. BizTableEntity;

public interface  BizTableEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert( BizTableEntity record);

    int insertSelective( BizTableEntity record);

     BizTableEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective( BizTableEntity record);

    int updateByPrimaryKey( BizTableEntity record);
}