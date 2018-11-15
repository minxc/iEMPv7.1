package org.minxc.emp.biz.core.manager;

import java.util.List;

import org.minxc.emp.biz.core.model.BusinessColumnImpl;
import org.minxc.emp.common.manager.Manager;

public interface BusinessColumnManager extends Manager<String, BusinessColumnImpl> {
	public void removeByTableId(String tableId);

	public List<BusinessColumnImpl> getByTableId(String tableId);
}