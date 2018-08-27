package org.minxc.emp.idm.impl.manager.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

import org.minxc.emp.idm.impl.dao.RoleDao;
import org.minxc.emp.idm.impl.manager.RoleManager;
import org.minxc.emp.idm.impl.model.RoleEntity;

/**
 * 角色管理 处理实现类
 */
@Service("roleManager")
public class RoleManagerImpl extends CommonManager<String, RoleEntity> implements RoleManager {
	@Resource
	RoleDao roleDao;

	public RoleEntity getByAlias(String alias) {
		return roleDao.getByAlias(alias);
	}

	public List<RoleEntity> getListByUserId(String userId) {
		if (StringUtils.isEmpty(userId))
			return Collections.emptyList();
		return roleDao.getList(userId, null);
	}

	public List<RoleEntity> getListByAccount(String account) {
		if (StringUtils.isEmpty(account))
			return Collections.emptyList();

		return roleDao.getList(null, account);
	}

	@Override
	public boolean isRoleExist(RoleEntity role) {
		return roleDao.isRoleExist(role) != 0;
	}

}
