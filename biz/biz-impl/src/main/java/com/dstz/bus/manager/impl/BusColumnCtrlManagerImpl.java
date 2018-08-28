package com.dstz.bus.manager.impl;

import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bus.dao.BusColumnCtrlDao;
import com.dstz.bus.manager.BusColumnCtrlManager;
import com.dstz.bus.model.BusColumnCtrl;
import javax.annotation.Resource;
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