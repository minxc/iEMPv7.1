package org.minxc.emp.basis.impl.core.manager;

import java.util.List;
import java.util.Map;

import org.minxc.emp.basis.impl.core.model.SysProperties;
import org.minxc.emp.common.manager.Manager;

/**
 * SYS_PROPERTIES 处理接口
 */
public interface SysPropertiesManager extends Manager<String, SysProperties> {


    /**
     * 分组列表。
     *
     * @return
     */
    List<String> getGroups();

    /**
     * 判断别名是否存在。
     *
     * @param sysProperties
     * @return
     */
    boolean isExist(SysProperties sysProperties);


    /**
     * 重新读取属性配置。
     *
     * @return
     */
    Map<String, Map<String, String>> reloadProperty();

}
