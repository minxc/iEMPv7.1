package org.minxc.emp.system.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.SysAuthorization;

@Mapper
public interface SysAuthorizationDao extends CommonDao<String, SysAuthorization> {
	
    public List<SysAuthorization> getByTarget(@Param("rightsObject")String rightsObject, @Param("rightsTarget")String rightsTarget);

    public void deleteByTarget(@Param("rightsObject")String rightsObject, @Param("rightsTarget")String rightsTarget);

}
