package org.minxc.emp.basis.impl.core.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.basis.impl.core.model.SysProperties;
import org.minxc.emp.common.db.dao.CommonDao;


/**
 * SYS_PROPERTIES DAO接口
 */
@Mapper

public interface SysPropertiesDao extends CommonDao<String, SysProperties> {

    /**
     * 分组列表。
     *
     * @return
     */
    List<String> getGroups();


    /**
     * 判断别名是否存在。
     *
     * @param sysProperties
     * @return
     */
    Integer isExist(SysProperties sysProperties);
}
