package org.minxc.emp.common.manager;

import org.minxc.emp.core.api.query.Page;
import org.minxc.emp.core.api.query.QueryFilter;

import java.io.Serializable;
import java.util.List;

/**
 * 
* 项目名称：common-db   
* 类名称：Manager   
* 类描述： 业务类管理实体类接口  
* 创建人：Xianchang.min   
* 创建时间：2018年9月3日 下午8:09:12   
* 修改人：Xianchang.min   
* 修改时间：2018年9月3日 下午8:09:12   
* 修改备注：   
* @version  1.0  
*
 */
public interface Manager<PK extends Serializable, T> {
    /**
     * 创建实体对象
     *
     * @param entity
     * @return
     */
    public void create(T entity);

    /**
     * 更新实体对象
     *
     * @param entity
     * @return
     */
    public void update(T entity);

    /**
     * 按实体ID删除对象
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
     * 按实体IDs删除记录
     *
     * @param ids
     */
    @SuppressWarnings("unchecked")
	public void removeByIds(PK... ids);

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
    public List<T> getAll();

    /**
     * 取得所有查询对象并分页查询
     *
     * @param page
     * @return
     */
    public List<T> getAllByPage(Page page);
}