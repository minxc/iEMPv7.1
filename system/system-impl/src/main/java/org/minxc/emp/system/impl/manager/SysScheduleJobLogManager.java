package org.minxc.emp.system.impl.manager;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.SystemScheduleJobLogEntity;

/**
 * 
 * @author Xianchang.min
 *
 */
public interface SysScheduleJobLogManager extends Manager<String, SystemScheduleJobLogEntity> {

    /**
     * 选择性插入
     * @param entity
     *          实体
     * @return
     */
    int insertSelective(SystemScheduleJobLogEntity entity);

    /**
     * 选择性更新
     *
     * @param entity
     *          更新
     * @return
     */
    int updateByPrimaryKeySelective(SystemScheduleJobLogEntity entity);


}
