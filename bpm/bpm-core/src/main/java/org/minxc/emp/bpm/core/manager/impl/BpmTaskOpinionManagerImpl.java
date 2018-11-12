package org.minxc.emp.bpm.core.manager.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;
import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.core.dao.BpmTaskOpinionDao;
import org.minxc.emp.bpm.core.manager.BpmTaskOpinionManager;
import org.minxc.emp.bpm.core.model.BpmTaskOpinion;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Service;

@Service(value = "bpmTaskOpinionManager")
public class BpmTaskOpinionManagerImpl extends CommonManager<String, BpmTaskOpinion> implements BpmTaskOpinionManager {
	@Resource
	BpmTaskOpinionDao k;

	public BpmTaskOpinion getByTaskId(String taskId) {
		return this.k.getByTaskId(taskId);
	}

	public void createOpinionByInstance(IBpmInstance bpmInstance, String formIdentity, boolean isCreateEvent) {
		BpmTaskOpinion bpmTaskOpinion = new BpmTaskOpinion();
		bpmTaskOpinion.setApproveTime(new Date());
		bpmTaskOpinion.setUpdateTime(new Date());
		bpmTaskOpinion.setDurMs(Long.valueOf(0L));
		bpmTaskOpinion.setInstId(bpmInstance.getId());
		bpmTaskOpinion.setSupInstId(bpmInstance.getParentInstId());
		bpmTaskOpinion.setOpinion(isCreateEvent ? "发起流程" : "流程结束");
		bpmTaskOpinion.setStatus(isCreateEvent ? "start" : "end");
		bpmTaskOpinion.setTaskId("0");
		bpmTaskOpinion.setTaskKey(isCreateEvent ? "start" : "end");
		bpmTaskOpinion.setTaskName(isCreateEvent ? "发起节点" : "终止节点");
		bpmTaskOpinion.setFormId(formIdentity);
		User user = ContextUtil.getCurrentUser();
		if (user != null) {
			bpmTaskOpinion.setApprover(user.getUserId());
			bpmTaskOpinion.setApproverName(user.getFullname());
			bpmTaskOpinion.setCreateBy(user.getUserId());
			bpmTaskOpinion.setUpdateBy(user.getUserId());
		}
		this.create(bpmTaskOpinion);
	}

	public void createOpinionByTask(TaskActionCmd taskActionModel) {
		IBpmTask task = taskActionModel.getBpmTask();
		IBpmInstance bpmInstance = taskActionModel.getBpmInstance();
		List<SystemIdentity> taskIdentitys = taskActionModel.getBpmIdentity(taskActionModel.getNodeId());
		BpmTaskOpinion bpmTaskOpinion = new BpmTaskOpinion();
		bpmTaskOpinion.setUpdateTime(new Date());
		bpmTaskOpinion.setDurMs(Long.valueOf(0L));
		bpmTaskOpinion.setInstId(bpmInstance.getId());
		bpmTaskOpinion.setSupInstId(bpmInstance.getParentInstId());
		bpmTaskOpinion.setOpinion(taskActionModel.getOpinion());
		bpmTaskOpinion.setStatus(taskActionModel.getActionName());
		bpmTaskOpinion.setTaskId(task.getId());
		bpmTaskOpinion.setTaskKey(task.getNodeId());
		bpmTaskOpinion.setTaskName(task.getName());
		bpmTaskOpinion.setFormId(taskActionModel.getFormId());
		User user = ContextUtil.getCurrentUser();
		if (user != null) {
			bpmTaskOpinion.setCreateBy(user.getUserId());
			bpmTaskOpinion.setUpdateBy(user.getUserId());
		}
		StringBuffer assignInfo = new StringBuffer();
		if (BeanUtils.isNotEmpty((Object) taskIdentitys)) {
			for (SystemIdentity identity : taskIdentitys) {
				assignInfo.append(identity.getType()).append("-").append(identity.getName()).append("-")
						.append(identity.getId()).append(",");
			}
		}
		bpmTaskOpinion.setAssignInfo(assignInfo.toString());
		this.create(bpmTaskOpinion);
	}

	public List<BpmTaskOpinion> getByInstAndNode(String instId, String nodeId) {
		return this.k.getByInstAndNode(instId, nodeId);
	}

	public List<BpmTaskOpinion> getByInstId(String instId) {
		return this.k.getByInstAndNode(instId, null);
	}
}