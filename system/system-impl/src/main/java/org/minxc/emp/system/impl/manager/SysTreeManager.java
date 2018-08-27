package org.minxc.emp.system.impl.manager;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.SysTree;

/**
 * 系统树 Manager处理接口
 *
 */
public interface SysTreeManager extends Manager<String, SysTree> {
    /**
     * <pre>
     * 根据别名获取对象
     * </pre>
     *
     * @param key
     * @return
     */
    SysTree getByKey(String key);

    void removeContainNode(String id);
}
