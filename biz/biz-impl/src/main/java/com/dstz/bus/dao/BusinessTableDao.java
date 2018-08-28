package com.dstz.bus.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bus.model.BusinessTable;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BusinessTableDao extends BaseDao<String, BusinessTable> {
}