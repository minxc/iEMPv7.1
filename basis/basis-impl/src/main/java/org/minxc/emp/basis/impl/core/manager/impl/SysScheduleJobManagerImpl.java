package org.minxc.emp.basis.impl.core.manager.impl;

import javax.annotation.Resource;

import org.minxc.emp.basis.impl.core.dao.SysScheduleJobDao;
import org.minxc.emp.basis.impl.core.manager.SysScheduleJobManager;
import org.minxc.emp.basis.impl.core.model.SysScheduleJob;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;


/**
 * 系统执行计划通过处理
 *
 * @author didi
 */
@Service("sysScheduleJobManager")
public class SysScheduleJobManagerImpl extends CommonManager<String, SysScheduleJob> implements SysScheduleJobManager {

    @Resource(name = "sysScheduleJobDao")
    private SysScheduleJobDao sysScheduleJobDao;
 
}
