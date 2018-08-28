package org.minxc.emp.biz.core.dao;

import com.dstz.base.dao.BaseDao;

import org.apache.ibatis.annotations.Param;
import org.minxc.emp.biz.core.model.BusinessPermission;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BusinessPermissionDao extends BaseDao<String, BusinessPermission> {
	public BusinessPermission getByObjTypeAndObjVal(@Param(value = "objType") String objType,
			@Param(value = "objVal") String objVal);
}