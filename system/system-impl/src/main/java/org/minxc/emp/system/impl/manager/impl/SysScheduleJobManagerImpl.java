package org.minxc.emp.system.impl.manager.impl;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.system.impl.dao.SysScheduleJobDao;
import org.minxc.emp.system.impl.manager.ScheduleJobManager;
import org.minxc.emp.system.impl.model.SystemScheduleJobEntity;
import org.springframework.stereotype.Service;


/**
 * 系统执行计划通过处理
 *
 * @author didi
 */
@Service("sysScheduleJobManager")
public class SysScheduleJobManagerImpl extends CommonManager<String, SystemScheduleJobEntity> implements ScheduleJobManager {

    @Resource(name = "sysScheduleJobDao")
    private SysScheduleJobDao sysScheduleJobDao;
 
}
