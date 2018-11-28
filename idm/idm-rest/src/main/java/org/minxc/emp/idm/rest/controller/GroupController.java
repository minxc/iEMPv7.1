package org.minxc.emp.idm.rest.controller;

import com.github.pagehelper.Page;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.impl.manager.GroupManager;
import org.minxc.emp.idm.impl.manager.UserManager;
import org.minxc.emp.idm.impl.model.GroupEntity;
import org.minxc.emp.idm.impl.model.OrgTreeEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 组织管理
 */
@RestController
@RequestMapping("/rest/groupmgr")
public class GroupController extends CommonController<GroupEntity> {

	@Resource
	private GroupManager groupManager;
	@Resource
	private UserManager userManager;

	/**
	 * 组织架构列表(分页条件查询)数据
	 *
	 * @param request 
	 * @param response 
	 * @return 
	 * @throws Exception PageJson @throws
	 */
	@RequestMapping("/listJson")
	public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);
		String parentId = request.getParameter("parentId");
		if (StringUtils.isNotEmpty(parentId)) {
			queryFilter.addFilter("PARENT_ID", parentId, QueryOperator.EQUAL);
		}
		List<GroupEntity> groupList = groupManager.query(queryFilter);
		Page<GroupEntity> orgList = (Page<GroupEntity>) groupList;
		return new PageJson(orgList);
	}

	@RequestMapping("/isExist")
	public boolean isExist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oldCode = RequestUtil.getString(request, "oldCode");
		String code = RequestUtil.getString(request, "key");
		if (oldCode.equals(code) && StringUtils.isNotEmpty(code)) {
			return false;
		}
		if (StringUtils.isNotEmpty(code)) {
			Group temp = groupManager.getByCode(code);
			return temp != null;
		}
		return false;
	}

	/**
	 * 组织架构明细页面
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/get")
	@Override
	@ErrorCatching
	public ResultMessage<GroupEntity> get(@RequestParam String id) throws Exception {
		GroupEntity group = groupManager.get(id);
		if (group != null && !group.getParentId().equals("0")) {
			String parentOrgName = groupManager.get(group.getParentId()).getName();
			group.setParentOrgName(parentOrgName);
		}

		return getSuccessResult(group);
	}

	@RequestMapping("/getTreeData")
	public List<OrgTreeEntity> getTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<OrgTreeEntity> groupTreeList = getGroupTree();
		if (BeanUtils.isEmpty(groupTreeList)) {
			groupTreeList = new ArrayList<OrgTreeEntity>();
		}
		OrgTreeEntity rootGroup = new OrgTreeEntity();
		rootGroup.setName("行政组织");
		rootGroup.setId("0"); // 根节点
		groupTreeList.add(rootGroup);
		return groupTreeList;
	}

	private List<OrgTreeEntity> getGroupTree() {
		List<OrgTreeEntity> groupTreeList = new ArrayList<OrgTreeEntity>();
		List<GroupEntity> groupList = groupManager.getAll();
		for (GroupEntity group : groupList) {
			OrgTreeEntity groupTree = new OrgTreeEntity(group);
			groupTreeList.add(groupTree);
		}
		return groupTreeList;
	}

	@Override
	protected String getModelDesc() {
		return "组织";
	}
}
