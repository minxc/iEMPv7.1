package org.minxc.emp.basis.impl.core.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.basis.impl.core.model.WorkbenchPanel;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;


@Mapper
public interface WorkbenchPanelDao extends CommonDao<String, WorkbenchPanel> {
    /**
     * 获取用户可用的
     *
     * @param query
     * @return
     */
    List<WorkbenchPanel> getUsablePanelsByUserRight(QueryFilter query);

    /**
     * 通过权限过滤获取用户的panel
     *
     * @param userPermission
     * @return
     */
    List<WorkbenchPanel> getByUser(Map<String, Object> userPermission);


    List<WorkbenchPanel> getBylayoutKey(String layoutKey);

}
