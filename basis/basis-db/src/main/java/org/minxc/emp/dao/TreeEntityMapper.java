package org.minxc.emp.dao;

import org.minxc.emp.model.TreeEntity;

public interface TreeEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(TreeEntity record);

    int insertSelective(TreeEntity record);

    TreeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TreeEntity record);

    int updateByPrimaryKey(TreeEntity record);
}