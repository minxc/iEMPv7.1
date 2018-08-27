package org.minxc.emp.idm.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.idm.impl.manager.GroupRelDefManager;
import org.minxc.emp.idm.impl.model.GroupRelDefEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <pre>
 * 描述：组织关系定义 控制器类
 * </pre>
 */
@RestController
@RequestMapping("/org/groupRelDef")
public class GroupRelDefController extends CommonController<GroupRelDefEntity> {
    @Resource
    GroupRelDefManager groupRelDefManager;


    @RequestMapping("getAllJson")
    public List<GroupRelDefEntity> getAllJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<GroupRelDefEntity> orgReldefList = groupRelDefManager.getAll();
        return orgReldefList;
    }

    /**
     * @return 
     * 保存组织关系定义信息
     *
     * @param request
     * @param response
     * @param orgReldef
     * @throws Exception void
     * @throws
     */
    @RequestMapping("save")
    @ErrorCatching
    @Override
    public ResultMessage<String> save(@RequestBody GroupRelDefEntity orgReldef) throws Exception {

        if (StringUtils.isEmpty(orgReldef.getId())) {
        	GroupRelDefEntity temp = groupRelDefManager.getByCode(orgReldef.getCode());
            if (temp != null) throw new BusinessException("code已存在，不可重复");
        }

      return  super.save( orgReldef);
    }


    @RequestMapping("isExist")
    public boolean isExist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        String code = RequestUtil.getString(request, "key");
        if (StringUtils.isNotEmpty(id))
            return false;
        if (StringUtils.isNotEmpty(code)) {
        	GroupRelDefEntity temp = groupRelDefManager.getByCode(code);
            return temp != null;
        }
        return false;
    }

    @Override
    protected String getModelDesc() {
        return "职务";
    }
}
