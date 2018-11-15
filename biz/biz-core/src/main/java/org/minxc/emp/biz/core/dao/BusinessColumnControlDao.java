package org.minxc.emp.biz.core.dao;


import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.biz.core.model.BusinessColumnControlImpl;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface BusinessColumnControlDao extends CommonDao<String, BusinessColumnControlImpl> {

	public void removeByTableId(String tableId);

	public BusinessColumnControlImpl getByColumnId(String columnId);
}