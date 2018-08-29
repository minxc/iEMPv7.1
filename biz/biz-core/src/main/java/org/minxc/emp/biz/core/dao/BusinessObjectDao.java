package org.minxc.emp.biz.core.dao;


import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.biz.core.model.BusinessObject;
import org.minxc.emp.common.db.dao.CommonDao;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface BusinessObjectDao extends CommonDao<String, BusinessObject> {
}