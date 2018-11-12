package org.minxc.emp.idm.rest.controller;

import com.github.pagehelper.Page;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.idm.impl.manager.UserRoleManager;
import org.minxc.emp.idm.impl.model.UserRoleEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/org/userRole")
public class UserRoleController extends CommonController<UserRoleEntity> {
	
    @Resource
    private UserRoleManager userRoleManager;

    /**
     * 用户角色管理列表(分页条件查询)数据
     */
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleId = RequestUtil.getString(request, "roleId");
        String userId = RequestUtil.getString(request, "userId");
        QueryFilter queryFilter = getQueryFilter(request);
        if (StringUtils.isNotEmpty(roleId)) {
            queryFilter.addParamsFilter("roleId", roleId);
        }
        if (StringUtils.isNotEmpty(userId)) {
            queryFilter.addParamsFilter("userId", userId);
        }
        Page<UserRoleEntity> userRoleList = (Page<UserRoleEntity>) userRoleManager.query(queryFilter);
        return new PageJson(userRoleList);
    }

    /**
     * 保存角色下的用户
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("saveRoleUsers")
    public void saveRoleUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleId = RequestUtil.getString(request, "roleId");
        String[] aryIds = RequestUtil.getStringAryByStr(request, "userId");

        if (StringUtils.isNotEmpty(roleId)) {
            for (String userId : aryIds) {
                addUserRole(userId, roleId);
            }
        }
        writeSuccessResult(response, "添加成功");
    }

    private void addUserRole(String userId, String roleId) {
        if (userRoleManager.getByRoleIdUserId(roleId, userId) != null) return;

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setId(UniqueIdUtil.getSuid());
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleManager.create(userRole);
    }

	/**
	 * 保存用户角色
	 */
    @RequestMapping("saveUserRole")
    @ErrorCatching
    public void saveUserRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = RequestUtil.getString(request, "userId");
        String[] aryIds = RequestUtil.getStringAryByStr(request, "groupIds");

        if (StringUtils.isNotEmpty(userId)) {
            for (String roleId : aryIds) {
                addUserRole(userId, roleId);
            }
        }

        writeSuccessResult(response, "添加成功");
    }

    @Override
    protected String getModelDesc() {
        return "用户角色";
    }
}
