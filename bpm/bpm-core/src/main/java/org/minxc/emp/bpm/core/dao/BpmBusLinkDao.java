package org.minxc.emp.bpm.core.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.bpm.core.model.BpmBusLink;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface BpmBusLinkDao extends CommonDao<String, BpmBusLink> {
	public List<BpmBusLink> getByInstanceId(String var1);
}