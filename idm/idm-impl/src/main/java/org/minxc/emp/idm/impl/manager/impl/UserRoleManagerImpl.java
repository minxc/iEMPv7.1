package org.minxc.emp.idm.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

import org.minxc.emp.idm.impl.dao.UserRoleDao;
import org.minxc.emp.idm.impl.manager.UserRoleManager;
import org.minxc.emp.idm.impl.model.UserRoleModel;

/**
 * 用户角色管理 处理实现类
 */
@Service("userRoleManager")
public class UserRoleManagerImpl extends CommonManager<String, UserRoleModel> implements UserRoleManager {
	@Resource
	UserRoleDao userRoleDao;

	public UserRoleModel getByRoleIdUserId(String roleId, String userId) {
		return userRoleDao.getByRoleIdUserId(roleId, userId);
	}

	// 这是什么鬼写法
	public List<UserRoleModel> getListByUserId(String userId) {
		return userRoleDao.queryByParams(null, userId, null);
	}

	public List<UserRoleModel> getListByRoleId(String roleId) {
		return userRoleDao.queryByParams(roleId, null, null);
	}

	public List<UserRoleModel> getListByAlias(String alias) {

		return userRoleDao.queryByParams(null, null, alias);
	}
}
