package org.minxc.emp.idm.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import com.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

import org.minxc.emp.idm.impl.dao.GroupDao;
import org.minxc.emp.idm.impl.dao.UserDao;
import org.minxc.emp.idm.impl.manager.GroupManager;
import org.minxc.emp.idm.impl.model.GroupEntity;
import org.minxc.emp.idm.impl.model.UserEntity;

/**
 * 描述：组织架构 处理实现类
 */
@Service("groupManager")
public class GroupManagerImpl extends CommonManager<String, GroupEntity> implements GroupManager {
	@Resource
	GroupDao groupDao;
	@Resource
	UserDao userDao;

	public GroupEntity getByCode(String code) {
		return groupDao.getByCode(code);
	}

	public List<GroupEntity> getByUserId(String userId) {
		return groupDao.getByUserId(userId);
	}

	public List<GroupEntity> getByUserAccount(String account) {
		UserEntity user = userDao.getByAccount(account);
		return groupDao.getByUserId(user.getId());
	}

	@Override
	public GroupEntity getMainGroup(String userId) {
		List<GroupEntity> list = groupDao.getByUserId(userId);
		if (BeanUtils.isEmpty(list))
			return null;
		if (list.size() == 1)
			return list.get(0);
		for (GroupEntity org : list) {
			if (org.getIsMaster() == 1)
				return org;
		}
		return list.get(0);
	}
}
