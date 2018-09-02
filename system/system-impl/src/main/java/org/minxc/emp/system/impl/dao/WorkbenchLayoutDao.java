package org.minxc.emp.system.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.WorkbenchLayout;

@Mapper
public interface WorkbenchLayoutDao extends CommonDao<String, WorkbenchLayout> {

	void removeByUserId(String currentUserId);

	List<WorkbenchLayout> getByUserId(String userId);
}
