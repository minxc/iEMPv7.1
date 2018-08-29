package org.minxc.emp.bpm.core.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.core.model.BpmTaskApprove;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;

@Mapper
public interface BpmInstanceDao extends CommonDao<String, BpmInstance> {
	public List<BpmInstance> getApplyList(QueryFilter var1);

	public List<BpmTaskApprove> getApproveHistoryList(QueryFilter var1);
}