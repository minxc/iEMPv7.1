package org.minxc.emp.biz.core.manager.impl;

import com.dstz.base.manager.impl.BaseManager;

import javax.annotation.Resource;

import org.minxc.emp.biz.core.dao.BusColumnCtrlDao;
import org.minxc.emp.biz.core.manager.BusColumnCtrlManager;
import org.minxc.emp.biz.core.model.BusColumnCtrl;
import org.springframework.stereotype.Service;

@Service
public class BusColumnCtrlManagerImpl extends BaseManager<String, BusColumnCtrl> implements BusColumnCtrlManager {
	@Resource
	private BusColumnCtrlDao busColumnCtrlDao;

	public void removeByTableId(String tableId) {
		this.busColumnCtrlDao.removeByTableId(tableId);
	}

	public BusColumnCtrl getByColumnId(String columnId) {
		return this.busColumnCtrlDao.getByColumnId(columnId);
	}
}