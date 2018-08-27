package org.minxc.emp.dao;

import org.minxc.emp.model.FormDefEntity;

public interface FormDefEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(FormDefEntity record);

    int insertSelective(FormDefEntity record);

    FormDefEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FormDefEntity record);

    int updateByPrimaryKeyWithBLOBs(FormDefEntity record);

    int updateByPrimaryKey(FormDefEntity record);
}