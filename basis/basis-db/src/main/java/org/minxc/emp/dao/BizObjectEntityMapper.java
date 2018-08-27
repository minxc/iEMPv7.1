package org.minxc.emp.dao;

import org.minxc.emp.model.BizObjectEntity;

public interface BizObjectEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BizObjectEntity record);

    int insertSelective(BizObjectEntity record);

    BizObjectEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BizObjectEntity record);

    int updateByPrimaryKeyWithBLOBs(BizObjectEntity record);

    int updateByPrimaryKey(BizObjectEntity record);
}