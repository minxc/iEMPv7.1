package org.minxc.emp.bpm.core.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.bpm.core.model.BpmnTaskOpinion;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface BpmnTaskOpinionDao extends CommonDao<String, BpmnTaskOpinion> {
	public BpmnTaskOpinion getByTaskId(String taskId);

	public List<BpmnTaskOpinion> getByInstAndNode(@Param(value = "instId") String instId,
                                                  @Param(value = "nodeId") String nodeId);
}