package org.minxc.emp.dao;

import org.minxc.emp.model.BpmDefinationEntity;

public interface BpmDefinationEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BpmDefinationEntity record);

    int insertSelective(BpmDefinationEntity record);

    BpmDefinationEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BpmDefinationEntity record);

    int updateByPrimaryKeyWithBLOBs(BpmDefinationEntity record);

    int updateByPrimaryKey(BpmDefinationEntity record);
}