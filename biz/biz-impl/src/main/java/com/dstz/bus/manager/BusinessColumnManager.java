package com.dstz.bus.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bus.model.BusinessColumn;
import java.util.List;

public interface BusinessColumnManager extends Manager<String, BusinessColumn> {
	public void removeByTableId(String tableId);

	public List<BusinessColumn> getByTableId(String tableId);
}