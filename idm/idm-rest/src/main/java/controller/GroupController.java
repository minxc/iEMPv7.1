package org.minxc.emp.organization.rest.controller;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import com.github.pagehelper.Page;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.organization.core.manager.GroupManager;
import org.minxc.emp.organization.core.manager.UserManager;
import org.minxc.emp.organization.core.model.GroupModelImpl;
import org.minxc.emp.organization.core.model.OrgTree;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 组
 */
@RestController
@RequestMapping("/org/group")
public class GroupController extends BaseController<GroupModelImpl> {
    @Resource
    private GroupManager empGroupManager;
    @Resource
    private UserManager userManager;

    /**
     * 组织架构列表(分页条件查询)数据
     *
     * @param request
     * @param response
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        String parentId = request.getParameter("parentId");
        if (StringUtil.isNotEmpty(parentId)) {
            queryFilter.addFilter("parent_id_", parentId, QueryOperation.EQUAL);
        }
        Page<GroupModelImpl> orgList = (Page<GroupModelImpl>) empGroupManager.query(queryFilter);
        return new PageJson(orgList);
    }


    @RequestMapping("isExist")
    public boolean isExist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oldCode = RequestUtil.getString(request, "oldCode");
        String code = RequestUtil.getString(request, "key");
        if (oldCode.equals(code) && StringUtil.isNotEmpty(code)) {
            return false;
        }
        if (StringUtil.isNotEmpty(code)) {
            GroupModelImpl temp = empGroupManager.getByCode(code);
            return temp != null;
        }
        return false;
    }

    /**
     * 组织架构明细页面
     *
     * @return
     * @throws Exception ModelAndView
     */
    @RequestMapping("get")
    @Override
    @CatchError
    public ResultMsg<GroupModelImpl> get(@RequestParam String id) throws Exception {
        GroupModelImpl group = empGroupManager.get(id);
        if (group != null && !group.getParentId().equals("0")) {
            String parentOrgName = empGroupManager.get(group.getParentId()).getName();
            group.setParentOrgName(parentOrgName);
        }

        return getSuccessResult(group);
    }

    @RequestMapping("getTreeData")
    public List<OrgTree> getTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<OrgTree> groupTreeList = getGroupTree();
        if (BeanUtils.isEmpty(groupTreeList)) {
            groupTreeList = new ArrayList<OrgTree>();
        }
        OrgTree rootGroup = new OrgTree();
        rootGroup.setName("行政组织");
        rootGroup.setId("0"); // 根节点
        groupTreeList.add(rootGroup);
        return groupTreeList;
    }

    private List<OrgTree> getGroupTree() {
        List<OrgTree> groupTreeList = new ArrayList<OrgTree>();
        List<GroupModelImpl> groupList = empGroupManager.getAll();
        for (GroupModelImpl group : groupList) {
            OrgTree groupTree = new OrgTree(group);
            groupTreeList.add(groupTree);
        }
        return groupTreeList;
    }

    @Override
    protected String getModelDesc() {
        return "组织";
    }
}
