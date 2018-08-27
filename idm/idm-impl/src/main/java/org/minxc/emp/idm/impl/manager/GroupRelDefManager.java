package org.minxc.emp.idm.impl.manager;

import org.minxc.emp.idm.impl.model.GroupRelDefEntity;
import org.minxc.emp.common.manager.Manager;

/**
 * 组织关系定义 处理接口
 */
public interface GroupRelDefManager extends Manager<String, GroupRelDefEntity> {
    /**
     * 根据职务编码获取职务定义
     *
     * @param code
     * @return
     */
    public GroupRelDefEntity getByCode(String code);

}
