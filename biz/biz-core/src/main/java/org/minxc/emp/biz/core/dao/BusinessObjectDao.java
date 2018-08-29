package org.minxc.emp.biz.core.dao;

import com.dstz.base.dao.BaseDao;

import org.minxc.emp.biz.core.model.BusinessObject;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BusinessObjectDao extends BaseDao<String, BusinessObject> {
}