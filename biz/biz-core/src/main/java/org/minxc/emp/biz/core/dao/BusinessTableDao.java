package org.minxc.emp.biz.core.dao;


import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.biz.core.model.BusinessTable;
import org.minxc.emp.common.db.dao.CommonDao;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface BusinessTableDao extends CommonDao<String, BusinessTable> {
}