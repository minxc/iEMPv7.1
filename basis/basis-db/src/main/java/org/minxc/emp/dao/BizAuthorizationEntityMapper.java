package org.minxc.emp.dao;

import org.minxc.emp.model.BizAuthorizationEntity;

public interface BizAuthorizationEntityMapper {
    int deleteByPrimaryKey(String rightsId);

    int insert(BizAuthorizationEntity record);

    int insertSelective(BizAuthorizationEntity record);

    BizAuthorizationEntity selectByPrimaryKey(String rightsId);

    int updateByPrimaryKeySelective(BizAuthorizationEntity record);

    int updateByPrimaryKey(BizAuthorizationEntity record);
}