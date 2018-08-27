package org.minxc.emp.basis.impl.service.impl;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.service.SerialNoService;
import org.minxc.emp.basis.impl.core.manager.SerialNoManager;
import org.springframework.stereotype.Service;


/**
 *  流水号 接口实现
 */
@Service("serialNoService")
public class DefaultSerialNoService implements SerialNoService {

    @Resource
    SerialNoManager serialNoManager;

    @Override
    public String genNextNo(String alias) {
        return serialNoManager.nextId(alias);
    }

    @Override
    public String getPreviewNo(String alias) {
        return serialNoManager.getCurIdByAlias(alias);
    }
}
