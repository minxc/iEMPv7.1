package org.minxc.emp.system.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.SystemLogErrorEntity;


/**
 * <pre>
 * 描述：错误日志 DAO接口
 * </pre>
 */
@Mapper
public interface LogErrDao extends CommonDao<String, SystemLogErrorEntity> {
}
