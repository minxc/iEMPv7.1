package org.minxc.emp.bpm.core.dao;

import org.minxc.emp.common.db.dao.CommonDao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.bpm.core.model.BpmTaskOpinion;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BpmTaskOpinionDao extends CommonDao<String, BpmTaskOpinion> {
	public BpmTaskOpinion getByTaskId(String taskId);

	public List<BpmTaskOpinion> getByInstAndNode(@Param(value = "instId") String instId,
			@Param(value = "nodeId") String nodeId);
}