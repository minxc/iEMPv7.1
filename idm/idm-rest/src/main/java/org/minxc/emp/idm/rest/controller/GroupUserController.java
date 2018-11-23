package org.minxc.emp.idm.rest.controller;

import com.github.pagehelper.Page;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.impl.manager.GroupRelationManager;
import org.minxc.emp.idm.impl.manager.GroupUserManager;
import org.minxc.emp.idm.impl.manager.UserManager;
import org.minxc.emp.idm.impl.model.GroupUserLinkModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户组织关系 控制器类
 */
@RestController
@RequestMapping("/org/groupUser")
public class GroupUserController extends GenericController {
	
    @Resource
    private GroupUserManager groupUserManager;
    @Resource
    private UserManager userManager;
    @Resource
    private GroupRelationManager groupRelationManager;

    /**
     * 用户组织关系列表(分页条件查询)数据
     */
    @RequestMapping("groupUserList")
    public PageJson listGroupUserJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String groupId = RequestUtil.getString(request, "groupId");
        String relId = RequestUtil.getString(request, "relId");

        QueryFilter queryFilter = getQueryFilter(request);
        queryFilter.addFilter("org.id_", groupId, QueryOperator.EQUAL);
        if (StringUtils.isNotEmpty(relId)) {
            queryFilter.addFilter("rel.rel_id_", relId, QueryOperator.EQUAL);
        }

        Page<Map> userList = (Page<Map>) groupUserManager.getUserByGroup(queryFilter);
        return new PageJson(userList);
    }


	/**
	 * 用户组织关系明细页面
	 */
    @RequestMapping("getJson")
    public GroupUserLinkModel getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        if (StringUtils.isEmpty(id)) {
            return null;
        }

        GroupUserLinkModel GroupUser = groupUserManager.get(id);
        return GroupUser;
    }


	/**
	 * 分配用户岗位
	 */
    @RequestMapping("saveGroupUserRel")
    @ErrorCatching
    public void saveGroupUserRel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] relIds = RequestUtil.getStringAryByStr(request, "relIds");
        String[] userId = RequestUtil.getStringAryByStr(request, "userIds");
        String grouId = RequestUtil.getString(request, "groupId");

        groupUserManager.saveGroupUserRel(grouId, userId, relIds);

        writeSuccessResult(response, "分配用户岗位成功");
    }

    /**
     * 批量删除用户组织关系记录
     *
     * @param request
     * @param response
     * @throws Exception void
     * @throws
     */
    @RequestMapping("remove")
    @ErrorCatching
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
        groupUserManager.removeByIds(aryIds);
        writeSuccessResult(response, "成功删除用户组织岗位关系");
    }

    /**
     *  通过 组织用户删除关系
     */
    @RequestMapping("removeByOrgIdUserId")
    @ErrorCatching
    public void removeByOrgIdUserId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = RequestUtil.getString(request, "userId");
        String orgId = RequestUtil.getString(request, "orgId");

        groupUserManager.removeByOrgIdUserId(orgId, userId);

        writeSuccessResult(response, "成功删除用户组织关系");
    }


    @RequestMapping("setMaster")
    @ErrorCatching
    public void setMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        groupUserManager.setMaster(id);
        writeSuccessResult(response, "设置用户主组织成功!");
    }


    /**
     * 分配用户岗位
     *
     * @param request
     * @param response
     * @param GroupUser
     * @throws Exception
     */
    @RequestMapping("saveUserRels")
    @ErrorCatching
    public void saveUserPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] relIds = RequestUtil.getStringAryByStr(request, "relIds");
        String[] groupIds = RequestUtil.getStringAryByStr(request, "groupIds");
        String userId = RequestUtil.getString(request, "userId");


        String relId = null;
        for (int i = 0; i < groupIds.length; i++) {
            String orgId = groupIds[i];

            if (BeanUtils.isNotEmpty(relIds)) {
                relId = relIds[i];
            }

            GroupUserLinkModel groupUser = groupUserManager.getGroupUser(orgId, userId, relId);
            if (groupUser == null) {
            	groupUser = groupUserManager.getGroupUser(orgId, userId, "");
                if (groupUser == null) {
                	groupUser = new GroupUserLinkModel();
                	groupUser.setId(UniqueIdUtil.getSuid());
                	groupUser.setGroupId(orgId);
                	groupUser.setUserId(userId);
                	groupUser.setPositionId(relId);
                	groupUser.setIsMaster(0);
                    groupUserManager.create(groupUser);
                } else {
                	groupUser.setPositionId(relId);
                    groupUserManager.update(groupUser);
                }
            }
        }

        writeSuccessResult(response, "分配用户岗位成功");
    }
}
