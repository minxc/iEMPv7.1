package org.minxc.emp.biz.core.manager.impl;

import com.dstz.base.manager.impl.BaseManager;

import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.biz.core.dao.BusinessColumnDao;
import org.minxc.emp.biz.core.manager.BusinessColumnManager;
import org.minxc.emp.biz.core.model.BusinessColumn;
import org.springframework.stereotype.Service;

@Service
public class BusinessColumnManagerImpl extends BaseManager<String, BusinessColumn> implements BusinessColumnManager {
	@Resource
	private BusinessColumnDao businessColumnDao;

	public void removeByTableId(String tableId) {
		this.businessColumnDao.removeByTableId(tableId);
	}

	public List<BusinessColumn> getByTableId(String tableId) {
		return this.businessColumnDao.getByTableId(tableId);
	}
}