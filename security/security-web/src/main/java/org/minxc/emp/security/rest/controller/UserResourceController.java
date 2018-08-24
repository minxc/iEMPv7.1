package org.minxc.emp.security.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.security.util.SubSystemUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.AppUtil;
import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.organization.api.constant.GroupTypeConstant;
import org.minxc.emp.organization.api.model.Group;
import org.minxc.emp.organization.api.model.User;
import org.minxc.emp.organization.api.service.GroupService;
import org.minxc.emp.system.api.model.system.Subsystem;
import org.minxc.emp.system.api.model.system.SystemResource;
import org.minxc.emp.system.api.service.SystemResourceService;
import org.minxc.emp.system.util.ContextUtil;

/**
 * 用户资源
 *
 * @author min.xianchang
 */
@RestController
public class UserResourceController extends GenericController {
    @Resource
    private GroupService groupService;
    @Resource
    SystemResourceService SystemResourceService;


    @RequestMapping("userResource/userMsg")
    @CatchError
    public ResultMsg<JSONObject> userMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Subsystem> subsystemList = SystemResourceService.getCurrentUserSystem();
        JSONObject mv = new JSONObject();
        if (BeanUtils.isEmpty(subsystemList)) {
            throw new BusinessException("当前用户尚未分配任何资源权限！");
        }

        String systemId = SubSystemUtil.getSystemId(request);
        Subsystem currentSystem = null;
        //获取当前系统
        if (StringUtil.isNotEmpty(systemId)) {
            for (Subsystem system : subsystemList) {
                if (system.getId().equals(systemId)) {
                    currentSystem = system;
                    break;
                }
            }
        } else {
            //获取默认系统
            currentSystem = SystemResourceService.getDefaultSystem(ContextUtil.getCurrentUserId());
        }

        //没有之前使用的系统
        if (currentSystem == null) {
            currentSystem = subsystemList.get(0);
        }
        SubSystemUtil.setSystemId(request, response, currentSystem.getId());

        Group group = ContextUtil.getCurrentGroup();
        List<Group> orgList = groupService.getGroupsByGroupTypeUserId(GroupTypeConstant.ORG.key(), ContextUtil.getCurrentUserId());

        mv.put("currentEnviroment",AppUtil.getCtxEnvironment());
        mv.put("subsystemList", subsystemList);
        mv.put("currentSystem", currentSystem);
        mv.put("currentOrg", group);
        mv.put("orgList", orgList);
        mv.put("user", ContextUtil.getCurrentUser());
        mv.put("resourceList", getSysResource(request, response));
        return getSuccessResult(mv);
    }

    
    // 重新获取 userMsg
    @RequestMapping("userResource/changeSystem")
    public ResultMsg changeSystem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = RequestUtil.getString(request, "id");
        SubSystemUtil.setSystemId(request, response, id);

        return getSuccessResult("切换成功");
    }
    // 重新获取 userMsg
    @RequestMapping("userResource/changeOrg")
    public ResultMsg changeOrg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = RequestUtil.getString(request, "id");
        Group org = groupService.getById(GroupTypeConstant.ORG.key(), id);
        ContextUtil.setCurrentOrg(org);

        return getSuccessResult("切换成功");
    }

    @RequestMapping("userResource/getResTree")
    public List<SystemResource> getSysResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = ContextUtil.getCurrentUser();
        String systemId = SubSystemUtil.getSystemId(request);
        boolean isAdmin = ContextUtil.isAdmin(user);
        List<SystemResource> list = null;
        if (isAdmin) {
            list = SystemResourceService.getBySystemId(systemId);
        } else {
            list = SystemResourceService.getBySystemAndUser(systemId, user.getUserId());
        }

        return BeanUtils.listToTree(list);
    }
}
