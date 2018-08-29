package org.minxc.emp.biz.core.dao;

import com.dstz.base.dao.BaseDao;

import org.minxc.emp.biz.core.model.BusinessTable;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BusinessTableDao extends BaseDao<String, BusinessTable> {
}