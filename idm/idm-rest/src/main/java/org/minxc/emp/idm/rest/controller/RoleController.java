package org.minxc.emp.idm.rest.controller;


import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.idm.impl.manager.RoleManager;
import org.minxc.emp.idm.impl.model.RoleEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色管理 控制器类
 */
@RestController
@RequestMapping("/org/role")
public class RoleController extends CommonController<RoleEntity> {
    @Resource
    RoleManager roleManager;


    @Override
    protected String getModelDesc() {
        return "角色";
    }

    @Override
    @ErrorCatching
    public ResultMessage<String> save( @RequestBody RoleEntity role) throws Exception {
        if (StringUtils.isEmpty(role.getId())) {
            boolean isExist = roleManager.isRoleExist(role);
            if (isExist) {
                throw new BusinessException("角色在系统中已存在!");
            }
        }
       return super.save(role);
    }

}
