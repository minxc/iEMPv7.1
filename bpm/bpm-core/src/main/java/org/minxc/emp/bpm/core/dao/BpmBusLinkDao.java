package org.minxc.emp.bpm.core.dao;

import com.dstz.base.dao.BaseDao;

import java.util.List;

import org.minxc.emp.bpm.core.model.BpmBusLink;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BpmBusLinkDao extends BaseDao<String, BpmBusLink> {
	public List<BpmBusLink> getByInstanceId(String var1);
}