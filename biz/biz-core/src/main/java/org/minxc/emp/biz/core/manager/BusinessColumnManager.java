package org.minxc.emp.biz.core.manager;


import java.util.List;

import org.minxc.emp.biz.core.model.BusinessColumn;
import org.minxc.emp.common.manager.Manager;

public interface BusinessColumnManager extends Manager<String, BusinessColumn> {
	public void removeByTableId(String tableId);

	public List<BusinessColumn> getByTableId(String tableId);
}