package org.minxc.emp.security.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minxc.emp.core.util.AppContextUtil;
import com.minxc.emp.core.util.BeanUtils;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.idm.api.constant.GroupTypeConstant;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.service.GroupService;
import org.minxc.emp.security.util.SubSystemUtil;
import org.minxc.emp.system.api.service.SysResourceService;
import org.minxc.emp.system.impl.model.Subsystem;
import org.minxc.emp.system.impl.model.SysResource;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

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
    SysResourceService SystemResourceService;


    @RequestMapping("userResource/userMsg")
    @ErrorCatching
    public ResultMessage<JSONObject> userMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Subsystem> subsystemList = SystemResourceService.getCurrentUserSystem();
        JSONObject mv = new JSONObject();
        if (BeanUtils.isEmpty(subsystemList)) {
            throw new BusinessException("当前用户尚未分配任何资源权限！");
        }

        String systemId = SubSystemUtil.getSystemId(request);
        Subsystem currentSystem = null;
        //获取当前系统
        if (StringUtils.isNotEmpty(systemId)) {
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

        mv.put("currentEnviroment",AppContextUtil.getCtxEnvironment());
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
    public ResultMessage changeSystem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = RequestUtil.getString(request, "id");
        SubSystemUtil.setSystemId(request, response, id);

        return getSuccessResult("切换成功");
    }
    // 重新获取 userMsg
    @RequestMapping("userResource/changeOrg")
    public ResultMessage changeOrg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = RequestUtil.getString(request, "id");
        Group org = groupService.getById(GroupTypeConstant.ORG.key(), id);
        ContextUtil.setCurrentOrg(org);

        return getSuccessResult("切换成功");
    }

    @RequestMapping("userResource/getResTree")
    public List<SysResource> getSysResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
