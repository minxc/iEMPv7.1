package org.minxc.emp.biz.core.manager.impl;


import javax.annotation.Resource;

import org.minxc.emp.biz.core.dao.BusColumnCtrlDao;
import org.minxc.emp.biz.core.manager.BusColumnCtrlManager;
import org.minxc.emp.biz.core.model.BusinessColumnControlImpl;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

@Service
public class BusColumnCtrlManagerImpl extends CommonManager<String, BusinessColumnControlImpl> implements BusColumnCtrlManager {
	
	
	@Resource
	private BusColumnCtrlDao busColumnCtrlDao;

	public void removeByTableId(String tableId) {
		this.busColumnCtrlDao.removeByTableId(tableId);
	}

	public BusinessColumnControlImpl getByColumnId(String columnId) {
		return this.busColumnCtrlDao.getByColumnId(columnId);
	}
}