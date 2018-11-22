package org.minxc.emp.idm.impl.manager.impl;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.minxc.emp.idm.impl.dao.GroupPositionDefinitionDao;
import org.minxc.emp.idm.impl.manager.GroupPositionDefinitionManager;
import org.minxc.emp.idm.impl.model.GroupPositionDefinitionEntity;

/**
 * 组织职务信息 处理实现类
 */
@Service("groupReldefManager")
public class GroupPositionDefinitionManagerImpl extends CommonManager<String, GroupPositionDefinitionEntity> implements GroupPositionDefinitionManager {
	
	@Autowired
	private GroupPositionDefinitionDao groupPositionDefinitionDao;

	public GroupPositionDefinitionEntity getByCode(String code) {
		return groupPositionDefinitionDao.getByCode(code);
	}

}
