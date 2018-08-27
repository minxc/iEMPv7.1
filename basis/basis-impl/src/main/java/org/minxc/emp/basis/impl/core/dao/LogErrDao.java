package org.minxc.emp.basis.impl.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.basis.impl.core.model.LogErr;
import org.minxc.emp.common.db.dao.CommonDao;


/**
 * <pre>
 * 描述：错误日志 DAO接口
 * </pre>
 */
@Mapper
public interface LogErrDao extends CommonDao<String, LogErr> {
}
