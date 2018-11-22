package org.minxc.emp.idm.impl.manager;

import org.minxc.emp.idm.impl.model.GroupPositionDefinitionEntity;
import org.minxc.emp.common.manager.Manager;

/**
 * 
 */
public interface GroupPositionDefinitionManager extends Manager<String, GroupPositionDefinitionEntity> {
    /**
     * 根据职务编码获取职务定义
     *
     * @param code
     * @return
     */
    public GroupPositionDefinitionEntity getByCode(String code);

}
