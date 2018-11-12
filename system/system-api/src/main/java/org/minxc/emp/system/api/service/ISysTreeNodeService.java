package org.minxc.emp.system.api.service;

import org.minxc.emp.system.api.model.SystemTreeNode;

/**
 * 
 * SysTreeNodeService接口
 * 
 * 
 * 日期:2018年3月28日 下午3:31:25
 * 
 * 
 */
public interface ISysTreeNodeService {
    /**
     * 
     * 根据id获取对象
     * 
     *
     * @param id
     * @return
     */
    SystemTreeNode getById(String id);

}
