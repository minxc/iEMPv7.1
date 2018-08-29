package org.minxc.emp.bpm.core.dao;

import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;

import java.util.List;

import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.core.model.BpmTaskApprove;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BpmInstanceDao extends CommonDao<String, BpmInstance> {
	public List<BpmInstance> getApplyList(QueryFilter var1);

	public List<BpmTaskApprove> getApproveHistoryList(QueryFilter var1);
}