package org.minxc.emp.biz.core.dao;


import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.biz.core.model.BusinessTableImpl;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface BusinessTableDao extends CommonDao<String, BusinessTableImpl> {
}