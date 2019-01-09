package org.minxc.emp.common.db.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.core.api.query.Page;
import org.minxc.emp.core.api.query.QueryFilter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @Title: BasicDao
 * @Package: org.minxc.emp.common.db.dao
 * @Description: 最基本的Dao,主要是利用原生的Mybatis处理
 * @author: Xianchang.min
 * @date 2018/8/25 19:06
 */

@Mapper
public class BasicDao<T> {

    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;

    private static final String NAME_SPACE = "org.minxc.emp.common.db.dao"; // mybatis命名空间

    /**
     * 执行sql语句
     *
     * @param sql void
     */
    public void execute(String sql) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("sql", sql);
        String key = getNameSpace("execute");
        sqlSessionTemplate.update(key, map);
    }

    /**
     * 查询列表
     *
     * @param sql
     * @return List
     */
    public List<T> query(String sql) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("sql", sql);
        String key = getNameSpace("query");
        return sqlSessionTemplate.selectList(key, map);
    }

    /**
     * 查询分页列表自行强转 com.github.pagehelper.Page<E>
     * @param sql
     * @return List
     */
    public List<T> query(String sql, Page page) {

        DefaultQueryFilter query = new DefaultQueryFilter();
        query.addParamsFilter("sql", sql);
        query.setPage(page);

        String key = getNameSpace("query");
        return  sqlSessionTemplate.selectList(key,  query);
    }

    private String getNameSpace(String sqlKey) {
        return NAME_SPACE + "." + sqlKey;
    }


    /**
     * 分页查询， 自行强转 com.github.pagehelper.Page<E> 获取page信息
     * @param sql
     * @param queryFilter
     * @return
     */
    public List<T> queryForListPage(String sql, QueryFilter queryFilter) {
        Assert.notNull(sql, "SQL语句不能为空。");
        queryFilter.addParamsFilter("sql", sql);
        return  sqlSessionTemplate.selectList(this.getNameSpace("queryFormList"), queryFilter);
    }
}
