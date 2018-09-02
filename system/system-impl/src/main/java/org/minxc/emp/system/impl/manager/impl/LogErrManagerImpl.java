package org.minxc.emp.system.impl.manager.impl;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.system.impl.dao.LogErrDao;
import org.minxc.emp.system.impl.manager.LogErrManager;
import org.minxc.emp.system.impl.model.LogErr;
import org.springframework.stereotype.Service;

/**
 *  错误日志 处理实现类
 */
@Service("sysLogErrManager")
public class LogErrManagerImpl extends CommonManager<String, LogErr> implements LogErrManager {
    @Resource
    LogErrDao sysLogErrDao;

    @ErrorCatching
    @Override
    public void getSub() {
        System.out.println("11111111");
    }
}
