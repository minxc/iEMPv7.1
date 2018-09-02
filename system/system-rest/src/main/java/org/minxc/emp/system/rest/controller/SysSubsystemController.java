package org.minxc.emp.system.rest.controller;

import com.github.pagehelper.Page;


import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.impl.manager.SubsystemManager;
import org.minxc.emp.system.impl.model.Subsystem;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <pre>
 * 描述：子系统定义 控制器类
 * </pre>
 */
@Controller
@RequestMapping("/sys/subsystem")
public class SysSubsystemController extends GenericController {
    @Resource
    SubsystemManager subsystemManager;

    /**
     * 子系统定义列表(分页条件查询)数据
     *
     * @param request
     * @param response
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @RequestMapping("listJson")
    public @ResponseBody
    PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<Subsystem> subsystemList = (Page<Subsystem>) subsystemManager.query(queryFilter);
        return new PageJson(subsystemList);
    }

    @RequestMapping("getUserSystem")
    @ErrorCatching(writeErrorToResponse = true)
    public @ResponseBody
    void getUserSystem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!ContextUtil.currentUserIsAdmin()) {
            throw new BusinessException("目前仅仅支持超管操作，尚未开发分管授权功能！");
        }

        List Subsystem = subsystemManager.getAll();
        writeSuccessData(response, Subsystem);
    }

    /**
     * 子系统定义明细页面
     *
     * @param request
     * @param response
     * @return
     * @throws Exception ModelAndView
     */
    @RequestMapping("getJson")
    public @ResponseBody
    Subsystem getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        Subsystem subsystem = subsystemManager.get(id);
        return subsystem;
    }

    /**
     * 保存子系统定义信息
     *
     * @param request
     * @param response
     * @param subsystem
     * @throws Exception void
     * @throws
     */
    @RequestMapping("save")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody Subsystem subsystem) throws Exception {
        String ResultMessage = null;

        boolean isExist = subsystemManager.isExist(subsystem);
        if (isExist) {
            ResultMessage = "别名子系统中已存在!";
        //    writeResultMessage(response.getWriter(), ResultMessage, ResultMessage.FAIL);
            return;
        }

        String id = subsystem.getId();
        try {
            if (StringUtils.isEmpty(id)) {
                subsystem.setId(UniqueIdUtil.getSuid());
                User user = ContextUtil.getCurrentUser();
                subsystem.setCreator(user.getFullname());
                subsystem.setCreatorId(user.getUserId());
                subsystemManager.create(subsystem);
                ResultMessage = "添加子系统定义成功";
            } else {
                subsystemManager.update(subsystem);
                ResultMessage = "更新子系统定义成功";
            }
      //      writeResultMessage(response.getWriter(), ResultMessage, ResultMessage.SUCCESS);
        } catch (Exception e) {
            ResultMessage = "对子系统定义操作失败";
    //        writeResultMessage(response.getWriter(), ResultMessage, e.getMessage(), ResultMessage.FAIL);
        }
    }

    /**
     * 批量删除子系统定义记录
     *
     * @param request
     * @param response
     * @throws Exception void
     * @throws
     */
    @RequestMapping("remove")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultMessage message = null;
        try {
            String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
            subsystemManager.removeByIds(aryIds);
            message = new ResultMessage(ResultMessage.SUCCESS, "删除子系统定义成功");
        } catch (Exception e) {
            message = new ResultMessage(ResultMessage.FAIL, "删除子系统定义失败");
        }
        writeResultMessage(response.getWriter(), message);
    }
}
