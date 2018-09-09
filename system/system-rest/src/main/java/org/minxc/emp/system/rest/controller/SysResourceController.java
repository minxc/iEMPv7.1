package org.minxc.emp.system.rest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.system.impl.manager.RelResourceManager;
import org.minxc.emp.system.impl.manager.SubsystemManager;
import org.minxc.emp.system.impl.manager.SysResourceManager;
import org.minxc.emp.system.impl.model.ApplicationEntity;
import org.minxc.emp.system.impl.model.SystemResourceEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.minxc.emp.core.util.BeanUtils;

/**
 * 子系统资源 控制器类
 */
@RestController
@RequestMapping("/sys/sysResource")
public class SysResourceController extends GenericController {
    @Resource
    SysResourceManager sysResourceManager;

    @Resource
    RelResourceManager relResourceManager;

    @Resource
    SubsystemManager subsystemManager;

    /**
     * 子系统资源列表(分页条件查询)数据
     *
     * @param request
     * @param response
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @RequestMapping("listJson")
    public  PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<SystemResourceEntity> sysResourceList = (Page<SystemResourceEntity>) sysResourceManager.query(queryFilter);
        return new PageJson(sysResourceList);
    }


    /**
     * 子系统资源明细页面
     *
     * @param request
     * @param response
     * @return
     * @throws Exception ModelAndView
     */
    @RequestMapping("getJson")
    public  void getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        if (StringUtils.isEmpty(id)) {
            String parentId = RequestUtil.getString(request, "parentId");
            String applicationId = RequestUtil.getString(request, "applicationId");
            SystemResourceEntity sysResource = new SystemResourceEntity();
            sysResource.setApplicationId(applicationId);
            sysResource.setParentId(parentId);
            sysResource.setNewWindow(0);
            sysResource.setHasChildren(1);
            sysResource.setOpened(1);
            sysResource.setEnableMenu(1);
            writeSuccessData(response, sysResource);
        } else {
            SystemResourceEntity sysResource = sysResourceManager.getByResId(id);
            writeSuccessData(response, sysResource);
        }
    }

    /**
     * 保存子系统资源信息
     *
     * @param request
     * @param response
     * @param sysResource
     * @throws Exception void
     * @throws
     */
    @RequestMapping("save")
    @ErrorCatching
    public ResultMessage<String> save(@RequestBody SystemResourceEntity sysResource) throws Exception {
        String ResultMessage = null;
        String id = sysResource.getId();
        boolean isExist = sysResourceManager.isExist(sysResource);
        if (isExist) {
           throw new BusinessException("资源已经存在,请修改重新添加!");
        }
        
        if (StringUtils.isEmpty(id)) {
            sysResource.setSeq(System.currentTimeMillis());
            sysResourceManager.create(sysResource);
            ResultMessage = "添加子系统资源成功";
        } else {
            sysResourceManager.update(sysResource);
            ResultMessage = "更新子系统资源成功";
        }
        
        return getSuccessResult(sysResource.getId(), ResultMessage);
        
    }

    /**
     * 批量删除子系统资源记录
     *
     * @param request
     * @param response
     * @throws Exception void
     * @throws
     */
    @RequestMapping("remove")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultMessage message = null;
        try {
            String id = RequestUtil.getString(request, "id");
            sysResourceManager.removeByResId(id);
            message = new ResultMessage(ResultMessage.SUCCESS, "删除子系统资源成功");
        } catch (Exception e) {
            message = new ResultMessage(ResultMessage.FAIL, "删除子系统资源失败");
        }
        writeResultMessage(response.getWriter(), message);
    }
	
	/*@RequestMapping("sysResourceEdit")
	public ModelAndView sysResourceEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String parentId = request.getParameter("parentId");
		String id = request.getParameter("id");
		String parentsysResourceName = "子系统资源管理";
		if (id == null && parentId != null && !parentId.equals("0")) {
			// 新增时
			parentsysResourceName = sysResourceManager.get(parentId).getName();
		}
		return getAutoView().addObject("id", id)
				.addObject("parentId", parentId).addObject("parentsysResourceName", parentsysResourceName);
	}
	*/


    @RequestMapping("sysResourceGet")
    @ErrorCatching(value = "获取资源失败", writeErrorToResponse = true)
    public void sysResourceGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        SystemResourceEntity sysResource = sysResourceManager.get(id);
        writeSuccessData(response, sysResource);
    }
	
/*	
	@RequestMapping("sysResourceList")
	public ModelAndView sysResourceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Subsystem> subsystemList=subsystemManager.getAll();
		return getAutoView().addObject("subsystemList",subsystemList);
	}*/


    @RequestMapping("getTreeData")
    @ErrorCatching
    public List<SystemResourceEntity> getTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String applicationId = RequestUtil.getString(request, "applicationId");
        ApplicationEntity subsystem = subsystemManager.get(applicationId);
        List<SystemResourceEntity> groupList = getGroupTree(applicationId);
        if (BeanUtils.isEmpty(groupList))
            groupList = new ArrayList<SystemResourceEntity>();
        SystemResourceEntity rootResource = new SystemResourceEntity();
        rootResource.setName(subsystem.getName());
        rootResource.setId("0");
        rootResource.setApplicationId(applicationId); // 根节点
        rootResource.setHasChildren(1);
        groupList.add(rootResource);
        return groupList;
    }

    private List<SystemResourceEntity> getGroupTree(String systemId) {
        List<SystemResourceEntity> groupList = sysResourceManager.getBySystemId(systemId);
        return groupList;
    }

    @RequestMapping("moveResource")
    public void moveResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultMessage message = null;
        try {
            String id = RequestUtil.getString(request, "id");
            SystemResourceEntity sysResource = sysResourceManager.get(id);
            String parentId = RequestUtil.getString(request, "parentId");

            SystemResourceEntity parentResource = sysResourceManager.get(parentId);
            if (parentResource != null) {
                parentResource.setHasChildren(1);
                sysResourceManager.update(parentResource);
            }
            sysResource.setParentId(parentId);
            sysResourceManager.update(sysResource);
            message = new ResultMessage(ResultMessage.SUCCESS, "移动资源成功");
        } catch (Exception e) {
            message = new ResultMessage(ResultMessage.FAIL, "移动资源失败");
        }
        writeResultMessage(response.getWriter(), message);
    }

}
