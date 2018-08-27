package org.minxc.emp.basis.impl.core.manager;

import org.minxc.emp.basis.impl.core.model.LogErr;
import org.minxc.emp.common.manager.Manager;

/**
 * 描述：错误日志 处理接口
 */
public interface LogErrManager extends Manager<String, LogErr> {

    void getSub();

}
