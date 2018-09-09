package org.minxc.emp.system.impl.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.ResourceLinkEntity;

/**
 * 描述：关联资源 DAO接口
 */
@Mapper
public interface RelResourceDao extends CommonDao<String, ResourceLinkEntity> {

    List<ResourceLinkEntity> getByResourceId(String resId);

    /**
     * 根据资源id删除关联资源。
     *
     * @param resId
     */
    void removeByResId(String resId);
}
