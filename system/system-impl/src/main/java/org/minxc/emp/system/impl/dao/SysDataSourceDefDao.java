package org.minxc.emp.system.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.SysDataSourceDef;


/**
 * 描述：数据源模板 DAO接口
 */
@Mapper
public interface SysDataSourceDefDao extends CommonDao<String, SysDataSourceDef> {
}
