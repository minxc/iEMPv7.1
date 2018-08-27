package org.minxc.emp.dao;

import org.minxc.emp.model.BizColumnEntity;

public interface BizColumnEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BizColumnEntity record);

    int insertSelective(BizColumnEntity record);

    BizColumnEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BizColumnEntity record);

    int updateByPrimaryKey(BizColumnEntity record);
}