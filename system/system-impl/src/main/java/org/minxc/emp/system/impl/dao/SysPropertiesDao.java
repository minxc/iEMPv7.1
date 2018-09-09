package org.minxc.emp.system.impl.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.SystemPropertiesEntity;


/**
 * SYS_PROPERTIES DAO接口
 */
@Mapper

public interface SysPropertiesDao extends CommonDao<String, SystemPropertiesEntity> {

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
    Integer isExist(SystemPropertiesEntity sysProperties);
}
