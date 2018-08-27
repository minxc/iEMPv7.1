package org.minxc.emp.organization.rest.controller;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.organization.core.manager.RoleManager;
import org.minxc.emp.organization.core.model.RoleModelImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 角色管理 控制器类
 */
@RestController
@RequestMapping("/org/role")
public class RoleController extends BaseController<RoleModelImpl> {
    @Resource
    RoleManager roleManager;


    @Override
    protected String getModelDesc() {
        return "角色";
    }

    @Override
    @CatchError
    public ResultMsg<String> save( @RequestBody RoleModelImpl role) throws Exception {
        if (StringUtil.isEmpty(role.getId())) {
            boolean isExist = roleManager.isRoleExist(role);
            if (isExist) {
                throw new BusinessException("角色在系统中已存在!");
            }
        }
       return super.save(role);
    }

}
