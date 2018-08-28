package com.dstz.bus.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bus.model.BusinessObject;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BusinessObjectDao extends BaseDao<String, BusinessObject> {
}