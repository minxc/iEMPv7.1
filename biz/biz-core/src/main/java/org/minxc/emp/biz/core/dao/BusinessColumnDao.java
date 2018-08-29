package org.minxc.emp.biz.core.dao;

import org.minxc.emp.common.db.dao.CommonDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.biz.core.model.BusinessColumn;

@Mapper
public interface BusinessColumnDao extends CommonDao<String, BusinessColumn> {
	public void removeByTableId(String tableId);

	public List<BusinessColumn> getByTableId(String tableId);
}