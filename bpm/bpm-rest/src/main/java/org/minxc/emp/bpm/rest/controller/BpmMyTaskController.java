package org.minxc.emp.bpm.rest.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.bpm.api.constant.InstanceStatus;
import org.minxc.emp.bpm.core.manager.BpmDefinitionManager;
import org.minxc.emp.bpm.core.manager.BpmInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmTaskManager;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.core.model.BpmTask;
import org.minxc.emp.bpm.core.model.BpmTaskApprove;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;


/**
 * 我的任务
 */
@RestController
@RequestMapping("/bpm/my")
public class BpmMyTaskController extends GenericController{
	@Resource
	BpmTaskManager bpmTaskManager;
	@Resource
	BpmInstanceManager bpmInstanceManager;
	@Resource 
	BpmDefinitionManager bpmDefinitionManager;
 
	/**
	 * 获取我待办事项
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("todoTaskList")
	public  PageJson todoTaskList(HttpServletRequest request,HttpServletResponse reponse) throws Exception{
		QueryFilter queryFilter=getQueryFilter(request);
		String userId = ContextUtil.getCurrentUserId();
		
		Page<BpmTask> bpmTaskList=(Page<BpmTask>)bpmTaskManager.getTodoList(userId,queryFilter);
		return new PageJson(bpmTaskList);
	}
	
	/**
	 * 获取我的申请
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("applyTaskList")
	public  PageJson applyTaskList(HttpServletRequest request,HttpServletResponse reponse) throws Exception{
		QueryFilter queryFilter=getQueryFilter(request);
		String userId = ContextUtil.getCurrentUserId();
		
		Page<BpmInstance> bpmTaskList=(Page<BpmInstance>)bpmInstanceManager.getApplyList(userId,queryFilter);
		return new PageJson(bpmTaskList);
	}
	
	/**
	 * 获取我的流程
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("definitionList")
	public  PageJson definitionList(HttpServletRequest request,HttpServletResponse reponse) throws Exception{
		QueryFilter queryFilter=getQueryFilter(request);
		String userId = ContextUtil.getCurrentUserId();
		
		List<BpmDefinition> list = bpmDefinitionManager.getMyDefinitionList(userId,queryFilter);
		return new PageJson(list);
	}
	
	/**
	 * 获取我审批过的
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("approveList")
	public PageJson approveList(HttpServletRequest request,HttpServletResponse reponse) throws Exception{
		QueryFilter queryFilter=getQueryFilter(request);
		String userId = ContextUtil.getCurrentUserId();
		
		List<BpmTaskApprove> bpmTaskList=bpmInstanceManager.getApproveHistoryList(userId,queryFilter);
		return new PageJson(bpmTaskList);
	}
	
	
	@RequestMapping("draftList")
	public PageJson draftList(HttpServletRequest request,HttpServletResponse reponse) throws Exception{
		QueryFilter queryFilter=getQueryFilter(request);
		String userId = ContextUtil.getCurrentUserId();
		queryFilter.addFilter("inst.status_", InstanceStatus.STATUS_DRAFT.getKey(), QueryOperator.EQUAL);
		List<BpmInstance> instanceList = bpmInstanceManager.getApplyList(userId, queryFilter);
		return new PageJson(instanceList);
	}
	
	
	 
}
