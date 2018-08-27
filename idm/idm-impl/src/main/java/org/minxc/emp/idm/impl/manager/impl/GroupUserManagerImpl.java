package org.minxc.emp.idm.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

//import com.dstz.base.core.cache.ICache;
import com.minxc.emp.core.util.BeanUtils;

import org.minxc.emp.idm.api.context.CurrentContext;
import org.minxc.emp.idm.impl.dao.GroupUserDao;
import org.minxc.emp.idm.impl.manager.GroupUserManager;
import org.minxc.emp.idm.impl.model.GroupUserEntity;

/**
 * 用户组织关系 处理实现类
 */
@Service("groupUserManager")
public class GroupUserManagerImpl extends CommonManager<String, GroupUserEntity> implements GroupUserManager {

	@Resource
	GroupUserDao groupUserDao;

//    @Resource
//    ICache iCache;

	@Resource
	private CacheManager cacheManager;

	public int updateUserPost(String id, String relId) {
		return groupUserDao.updateUserPost(id, relId);
	}

	public GroupUserEntity getGroupUser(String orgId, String userId, String relId) {
		return groupUserDao.getGroupUser(orgId, userId, relId);
	}

	public List<GroupUserEntity> getListByGroupIdUserId(String orgId, String userId) {
		return groupUserDao.getListByGroupIdUserId(orgId, userId);
	}

	public int removeByOrgIdUserId(String orgId, String userId) {
		return groupUserDao.removeByGroupIdUserId(orgId, userId);
	}

	public void setMaster(String id) {
		GroupUserEntity orgUser = this.get(id);
		if (orgUser.getIsMaster() == 0) {
			groupUserDao.cancelUserMasterGroup(orgUser.getUserId());
			groupUserDao.setMaster(id);
		} else {
			orgUser.setIsMaster(0);
			groupUserDao.update(orgUser);
		}

		// 删除缓存。
		String userKey = CurrentContext.CURRENT_ORG + orgUser.getUserId();
		cacheManager.getCache("idm").evict(userKey);
//        iCache.delByKey(userKey);
	}

	public GroupUserEntity getGroupUserMaster(String userId) {
		return groupUserDao.getGroupUserMaster(userId);
	}

	@Override
	public List getUserByGroup(QueryFilter queryFilter) {
		return groupUserDao.getUserByGroup(queryFilter);
	}

	@Override
	public void saveGroupUserRel(String groupId, String[] userIds, String[] relIds) {
		for (String userId : userIds) {
			if (StringUtils.isEmpty(userId))
				continue;
			// 没有选择岗位情况。仅仅加入组
			if (BeanUtils.isEmpty(relIds)) {
				List<GroupUserEntity> list = groupUserDao.getListByGroupIdUserId(groupId, userId);
				if (BeanUtils.isNotEmpty(list))
					continue;

				GroupUserEntity user = new GroupUserEntity(groupId, userId, null);
				groupUserDao.create(user);
				continue;
			}

			for (String relId : relIds) {
				if (StringUtils.isEmpty(relId))
					continue;

				GroupUserEntity groupUser = groupUserDao.getGroupUser(groupId, userId, relId);
				if (groupUser != null)
					continue;

				groupUser = new GroupUserEntity(groupId, userId, relId);
				groupUserDao.create(groupUser);
			}
		}
	}

}
