package org.minxc.emp.system.impl.manager;

import java.util.List;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.SysDataSourceDef;
import org.minxc.emp.system.impl.model.def.SysDataSourceDefAttribute;

/**
 * 数据源模板 Manager处理接口
 */
public interface SysDataSourceDefManager extends Manager<String, SysDataSourceDef> {

    /**
     * <pre>
     * 根据classPath类路径获取数据源的配置参数
     * </pre>
     *
     * @param classPath
     * @return
     */
    List<SysDataSourceDefAttribute> initAttributes(String classPath);

}
