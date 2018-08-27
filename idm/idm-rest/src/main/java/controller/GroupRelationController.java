package org.minxc.emp.organization.rest.controller;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import com.github.pagehelper.Page;
import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.organization.core.manager.GroupRelationManager;
import org.minxc.emp.organization.core.model.GroupRelation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 组织关联关系 控制器类
 */
@RestController
@RequestMapping("/org/groupRelation")
public class GroupRelationController extends BaseController<GroupRelation> {
    @Resource
    GroupRelationManager groupRelManager;

    /**
     * 组织关联关系列表(分页条件查询)数据
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
        String groupId = RequestUtil.getString(request, "groupId");
        if (StringUtil.isNotEmpty(groupId)) {
            queryFilter.addParamsFilter("groupId", groupId);
        }
        Page<GroupRelation> orgRelList = (Page<GroupRelation>) groupRelManager.queryInfoList(queryFilter);
        return new PageJson(orgRelList);
    }


    /**
     * 保存组织关联关系信息
     */
    @RequestMapping("save")
    @CatchError
    @Override
    public ResultMsg<String> save(@RequestBody GroupRelation orgRel) throws Exception {

        if (StringUtil.isEmpty(orgRel.getId())) {
            GroupRelation relation = groupRelManager.getByCode(orgRel.getGroupCode());
            if (relation != null) {
                throw new BusinessException("岗位编码已经存在！");
            }
        }
       return super.save(orgRel);
    }


    @RequestMapping("isExist")
    public boolean isExist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        String code = RequestUtil.getString(request, "key");
        if (StringUtil.isNotEmpty(id))
            return false;
        if (StringUtil.isNotEmpty(code)) {
            GroupRelation temp = groupRelManager.getByCode(code);
            return temp != null;
        }
        return false;
    }


    @RequestMapping("getByGroupId")
    @CatchError
    public void getByGroupId(HttpServletRequest request, HttpServletResponse response, String groupId) throws Exception {
        List<GroupRelation> list = groupRelManager.getListByGroupId(groupId);
        writeSuccessData(response, list);
    }

    @Override
    protected String getModelDesc() {
        return "组关系";
    }
}
