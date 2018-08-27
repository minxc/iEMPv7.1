package org.minxc.emp.idm.impl.manager;

import org.minxc.emp.idm.impl.model.GroupRelationEntity;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.QueryFilter;

import java.util.List;

/**
 * 组织关联关系 处理接口
 */
public interface GroupRelationManager extends Manager<String, GroupRelationEntity> {
    GroupRelationEntity getByCode(String code);

    /**
     * 根据组织ID获取岗位列表
     *
     * @param orgId
     * @return
     */
    List<GroupRelationEntity> getListByGroupId(String orgId);


    List<GroupRelationEntity> queryInfoList(QueryFilter queryFilter);

    /**
     * 根据用户ID获取对应的岗位列表
     *
     * @param userId
     * @return
     */
    List<GroupRelationEntity> getListByUserId(String userId);
}
