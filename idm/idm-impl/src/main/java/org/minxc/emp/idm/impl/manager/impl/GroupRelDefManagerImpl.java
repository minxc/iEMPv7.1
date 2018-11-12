package org.minxc.emp.idm.impl.manager.impl;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

import org.minxc.emp.idm.impl.dao.GroupRelDefDao;
import org.minxc.emp.idm.impl.manager.GroupRelDefManager;
import org.minxc.emp.idm.impl.model.GroupRelDefEntity;

/**
 * 组织关系定义 处理实现类
 */
@Service("groupReldefManager")
public class GroupRelDefManagerImpl extends CommonManager<String, GroupRelDefEntity> implements GroupRelDefManager {
	@Resource
	GroupRelDefDao groupRelDefDao;

	public GroupRelDefEntity getByCode(String code) {
		return groupRelDefDao.getByCode(code);
	}

}
