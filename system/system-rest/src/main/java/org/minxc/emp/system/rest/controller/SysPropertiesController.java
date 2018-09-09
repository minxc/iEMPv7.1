package org.minxc.emp.system.rest.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.system.impl.manager.SysPropertiesManager;
import org.minxc.emp.system.impl.model.SystemPropertiesEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;


/**
 * 系统属性
 */
@Controller
@RequestMapping("/sys/sysProperties")
public class SysPropertiesController extends GenericController {
	
    @Resource
    SysPropertiesManager sysPropertiesManager;

    /**
     * 系统属性列表(分页条件查询)数据
     */
    @RequestMapping("listJson")
    public @ResponseBody
    PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<SystemPropertiesEntity> sysPropertiesList = (Page<SystemPropertiesEntity>) sysPropertiesManager.query(queryFilter);
        return new PageJson(sysPropertiesList);
    }


    /**
     * 系统属性明细页面
     */
    @RequestMapping("getJson")
    @ErrorCatching(writeErrorToResponse = true)
    public void getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        SystemPropertiesEntity sysProperties = new SystemPropertiesEntity();
        List<String> groups = sysPropertiesManager.getGroups();
        if (StringUtils.isEmpty(id)) {
            sysProperties.setCategorys(groups);
            writeSuccessData(response, sysProperties);
            return;
        }
        sysProperties = sysPropertiesManager.get(id);
        sysProperties.setCategorys(groups);
        writeSuccessData(response, sysProperties);
    }

    /**
     * 保存系统属性信息
     *
     * @param request
     * @param response
     * @param sysProperties
     * @throws Exception void
     * @throws
     */
    @RequestMapping("save")
    @ErrorCatching("对系统属性操作失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SystemPropertiesEntity sysProperties) throws Exception {
        String ResultMessage = null;

        boolean isExist = sysPropertiesManager.isExist(sysProperties);
        if (isExist) {
            throw new BusinessException("别名系统中已存在!");
        }

        String id = sysProperties.getId();
        sysProperties.setValByEncrypt();

        if (StringUtils.isEmpty(id)) {
            sysProperties.setId(UniqueIdUtil.getSuid());
            sysProperties.setCreateTime(new Date());
            sysPropertiesManager.create(sysProperties);
            ResultMessage = "添加系统属性成功";
        } else {
            sysPropertiesManager.update(sysProperties);
            ResultMessage = "更新系统属性成功";
        }

        sysPropertiesManager.reloadProperty();
        writeSuccessResult(response, ResultMessage);
    }

    /**
     * 批量删除系统属性记录
     */
    @RequestMapping("remove")
    @ErrorCatching("删除系统属性失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");

        sysPropertiesManager.removeByIds(aryIds);
        writeSuccessResult(response, "删除系统属性成功");
    }
}
