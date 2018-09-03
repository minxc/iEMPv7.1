package org.minxc.emp.system.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.ApplicationEntity;



/**
 * 子系统定义 DAO接口
 */
@Mapper
public interface SubsystemDao extends CommonDao<String, ApplicationEntity> {

    /**
     * 判断别名是否存在
     *
     * @param subsystem
     * @return
     */
	Integer isExist(ApplicationEntity subsystem);

    /**
     * 获取子系统列表。
     *
     * @return
     */
    List<ApplicationEntity> getList();

    /**
     * 更新为默认。
     */
    void updNoDefault();


    /**
     * 根据用户获取子系统列表。
     *
     * @param userId
     * @return
     */
    List<ApplicationEntity> getSystemByUser(String userId);
}
