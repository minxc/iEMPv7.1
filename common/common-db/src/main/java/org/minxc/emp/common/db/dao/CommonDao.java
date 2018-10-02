package org.minxc.emp.common.db.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.core.api.query.QueryFilter;

import java.io.Serializable;
import java.util.List;

/**
 * @version V1.0
 * @Title: CommonDao
 * @Package: org.minxc.emp.common.db.dao
 * @Description:
 * @author: Xianchang.min
 * @date 2018/8/26 20:09
 */


public interface CommonDao<PK extends Serializable, T> {
    /**
     *  创建实体对象
     *
     * @param entity
     * @return
     */
    public void create(T entity);

    /**
     *  更新实体对象
     *
     * @param entity
     * @return
     */
    public Integer update(T entity);

    /**
     *  按实体ID删除对象
     *
     * @param entityId
     */
    public void remove(PK entityId);

    /**
     * 按实体ID获取实体
     *
     * @param entityId
     */
    public T get(PK entityId);

    /**
     * 查询实体对象
     *
     * @param queryFilter
     * @return
     */
    public List<T> query(QueryFilter queryFilter);
    

    /**
     * 取得所有查询对象
     *
     * @return
     */
    public List<T> query();

}