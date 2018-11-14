package org.minxc.emp.biz.core.dao;


import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.biz.core.model.BusinessObjectImpl;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface BusinessObjectDao extends CommonDao<String, BusinessObjectImpl> {
}