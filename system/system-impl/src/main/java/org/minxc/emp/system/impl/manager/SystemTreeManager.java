package org.minxc.emp.system.impl.manager;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.TreeEntity;

/**
 * 系统树 Manager处理接口
 *
 */
public interface SystemTreeManager extends Manager<String, TreeEntity> {
    /**
     * 
     * 根据别名获取对象
     * 
     *
     * @param key
     * @return
     */
    TreeEntity getByKey(String key);

    void removeContainNode(String id);
}
