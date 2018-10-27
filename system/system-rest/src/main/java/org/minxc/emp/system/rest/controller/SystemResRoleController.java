package org.minxc.emp.system.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.system.impl.manager.ResRoleManager;
import org.minxc.emp.system.impl.manager.SubsystemManager;
import org.minxc.emp.system.impl.manager.SystemResourceManager;
import org.minxc.emp.system.impl.model.RoleResouceLinkEntity;
import org.minxc.emp.system.impl.model.SystemResourceEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色资源分配 控制器类
 */
@Controller
@RequestMapping("/sys/resRole")
public class SystemResRoleController extends GenericController {

	@Resource
	ResRoleManager resRoleManager;

	@Resource
	SystemResourceManager sysResourceManager;

	@Resource
	SubsystemManager subsystemManager;

	/**
	 * 角色资源分配列表(分页条件查询)数据
	 *
	 * @param request @param response @return @throws Exception PageJson @throws
	 */
	@RequestMapping("listJson")
	public @ResponseBody PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);
		Page<RoleResouceLinkEntity> resRoleList = (Page<RoleResouceLinkEntity>) resRoleManager.query(queryFilter);
		return new PageJson(resRoleList);
	}

	/**
	 * 角色资源分配明细页面
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("getJson")
	public @ResponseBody RoleResouceLinkEntity getJson(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = RequestUtil.getString(request, "id");
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		RoleResouceLinkEntity resRole = resRoleManager.get(id);
		return resRole;
	}

	/**
	 * 保存角色资源分配信息
	 *
	 * @param request @param response @param resRole @throws Exception void @throws
	 */
	@RequestMapping("save")
	@ErrorCatching("对角色资源分配操作失败")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleId = RequestUtil.getString(request, "roleId");
		String systemId = RequestUtil.getString(request, "systemId");
		String resIds = RequestUtil.getString(request, "resIds");

		resRoleManager.assignResByRoleSys(resIds, systemId, roleId);
		writeSuccessResult(response, " 添加角色资源分配成功");
	}

	/**
	 * 批量删除角色资源分配记录
	 *
	 * @param request @param response @throws Exception void @throws
	 */
	@RequestMapping("remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage message = null;
		try {
			String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
			resRoleManager.removeByIds(aryIds);
			message = new ResultMessage(ResultMessage.SUCCESS, "删除角色资源分配成功");
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.FAIL, "删除角色资源分配失败");
		}
		writeResultMessage(response.getWriter(), message);
	}

	@RequestMapping("getTreeData")
	@ResponseBody
	public List<SystemResourceEntity> getTreeData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String roleId = RequestUtil.getString(request, "roleId");
		String applicationId = RequestUtil.getString(request, "applicationId");
		List<SystemResourceEntity> roleResourceList = sysResourceManager.getBySystemAndRole(applicationId, roleId);
		List<SystemResourceEntity> resourceList = sysResourceManager.getBySystemId(applicationId);
		for (SystemResourceEntity sysResource : resourceList) {
			if (roleResourceList.contains(sysResource)) {
				sysResource.setChecked(true);
			}
		}
		if (BeanUtils.isEmpty(resourceList))
			resourceList = new ArrayList<SystemResourceEntity>();

		SystemResourceEntity rootRes = new SystemResourceEntity();
		String rootName = subsystemManager.get(applicationId).getName();
		rootRes.setName(rootName);
		rootRes.setId("0");
		rootRes.setApplicationId(applicationId); // 根节点
		resourceList.add(rootRes);
		return resourceList;
	}
}
