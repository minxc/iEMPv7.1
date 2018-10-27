package org.minxc.emp.system.rest.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.system.impl.manager.DataSourceManager;
import org.minxc.emp.system.impl.model.SystemDataSourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 sysDataSource层的controller
 */
@Controller
@RequestMapping("/sys/sysDataSource/")
public class SystemDataSourceController extends GenericController {
    @Autowired
    DataSourceManager sysDataSourceManager;

    /**
     * <pre>
     * 测试连接
     * </pre>
     *
     * @param request
     * @param response
     * @param sysDataSource
     * @throws Exception
     */
    @RequestMapping("checkConnection")
    @ErrorCatching(writeErrorToResponse = true, value = "连接失败")
    public void checkConnection(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String key = RequestUtil.getString(request, "key");
    	boolean connectable = false;
        try {
        	DataSource dataSource = sysDataSourceManager.getDataSourceByKey(key);
        	Connection connection = dataSource.getConnection();
            connection.close();
            connectable = true;
        } catch (Exception e) {
        }
    	
        writeSuccessData(response, connectable, connectable ? "连接成功" : "连接失败");
    }

    /**
     * <pre>
     * sysDataSourceEdit.html的save后端
     * </pre>
     *
     * @param request
     * @param response
     * @param sysDataSource
     * @throws Exception
     */
    @RequestMapping("save")
    @ErrorCatching(writeErrorToResponse = true, value = "保存数据源失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SystemDataSourceEntity sysDataSource) throws Exception {
        if (StringUtils.isEmpty(sysDataSource.getId())) {
            sysDataSource.setId(UniqueIdUtil.getSuid());
            sysDataSourceManager.create(sysDataSource);
        } else {
            sysDataSourceManager.update(sysDataSource);
        }
        writeSuccessData(response, sysDataSource, "保存数据源成功");
    }

    /**
     * <pre>
     * 获取sysDataSource的后端
     * 目前支持根据id 获取sysDataSource
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getObject")
    @ErrorCatching(writeErrorToResponse = true, value = "获取sysDataSource异常")
    public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        SystemDataSourceEntity sysDataSource = null;
        if (StringUtils.isNotEmpty(id)) {
            sysDataSource = sysDataSourceManager.get(id);
        }
        writeSuccessData(response, sysDataSource);
    }

    /**
     * <pre>
     * list页的后端
     * </pre>
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
        List<SystemDataSourceEntity> list = sysDataSourceManager.query(queryFilter);
        return new PageJson(list);
    }

    /**
     * <pre>
     * 批量删除
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("remove")
    @ErrorCatching(writeErrorToResponse = true, value = "删除数据源失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
        sysDataSourceManager.removeByIds(aryIds);
        writeSuccessResult(response, "删除数据源成功");
    }
}
