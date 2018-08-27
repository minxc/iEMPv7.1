package org.minxc.emp.organization.rest.controller;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import com.github.pagehelper.Page;
import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.organization.core.manager.UserRoleManager;
import org.minxc.emp.organization.core.model.UserRoleModelImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/org/userRole")
public class UserRoleController extends BaseController<UserRoleModelImpl> {
    @Resource
    UserRoleManager userRoleManager;

    /**
     * 用户角色管理列表(分页条件查询)数据
     */
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleId = RequestUtil.getString(request, "roleId");
        String userId = RequestUtil.getString(request, "userId");
        QueryFilter queryFilter = getQueryFilter(request);
        if (StringUtil.isNotEmpty(roleId)) {
            queryFilter.addParamsFilter("roleId", roleId);
        }
        if (StringUtil.isNotEmpty(userId)) {
            queryFilter.addParamsFilter("userId", userId);
        }
        Page<UserRoleModelImpl> userRoleList = (Page<UserRoleModelImpl>) userRoleManager.query(queryFilter);
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

        if (StringUtil.isNotEmpty(roleId)) {
            for (String userId : aryIds) {
                addUserRole(userId, roleId);
            }
        }
        writeSuccessResult(response, "添加成功");
    }

    private void addUserRole(String userId, String roleId) {
        if (userRoleManager.getByRoleIdUserId(roleId, userId) != null) return;

        UserRoleModelImpl userRole = new UserRoleModelImpl();
        userRole.setId(UniqueIdUtil.getSuid());
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleManager.create(userRole);
    }

    /**
     * 保存用户角色
     */
    @RequestMapping("saveUserRole")
    @CatchError
    public void saveUserRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = RequestUtil.getString(request, "userId");
        String[] aryIds = RequestUtil.getStringAryByStr(request, "groupIds");

        if (StringUtil.isNotEmpty(userId)) {
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
