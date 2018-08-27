package org.minxc.emp.dao;

import org.minxc.emp.model.ScriptEntity;

public interface ScriptEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScriptEntity record);

    int insertSelective(ScriptEntity record);

    ScriptEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScriptEntity record);

    int updateByPrimaryKeyWithBLOBs(ScriptEntity record);

    int updateByPrimaryKey(ScriptEntity record);
}