package org.minxc.emp.dao;

import org.minxc.emp.model.BizColumnControlEntity;

public interface BizColumnControlEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BizColumnControlEntity record);

    int insertSelective(BizColumnControlEntity record);

    BizColumnControlEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BizColumnControlEntity record);

    int updateByPrimaryKey(BizColumnControlEntity record);
}