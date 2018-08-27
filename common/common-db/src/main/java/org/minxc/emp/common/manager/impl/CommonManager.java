package org.minxc.emp.common.manager.impl;

import java.io.Serializable;
import java.util.List;

import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.Page;
import org.minxc.emp.core.api.query.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <pre>
 * 描述：抽象实体业务管理类实现
 * </pre>
 */
public abstract class CommonManager<PK extends Serializable, T extends Serializable> implements Manager<PK, T> {
    //获取基础类

    @Autowired
    protected CommonDao<PK, T> dao;
    
    public void create(T entity) {
        dao.create(entity);
    }

   
    public void update(T entity) {
        dao.update(entity);
    }

    public void remove(PK entityId) {
        dao.remove(entityId);
    }


    public T get(PK entityId) {
        return dao.get(entityId);
    }

   
    public void removeByIds(PK... ids) {
        if (ids != null) {
            for (PK pk : ids) {
                remove(pk);
            }
        }
    }

  
    public List<T> query(QueryFilter queryFilter) {
        return dao.query(queryFilter);
    }

    /**
     * <pre>
     * 根据QueryFilter获取唯一值
     * </pre>
     * @deprecated 请尽量写SQL。列表查询不会包含大数据文本 比如 form.html
     * @param queryFilter
     * @return
     */
    public T queryOne(QueryFilter queryFilter) {
        List<T> list = query(queryFilter);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

  
    public List<T> getAll() {
        return dao.query();
    }
    
	@Override
	public List<T> getAllByPage(Page page) {
		return null;
	}

}
