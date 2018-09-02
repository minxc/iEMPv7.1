package org.minxc.emp.system.impl.manager;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.system.impl.model.WorkbenchPanel;


public interface WorkbenchPanelManager extends Manager<String, WorkbenchPanel> {
    /**
     * 获取用户的工作台面板，按照布局排序，通过权限过滤，未布局则不展示
     *
     * @param currentUserId
     * @return
     */
    List<WorkbenchPanel> getByUserId(String currentUserId);

    /**
     * 获取panel的数据
     *
     * @param requstParam
     * @return
     */

    JSON getPanelData(Map<String, String> requstParam);

    JSON getDataByInterFace(QueryFilter filter, String dataSource);

    /**
     * 获取可用的日程
     *
     * @param query
     * @return
     */
    List<WorkbenchPanel> getMyUsablePanels(QueryFilter query);

    List<WorkbenchPanel> getBylayoutKey(String layoutKey);

}
