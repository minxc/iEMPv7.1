package org.minxc.emp.idm.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;

import org.minxc.emp.idm.impl.model.GroupRelDefEntity;

/**
 * 组织关系定义 DAO接口
 */
@Mapper
public interface GroupRelDefDao extends CommonDao<String, GroupRelDefEntity> {
    public GroupRelDefEntity getByCode(String code);
}
