package org.minxc.emp.idm.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;

import org.minxc.emp.idm.impl.model.GroupPositionDefinitionEntity;

/**
 * 组织职务信息 DAO接口
 */
@Mapper
public interface GroupPositionDefinitionDao extends CommonDao<String, GroupPositionDefinitionEntity> {
    public GroupPositionDefinitionEntity getByCode(String code);
}
