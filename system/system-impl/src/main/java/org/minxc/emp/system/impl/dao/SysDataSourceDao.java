package org.minxc.emp.system.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.SystemDataSourceEntity;

/**
 * 数据源 DAO接口
 */
@Mapper
public interface SysDataSourceDao extends CommonDao<String, SystemDataSourceEntity> {
}
