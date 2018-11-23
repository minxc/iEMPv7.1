package org.minxc.emp.idm.impl.manager;

import org.minxc.emp.idm.impl.model.GroupPositionLinkModel;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.QueryFilter;

import java.util.List;

/**
 * 组织关联关系 处理接口
 */
public interface GroupRelationManager extends Manager<String, GroupPositionLinkModel> {
	GroupPositionLinkModel getByCode(String code);

    /**
     * 根据组织ID获取岗位列表
     *
     * @param orgId
     * @return
     */
    List<GroupPositionLinkModel> getListByGroupId(String orgId);


    List<GroupPositionLinkModel> queryInfoList(QueryFilter queryFilter);

    /**
     * 根据用户ID获取对应的岗位列表
     *
     * @param userId
     * @return
     */
    List<GroupPositionLinkModel> getListByUserId(String userId);
}
