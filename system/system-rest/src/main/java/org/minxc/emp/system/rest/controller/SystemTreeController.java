package org.minxc.emp.system.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.system.impl.manager.SystemTreeManager;
import org.minxc.emp.system.impl.manager.SystemTreeNodeManager;
import org.minxc.emp.system.impl.model.TreeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * sysTree层的controller
 */
@Controller
@RequestMapping("/sys/sysTree/")
public class SystemTreeController extends GenericController {
	
    @Autowired
    SystemTreeManager sysTreeManager;
    @Autowired
    SystemTreeNodeManager sysTreeNodeManager;

    /**
     * 
     * sysTreeEdit.html的save后端
     * 
     *
     * @param request
     * @param response
     * @param sysTree
     * @throws Exception
     */
    @RequestMapping("save")
    @ErrorCatching(writeErrorToResponse = true, value = "保存系统树失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody TreeEntity sysTree) throws Exception {
        if (StringUtils.isEmpty(sysTree.getId())) {
            sysTree.setId(UniqueIdUtil.getSuid());
            sysTreeManager.create(sysTree);
        } else {
            sysTreeManager.update(sysTree);
        }
        writeSuccessData(response, sysTree, "保存系统树成功");
    }

    /**
     * 
     * 获取sysTree的后端
     * 
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getObject")
    @ErrorCatching(writeErrorToResponse = true, value = "获取sysTree异常")
    public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        String key = RequestUtil.getString(request, "key");
        TreeEntity sysTree = null;
        if (StringUtils.isNotEmpty(id)) {
            sysTree = sysTreeManager.get(id);
        } else if (StringUtils.isNotEmpty(key)) {
            sysTree = sysTreeManager.getByKey(key);
        }
        writeSuccessData(response, sysTree);
    }

    /**
     * 
     * list页的后端
     * 
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("listJson")
    @ResponseBody
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        List<TreeEntity> list = sysTreeManager.query(queryFilter);
        return new PageJson(list);
    }

    /**
     * 
     * 批量删除
     * 
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("remove")
    @ErrorCatching(writeErrorToResponse = true, value = "删除系统树失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
        for (String id : aryIds) {
            sysTreeManager.removeContainNode(id);
        }
        writeSuccessResult(response, "删除系统树成功");
    }
}
