package org.minxc.emp.dao;

import org.minxc.emp.model.BizPermissionEntity;
import org.minxc.emp.model.BizPermissionEntityWithBLOBs;

public interface BizPermissionEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BizPermissionEntityWithBLOBs record);

    int insertSelective(BizPermissionEntityWithBLOBs record);

    BizPermissionEntityWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BizPermissionEntityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BizPermissionEntityWithBLOBs record);

    int updateByPrimaryKey(BizPermissionEntity record);
}