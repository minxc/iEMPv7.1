package org.minxc.emp.bpm.core.manager.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.core.dao.TaskIdentityLinkDao;
import org.minxc.emp.bpm.core.manager.TaskIdentityLinkManager;
import org.minxc.emp.bpm.core.model.TaskIdentityLink;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.service.GroupService;
import org.springframework.stereotype.Service;

@Service(value = "taskIdentityLinkManager")
public class TaskIdentityLinkManagerImpl extends CommonManager<String, TaskIdentityLink>
		implements
			TaskIdentityLinkManager {
	@Resource
	TaskIdentityLinkDao m;
	@Resource
	GroupService n;

	public void removeByTaskId(String taskId) {
		this.m.removeByTaskId(taskId);
	}

	public void removeByInstanceId(String instId) {
		this.m.removeByInstanceId(instId);
	}

	public void bulkCreate(List<TaskIdentityLink> list) {
		this.m.bulkCreate(list);
	}

	public Boolean checkUserOperatorPermission(String userId, String instanceId, String taskId) {
		if (StringUtil.isEmpty((String) instanceId) && StringUtil.isEmpty((String) taskId)) {
			throw new BusinessException("检查权限必须提供流程或者任务id",  BpmStatusCode.PARAM_ILLEGAL);
		}
		Set<String> rights = this.getUserRights(userId);
		return this.m.checkUserOperatorPermission(rights, taskId, instanceId) > 0;
	}

	public Set<String> getUserRights(String userId) {
		List<Group> list = this.n.getGroupsByUserId(userId);
		HashSet<String> rights = new HashSet<String>();
		rights.add(String.format("%s-%s", userId, "user"));
		if (BeanUtils.isEmpty((Object) list)) {
			return rights;
		}
		for (Group group : list) {
			rights.add(String.format("%s-%s", group.getGroupId(), group.getGroupType()));
		}
		return rights;
	}

	public void createIdentityLink(IBpmTask bpmTask, List<SystemIdentity> identitys) {
		ArrayList<TaskIdentityLink> identityLinks = new ArrayList<TaskIdentityLink>();
		for (SystemIdentity identity : identitys) {
			TaskIdentityLink link = new TaskIdentityLink();
			link.setId(UniqueIdUtil.getSuid());
			link.setIdentity(identity.getId());
			link.setIdentityName(identity.getName());
			link.setType(identity.getType());
			link.setPermissionCode(identity.getId() + "-" + identity.getType());
			link.setTaskId(bpmTask.getId());
			link.setInstId(bpmTask.getInstId());
			identityLinks.add(link);
		}
		this.bulkCreate(identityLinks);
	}

	public List<TaskIdentityLink> getByTaskId(String taskId) {
		return this.m.getByTaskId(taskId);
	}
}