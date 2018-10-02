package org.minxc.emp.system.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.SystemScheduleJobEntity;

/**
 * 系统计划任务数据操作
 */
@Mapper
public interface SysScheduleJobDao extends CommonDao<String, SystemScheduleJobEntity> {

	/**
	 * 选择性插入
	 *
	 * @param entity 实体
	 * @return
	 */
	int insertSelective(SystemScheduleJobEntity entity);

	/**
	 * 选择性更新
	 *
	 * @param entity 实体
	 * @return
	 */
	int updateByPrimaryKeySelective(SystemScheduleJobEntity entity);

	/**
	 * 是否存在
	 *
	 * @param jobName  工作名称
	 * @param jobGroup 工作分组
	 * @return
	 */
	boolean exists(@Param("jobName") String jobName, @Param("jobGroup") String jobGroup);
}
