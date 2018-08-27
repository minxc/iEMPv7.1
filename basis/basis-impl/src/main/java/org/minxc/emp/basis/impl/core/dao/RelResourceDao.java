package org.minxc.emp.basis.impl.core.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.basis.impl.core.model.RelResource;
import org.minxc.emp.common.db.dao.CommonDao;

/**
 * 描述：关联资源 DAO接口
 */
@Mapper
public interface RelResourceDao extends CommonDao<String, RelResource> {

    List<RelResource> getByResourceId(String resId);

    /**
     * 根据资源id删除关联资源。
     *
     * @param resId
     */
    void removeByResId(String resId);
}
