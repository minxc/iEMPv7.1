package org.minxc.emp.basis.impl.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.basis.impl.core.model.WorkbenchLayout;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface WorkbenchLayoutDao extends CommonDao<String, WorkbenchLayout> {

	void removeByUserId(String currentUserId);

	List<WorkbenchLayout> getByUserId(String userId);
}
