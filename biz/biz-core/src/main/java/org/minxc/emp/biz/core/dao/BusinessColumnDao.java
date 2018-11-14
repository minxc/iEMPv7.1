package org.minxc.emp.biz.core.dao;

import org.minxc.emp.common.db.dao.CommonDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.biz.core.model.BusinessColumnImpl;

@Mapper
public interface BusinessColumnDao extends CommonDao<String, BusinessColumnImpl> {
	public void removeByTableId(String tableId);

	public List<BusinessColumnImpl> getByTableId(String tableId);
}