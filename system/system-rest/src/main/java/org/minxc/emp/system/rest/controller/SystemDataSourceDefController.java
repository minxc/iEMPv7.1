package org.minxc.emp.system.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.system.impl.manager.SystemDataSourceDefManager;
import org.minxc.emp.system.impl.model.SysDataSourceDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * sysDataSourceDef层的controller
 */
@Controller
@RequestMapping("/sys/sysDataSourceDef/")
public class SystemDataSourceDefController extends GenericController {
	
    @Autowired
    SystemDataSourceDefManager sysDataSourceDefManager;

    /**
     * 
     * 根据类路径获取类字段
     * 
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("initAttributes")
    @ErrorCatching(writeErrorToResponse = true, value = "初始化属性异常")
    public void initAttributes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String classPath = RequestUtil.getString(request, "classPath");
        writeSuccessData(response, sysDataSourceDefManager.initAttributes(classPath));
    }

    /**
     * 
     * sysDataSourceDefEdit.html的save后端
     * 
     *
     * @param request
     * @param response
     * @param sysDataSourceDef
     * @throws Exception
     */
    @RequestMapping("save")
    @ErrorCatching(writeErrorToResponse = true, value = "保存数据源模板失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SysDataSourceDef sysDataSourceDef) throws Exception {
        if (StringUtils.isEmpty(sysDataSourceDef.getId())) {
            sysDataSourceDef.setId(UniqueIdUtil.getSuid());
            sysDataSourceDefManager.create(sysDataSourceDef);
        } else {
            sysDataSourceDefManager.update(sysDataSourceDef);
        }

        writeSuccessData(response, sysDataSourceDef, "保存数据源模板成功");
    }

    /**
     * 
     * 获取sysDataSourceDef的后端
     * 目前支持根据id 获取sysDataSourceDef
     * 
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getObject")
    @ErrorCatching(writeErrorToResponse = true, value = "获取sysDataSourceDef异常")
    public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        SysDataSourceDef sysDataSourceDef = null;
        if (StringUtils.isNotEmpty(id)) {
            sysDataSourceDef = sysDataSourceDefManager.get(id);
        }
        writeSuccessData(response, sysDataSourceDef);
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
        List<SysDataSourceDef> list = sysDataSourceDefManager.query(queryFilter);
        return new PageJson(list);
    }
}
