package org.minxc.emp.biz.core.manager.impl;


import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.biz.core.dao.BusinessColumnDao;
import org.minxc.emp.biz.core.manager.BusinessColumnManager;
import org.minxc.emp.biz.core.model.BusinessColumnImpl;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

@Service
public class BusinessColumnManagerImpl extends CommonManager<String, BusinessColumnImpl> implements BusinessColumnManager {
	@Resource
	private BusinessColumnDao businessColumnDao;

	public void removeByTableId(String tableId) {
		this.businessColumnDao.removeByTableId(tableId);
	}

	public List<BusinessColumnImpl> getByTableId(String tableId) {
		return this.businessColumnDao.getByTableId(tableId);
	}
}