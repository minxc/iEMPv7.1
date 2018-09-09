package org.minxc.emp.system.impl.service;

import org.minxc.emp.system.api.model.SystemTreeNode;
import org.minxc.emp.system.api.service.ISysTreeNodeService;
import org.minxc.emp.system.impl.manager.SysTreeNodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：SysTreeNodeService接口
 */
@Service
public class SysTreeNodeService implements ISysTreeNodeService {
    @Autowired
    SysTreeNodeManager sysTreeNodeManager;

    @Override
    public SystemTreeNode getById(String id) {
        return sysTreeNodeManager.get(id);
    }
}
