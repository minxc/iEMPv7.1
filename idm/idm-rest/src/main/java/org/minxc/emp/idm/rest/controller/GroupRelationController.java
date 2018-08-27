package org.minxc.emp.idm.rest.controller;

import com.github.pagehelper.Page;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.idm.impl.manager.GroupRelationManager;
import org.minxc.emp.idm.impl.model.GroupRelationEntity;
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
public class GroupRelationController extends CommonController<GroupRelationEntity> {
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
        if (StringUtils.isNotEmpty(groupId)) {
            queryFilter.addParamsFilter("groupId", groupId);
        }
        Page<GroupRelationEntity> orgRelList = (Page<GroupRelationEntity>) groupRelManager.queryInfoList(queryFilter);
        return new PageJson(orgRelList);
    }


    /**
     * 保存组织关联关系信息
     */
    @RequestMapping("save")
    @ErrorCatching
    @Override
    public ResultMessage<String> save(@RequestBody GroupRelationEntity orgRel) throws Exception {

        if (StringUtils.isEmpty(orgRel.getId())) {
        	GroupRelationEntity relation = groupRelManager.getByCode(orgRel.getGroupCode());
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
        if (StringUtils.isNotEmpty(id))
            return false;
        if (StringUtils.isNotEmpty(code)) {
        	GroupRelationEntity temp = groupRelManager.getByCode(code);
            return temp != null;
        }
        return false;
    }


    @RequestMapping("getByGroupId")
    @ErrorCatching
    public void getByGroupId(HttpServletRequest request, HttpServletResponse response, String groupId) throws Exception {
        List<GroupRelationEntity> list = groupRelManager.getListByGroupId(groupId);
        writeSuccessData(response, list);
    }

    @Override
    protected String getModelDesc() {
        return "组关系";
    }
}
