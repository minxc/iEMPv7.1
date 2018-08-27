package org.minxc.emp.basis.impl.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.basis.impl.core.model.SysScheduleJob;
import org.minxc.emp.common.db.dao.CommonDao;

/**
 * 系统计划任务数据操作
 */
@Mapper
public interface SysScheduleJobDao  extends CommonDao<String, SysScheduleJob> {


    /**
     * 选择性插入
     *
     * @param entity
     *          实体
     * @return
     */
    int insertSelective(SysScheduleJob entity);

    /**
     * 选择性更新
     *
     * @param entity
     *          实体
     * @return
     */
    int updateByPrimaryKeySelective(SysScheduleJob entity);

    /**
     * 是否存在
     *
     * @param jobName
     *          工作名称
     * @param jobGroup
     *          工作分组
     * @return
     */
    boolean exists(@Param("jobName")String jobName, @Param("jobGroup")String jobGroup);
}
