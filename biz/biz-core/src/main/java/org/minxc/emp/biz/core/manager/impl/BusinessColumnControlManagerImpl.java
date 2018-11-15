package org.minxc.emp.biz.core.manager.impl;


import javax.annotation.Resource;

import org.minxc.emp.biz.core.dao.BusinessColumnControlDao;
import org.minxc.emp.biz.core.manager.BusinessColumnControlManager;
import org.minxc.emp.biz.core.model.BusinessColumnControlImpl;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

@Service
public class BusinessColumnControlManagerImpl extends CommonManager<String, BusinessColumnControlImpl> implements BusinessColumnControlManager {
	
	
	@Resource
	private BusinessColumnControlDao businessColumnControlDao;

	public void removeByTableId(String tableId) {
		this.businessColumnControlDao.removeByTableId(tableId);
	}

	public BusinessColumnControlImpl getByColumnId(String columnId) {
		return this.businessColumnControlDao.getByColumnId(columnId);
	}
}