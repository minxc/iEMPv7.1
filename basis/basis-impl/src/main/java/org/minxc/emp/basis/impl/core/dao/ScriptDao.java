package org.minxc.emp.basis.impl.core.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.basis.impl.core.model.Script;
import org.minxc.emp.common.db.dao.CommonDao;

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
