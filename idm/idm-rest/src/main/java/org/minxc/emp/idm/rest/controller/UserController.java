package org.minxc.emp.idm.rest.controller;

import com.dstz.base.core.encrypt.EncryptUtil;
import com.github.pagehelper.Page;
import com.minxc.emp.core.util.CryptoUtil;

import ch.qos.logback.core.util.ContextUtil;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.idm.impl.manager.GroupUserManager;
import org.minxc.emp.idm.impl.manager.UserManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * 描述：用户表 控制器类
 * </pre>
 */
@RestController
@RequestMapping("/org/user")
public class UserController extends CommonController<User> {
    @Resource
    UserManager userManager;
    @Resource
    GroupUserManager orgUserManager;

    /**
     * 获取用户下的组织列表
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("listUserOrgJson")
    public PageJson listUserOrgJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        String userId = RequestUtil.getString(request, "userId");
        queryFilter.addFilter("u.id_", userId, QueryOperator.EQUAL);
        Page<User> userList = (Page<User>) userManager.queryOrgUser(queryFilter);
        return new PageJson(userList);
    }

    /**
     * 获取用户下的岗位列表
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("listUserPostJson")
    public PageJson listUserPostJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        String userId = RequestUtil.getString(request, "userId");
        queryFilter.addFilter("orguser.user_id_", userId, QueryOperator.EQUAL);
        Page orgUserList = (Page) userManager.queryUserGroupRel(queryFilter);
        return new PageJson(orgUserList);
    }

    /**
     * 保存用户表信息
     *
     * @param request
     * @param response
     * @param user
     * @throws Exception void
     * @throws
     */
    @RequestMapping("save")
    @Override
    @ErrorCatching(writeErrorToResponse = true, value = "操作用户失败！")
    public ResultMessage<String> save( @RequestBody User user) throws Exception {
        String resultMsg = null;
        boolean isExist = userManager.isUserExist(user);
        if (isExist) {
            throw new BusinessException("用户在系统中已存在!");
        }

        String id = user.getId();
        if (StringUtils.isEmpty(id)) {
            user.setId(UniqueIdUtil.getSuid());
            String password = CryptoUtil.encodeSHA(user.getPassword());
            user.setPassword(password);
            //添加用户和组织的关系，默认为主关系。
            if (StringUtils.isNotEmpty(user.getGroupId())) {
                GroupUser orgUser = new GroupUser();
                orgUser.setId(UniqueIdUtil.getSuid());
                orgUser.setIsMaster(GroupUser.MASTER_YES);
                orgUser.setGroupId(user.getGroupId());
                orgUser.setUserId(user.getUserId());
                orgUserManager.create(orgUser);
            }
            userManager.create(user);
            resultMsg = "添加用户成功!";
        } else {
            userManager.update(user);
            resultMsg = "更新用户成功";
        }
        
        return getSuccessResult(user.getId(), resultMsg);
    }


    @RequestMapping("saveUserInfo")
    @ErrorCatching("更新失败")
    public void saveUserInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws Exception {
        userManager.update(user);
        writeSuccessData(response, "更新用户成功");
    }

    @RequestMapping("updateUserPsw")
    @ErrorCatching("更新密码失败")
    public void updateUserPsw(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oldPassWorld = RequestUtil.getString(request, "oldPassWorld");
        String newPassword = RequestUtil.getString(request, "newPassword");

        User user = userManager.get(ContextUtil.getCurrentUserId());
        if (!user.getPassword().equals(CryptoUtil.encodeSHA(oldPassWorld))) {
            throw new BusinessException("旧密码输入错误");
        }

        user.setPassword(CryptoUtil.encodeSHA(newPassword));
        userManager.update(user);
        writeSuccessResult(response, "更新密码成功");

    }


    @RequestMapping("getUserByGroupJson")
    public PageJson getUserByGroupJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        String orgId = RequestUtil.getString(request, "orgId");
        String relId = RequestUtil.getString(request, "relId");
        queryFilter.addFilter("orguser.org_id_", orgId, QueryOP.EQUAL);
        if (StringUtil.isNotEmpty(relId)) {
            queryFilter.addFilter("orguser.rel_id_", relId, QueryOP.EQUAL);
        }
        Page orgUserList = (Page) orgUserManager.getUserByGroup(queryFilter);
        return new PageJson(orgUserList);
    }

    @Override
    protected String getModelDesc() {
        return "用户";
    }
}
