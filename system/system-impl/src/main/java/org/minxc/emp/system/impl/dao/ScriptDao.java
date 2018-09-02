package org.minxc.emp.system.impl.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.Script;

@Mapper
public interface ScriptDao extends CommonDao<String, Script> {

    /**
     * 返回所有脚本的分类
     *
     * @return
     */
    public List<String> getDistinctCategory();

    /**
     * 判断脚本名称是否存在
     *
     * @param name
     * @return
     */
    public Integer isNameExists(String name);
}
