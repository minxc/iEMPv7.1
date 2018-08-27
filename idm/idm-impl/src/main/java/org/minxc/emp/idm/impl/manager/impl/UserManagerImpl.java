package org.minxc.emp.idm.impl.manager.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.springframework.stereotype.Service;

import org.minxc.emp.idm.impl.dao.UserDao;
import org.minxc.emp.idm.impl.dao.UserRoleDao;
import org.minxc.emp.idm.impl.manager.UserManager;
import org.minxc.emp.idm.impl.model.UserEntity;

/**
 * 用户表 处理实现类
 */
@Service("userManager")
public class UserManagerImpl extends CommonManager<String, UserEntity> implements UserManager {
	@Resource
	UserDao userDao;
	@Resource
	UserRoleDao userRoleDao;

	public UserEntity getByAccount(String account) {
		return this.userDao.getByAccount(account);
	}

	/**
	 * 不含组织用户关系数据
	 */
	public List<UserEntity> queryOrgUser(QueryFilter queryFilter) {
		return userDao.queryOrgUser(queryFilter);
	}

	/**
	 * 不含组织用户关系数据
	 */
	public List<UserEntity> getUserListByOrgId(String orgId) {
		return userDao.getUserListByOrgId(orgId);
	}

	public List<UserEntity> getListByRelCode(String relCode) {
		return userDao.getListByRelCode(relCode);
	}

	public List<UserEntity> getListByRelId(String relId) {
		return userDao.getUserListByRelId(relId);
	}

	public List<UserEntity> getUserListByRoleId(String roleId) {
		if (StringUtils.isEmpty(roleId))
			return Collections.emptyList();

		return userDao.getUserListByRole(roleId, null);
	}

	@Override
	public boolean isUserExist(UserEntity user) {
		return userDao.isUserExist(user) > 0;
	}

	@Override
	public List queryUserGroupRel(QueryFilter queryFilter) {
		return userDao.queryUserGroupRel(queryFilter);
	}

	@Override
	public List<UserEntity> getUserListByRoleCode(String roleCode) {
		if (StringUtils.isEmpty(roleCode))
			return Collections.emptyList();

		return userDao.getUserListByRole(null, roleCode);
	}
}
