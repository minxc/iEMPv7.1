package org.minxc.emp.basis.impl.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.basis.impl.core.model.SysAuthorization;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface SysAuthorizationDao extends CommonDao<String, SysAuthorization> {
	
    public List<SysAuthorization> getByTarget(@Param("rightsObject")String rightsObject, @Param("rightsTarget")String rightsTarget);

    public void deleteByTarget(@Param("rightsObject")String rightsObject, @Param("rightsTarget")String rightsTarget);

}
