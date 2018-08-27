package org.minxc.emp.basis.impl.core.manager;

import org.minxc.emp.basis.impl.core.model.SysScheduleJobLog;
import org.minxc.emp.common.manager.Manager;

/**
 * 
 * @author Xianchang.min
 *
 */
public interface SysScheduleJobLogManager extends Manager<String, SysScheduleJobLog> {

    /**
     * 选择性插入
     * @param entity
     *          实体
     * @return
     */
    int insertSelective(SysScheduleJobLog entity);

    /**
     * 选择性更新
     *
     * @param entity
     *          更新
     * @return
     */
    int updateByPrimaryKeySelective(SysScheduleJobLog entity);


}
