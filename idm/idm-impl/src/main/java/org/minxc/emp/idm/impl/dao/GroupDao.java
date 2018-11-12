package org.minxc.emp.idm.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.idm.impl.model.GroupEntity;

import java.util.List;

import org.minxc.emp.common.db.dao.CommonDao;


/**
 * 组织架构 DAO接口
 */
@Mapper
public interface GroupDao extends CommonDao<String, GroupEntity> {
    /**
     * 根据Code取定义对象。
     *
     * @param code
     * @return
     */
    GroupEntity getByCode(String code);

    /**
     * 根据用户ID获取组织列表
     *
     * @param userId
     * @return
     */
    List<GroupEntity> getByUserId(String userId);


}
