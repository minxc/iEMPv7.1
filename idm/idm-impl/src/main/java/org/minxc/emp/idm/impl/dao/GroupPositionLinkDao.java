package org.minxc.emp.idm.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.idm.impl.model.GroupPositionLinkModel;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;

/**
 * 组织关联关系 DAO接口
 */
@Mapper
public interface GroupPositionLinkDao extends CommonDao<String, GroupPositionLinkModel> {
	
	GroupPositionLinkModel getByCode(String code);

	List<GroupPositionLinkModel> getListByGroupId(String groupId);

	List<GroupPositionLinkModel> queryInfoList(QueryFilter queryFilter);

	GroupPositionLinkModel getByGroupIdAndPositionId(String groupId, String positionId);

	List<GroupPositionLinkModel> getGroupPositionLinkListByParam(@Param("account") String account, @Param("userId") String userId);
}
