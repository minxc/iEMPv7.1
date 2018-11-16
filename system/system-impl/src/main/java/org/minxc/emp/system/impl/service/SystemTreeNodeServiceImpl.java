package org.minxc.emp.system.impl.service;

import org.minxc.emp.system.api.model.SystemTreeNode;
import org.minxc.emp.system.api.service.SystemTreeNodeService;
import org.minxc.emp.system.impl.manager.SystemTreeNodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysTreeNodeService接口
 */
@Service
public class SystemTreeNodeServiceImpl implements SystemTreeNodeService {
    @Autowired
    SystemTreeNodeManager systemTreeNodeManager;

    @Override
    public SystemTreeNode getById(String id) {
        return systemTreeNodeManager.get(id);
    }
}
