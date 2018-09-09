package org.minxc.emp.system.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.TreeEntity;


/**
 * 系统树 DAO接口
 *
 */
@Mapper
public interface SysTreeDao extends CommonDao<String, TreeEntity> {

}
