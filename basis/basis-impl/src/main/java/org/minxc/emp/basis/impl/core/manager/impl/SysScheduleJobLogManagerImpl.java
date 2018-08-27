package org.minxc.emp.basis.impl.core.manager.impl;

import org.minxc.emp.basis.impl.core.dao.SysScheduleJobLogDao;
import org.minxc.emp.basis.impl.core.manager.SysScheduleJobLogManager;
import org.minxc.emp.basis.impl.core.model.SysScheduleJobLog;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author didi
 */
@Service("sysScheduleJobLogManager")
public class SysScheduleJobLogManagerImpl extends CommonManager<String, SysScheduleJobLog> implements SysScheduleJobLogManager {

    @Resource
    private SysScheduleJobLogDao sysScheduleJobLogDao;

    @Override
    public int insertSelective(SysScheduleJobLog entity) {

        return sysScheduleJobLogDao.insertSelective(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(SysScheduleJobLog entity) {

        return sysScheduleJobLogDao.updateByPrimaryKeySelective(entity);
    }
}
