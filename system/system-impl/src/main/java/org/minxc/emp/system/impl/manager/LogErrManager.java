package org.minxc.emp.system.impl.manager;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.SystemLogErrorEntity;

/**
 * 描述：错误日志 处理接口
 */
public interface LogErrManager extends Manager<String, SystemLogErrorEntity> {

    void getSub();

}
