package org.minxc.emp.idm.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.idm.impl.model.GroupRelationEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;

/**
 * 描述：组织关联关系 DAO接口
 */
@Mapper
public interface GroupRelationDao extends CommonDao<String, GroupRelationEntity> {
	GroupRelationEntity getByCode(String code);

	List<GroupRelationEntity> getListByGroupId(String groupId);

	List<GroupRelationEntity> queryInfoList(QueryFilter queryFilter);

	GroupRelationEntity getByGroupIdRelDefId(String orgId, String relDefId);

	List<GroupRelationEntity> getRelListByParam(@Param("account") String account, @Param("userId") String userId);
}
