package org.minxc.emp.biz.core.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.biz.core.model.BusinessPermissionImpl;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface BusinessPermissionDao extends CommonDao<String, BusinessPermissionImpl> {
	public BusinessPermissionImpl getByObjTypeAndObjVal(@Param(value = "objType") String objType,
                                                        @Param(value = "objVal") String objVal);
}