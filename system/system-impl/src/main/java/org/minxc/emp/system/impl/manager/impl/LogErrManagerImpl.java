package org.minxc.emp.system.impl.manager.impl;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.system.impl.dao.LogErrDao;
import org.minxc.emp.system.impl.manager.LogErrorManager;
import org.minxc.emp.system.impl.model.SystemLogErrorEntity;
import org.springframework.stereotype.Service;

/**
 * 
* 项目名称：system-impl   
* 类名称：LogErrManagerImpl   
* 类描述： 错误日志 处理实现类 
* 创建人：Xianchang.min   
* 创建时间：2018年9月2日 下午4:22:28   
* 修改人：Xianchang.min   
* 修改时间：2018年9月2日 下午4:22:28   
* 修改备注：   
* @version  1.0  
*
 */


@Service("sysLogErrManager")
public class LogErrManagerImpl extends CommonManager<String, SystemLogErrorEntity> implements LogErrorManager {
    @Resource
    LogErrDao sysLogErrDao;

    @ErrorCatching
    @Override
    public void getSub() {
        System.out.println("11111111");
    }
}
