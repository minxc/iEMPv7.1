package org.minxc.emp.idm.impl.manager.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.springframework.stereotype.Service;

import org.minxc.emp.idm.impl.dao.GroupPositionLinkDao;
import org.minxc.emp.idm.impl.manager.GroupRelationManager;
import org.minxc.emp.idm.impl.model.GroupPositionLinkModel;

/**
 * 组织关联关系 处理实现类
 */
@Service("groupRelationManager")
public class GroupRelationManagerImpl extends CommonManager<String, GroupPositionLinkModel>
		implements GroupRelationManager {
	@Resource
	private GroupPositionLinkDao groupPositionLinkDao;

	public GroupPositionLinkModel getByCode(String code) {
		return this.groupPositionLinkDao.getByCode(code);
	}

	public List<GroupPositionLinkModel> getListByGroupId(String groupId) {
		return this.groupPositionLinkDao.getListByGroupId(groupId);
	}

	public List<GroupPositionLinkModel> queryInfoList(QueryFilter queryFilter) {
		return this.groupPositionLinkDao.queryInfoList(queryFilter);
	}

	public List<GroupPositionLinkModel> getListByUserId(String userId) {
		if (StringUtils.isEmpty(userId))
			return Collections.emptyList();
		return this.groupPositionLinkDao.getGroupPositionLinkListByParam(null, userId);
	}

	public List<GroupPositionLinkModel> getListByAccount(String account) {
		if (StringUtils.isEmpty(account))
			return Collections.emptyList();
		return this.groupPositionLinkDao.getGroupPositionLinkListByParam(account, null);
	}
}
