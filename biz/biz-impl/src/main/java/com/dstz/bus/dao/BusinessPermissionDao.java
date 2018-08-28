package com.dstz.bus.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bus.model.BusinessPermission;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BusinessPermissionDao extends BaseDao<String, BusinessPermission> {
	public BusinessPermission getByObjTypeAndObjVal(@Param(value = "objType") String objType,
			@Param(value = "objVal") String objVal);
}