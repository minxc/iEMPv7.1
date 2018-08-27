package org.minxc.emp.basis.impl.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.basis.impl.core.model.SysScheduleJobLog;
import org.minxc.emp.common.db.dao.CommonDao;

/**
 * 任务计划日志持久化
 */

@Mapper
public interface SysScheduleJobLogDao extends CommonDao<String, SysScheduleJobLog> {

	/**
	 * 选择性插入
	 * 
	 * @param entity 实体
	 * @return
	 */
	int insertSelective(SysScheduleJobLog entity);

	/**
	 * 选择性更新
	 *
	 * @param entity 更新
	 * @return
	 */
	int updateByPrimaryKeySelective(SysScheduleJobLog entity);

	/**
	 * 根据任务计划ID移除
	 * 
	 * @param jobId
	 * @return
	 */
	int removeByJobId(String jobId);
}
