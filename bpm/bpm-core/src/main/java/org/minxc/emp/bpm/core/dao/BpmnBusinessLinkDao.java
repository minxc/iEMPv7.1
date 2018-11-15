package org.minxc.emp.bpm.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.bpm.core.model.BpmnBusinessLink;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface BpmnBusinessLinkDao extends CommonDao<String, BpmnBusinessLink> {
	public List<BpmnBusinessLink> getByInstanceId(String var1);
}