package org.minxc.emp.bpm.core.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import org.minxc.emp.bpm.api.constant.TaskStatus;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.core.dao.BpmnTaskDao;
import org.minxc.emp.bpm.core.manager.BpmnInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmnTaskManager;
import org.minxc.emp.bpm.core.manager.TaskIdentityLinkManager;
import org.minxc.emp.bpm.core.model.BpmnTaskImpl;
import org.minxc.emp.bpm.core.model.TaskIdentityLink;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Service;

@Service(value = "bpmTaskManager")
public class BpmnTaskManagerImpl extends CommonManager<String, BpmnTaskImpl> implements BpmnTaskManager {
	@Resource
    BpmnTaskDao h;
	@Resource
	TaskIdentityLinkManager i;
	@Resource
    BpmnInstanceManager j;

	public List<BpmnTaskImpl> getByInstIdNodeId(String instId, String nodeId) {
		return this.h.getByInstIdNodeId(instId, nodeId);
	}

	public List<BpmnTaskImpl> getByInstId(String instId) {
		return this.h.getByInstIdNodeId(instId, null);
	}

	public List<BpmnTaskImpl> getTodoList(String userId, QueryFilter queryFilter) {
		Set userRights = this.i.getUserRights(userId);
		queryFilter.addParamsFilter("userRights", (Object) userRights);
		queryFilter.addParamsFilter("userId", (Object) ContextUtil.getCurrentUserId());
		List taskList = this.h.getTodoList(queryFilter);
		return taskList;
	}

	public List getTodoList(QueryFilter queryFilter) {
		String userId = ContextUtil.getCurrentUserId();
		String type = (String) queryFilter.getParams().get("type");
		String title = (String) queryFilter.getParams().get("subject");
		if (StringUtil.isNotEmpty((String) title)) {
			queryFilter.addFilter("subject_", title, QueryOperator.LIKE);
		}
		if ("done".equals(type)) {
			return this.j.getApproveHistoryList(userId, queryFilter);
		}
		Set userRights = this.i.getUserRights(userId);
		queryFilter.addParamsFilter("userRights", (Object) userRights);
		return this.h.getTodoList(queryFilter);
	}

	public void assigneeTask(String taskId, String userId, String userName) {
		BpmnTaskImpl task = (BpmnTaskImpl) this.get(taskId);
		if (task == null) {
			throw new BusinessException("任务可能已经被处理，请刷新。",  BpmnStatusCode.TASK_NOT_FOUND);
		}
		task.setAssigneeId(userId);
		task.setAssigneeNames(userName);
		task.setStatus(TaskStatus.DESIGNATE.getKey());
		this.update(task);
	}

	public void unLockTask(String taskId) {
		BpmnTaskImpl task = (BpmnTaskImpl) this.get(taskId);
		
		List<TaskIdentityLink> identitys = this.i.getByTaskId(task.getId());
		if (BeanUtils.isEmpty((Object) identitys)) {
			throw new BusinessException("该任务并非多候选人状态，无效的操作！");
		}
		ArrayList<String> names = new ArrayList<String>();
		for (TaskIdentityLink identity : identitys) {
			names.add(identity.getIdentityName());
		}
		task.setAssigneeId("0");
		task.setAssigneeNames(StringUtil.convertCollectionAsString(names));
		task.setStatus(TaskStatus.NORMAL.getKey());
		this.update(task);
	}
}