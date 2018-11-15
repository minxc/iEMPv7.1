package org.minxc.emp.bpm.core.manager.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.bpm.api.model.task.BpmTask;
import org.minxc.emp.bpm.core.dao.BpmnTaskOpinionDao;
import org.minxc.emp.bpm.core.manager.BpmnTaskOpinionManager;
import org.minxc.emp.bpm.core.model.BpmnTaskOpinion;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Service;

@Service(value = "bpmTaskOpinionManager")
public class BpmnTaskOpinionManagerImpl extends CommonManager<String, BpmnTaskOpinion> implements BpmnTaskOpinionManager {
	@Resource
    BpmnTaskOpinionDao k;

	public BpmnTaskOpinion getByTaskId(String taskId) {
		return this.k.getByTaskId(taskId);
	}

	public void createOpinionByInstance(BpmnInstance bpmInstance, String formIdentity, boolean isCreateEvent) {
		BpmnTaskOpinion bpmnTaskOpinion = new BpmnTaskOpinion();
		bpmnTaskOpinion.setApproveTime(new Date());
		bpmnTaskOpinion.setUpdateTime(new Date());
		bpmnTaskOpinion.setDurMs(Long.valueOf(0L));
		bpmnTaskOpinion.setInstId(bpmInstance.getId());
		bpmnTaskOpinion.setSupInstId(bpmInstance.getParentInstId());
		bpmnTaskOpinion.setOpinion(isCreateEvent ? "发起流程" : "流程结束");
		bpmnTaskOpinion.setStatus(isCreateEvent ? "start" : "end");
		bpmnTaskOpinion.setTaskId("0");
		bpmnTaskOpinion.setTaskKey(isCreateEvent ? "start" : "end");
		bpmnTaskOpinion.setTaskName(isCreateEvent ? "发起节点" : "终止节点");
		bpmnTaskOpinion.setFormId(formIdentity);
		User user = ContextUtil.getCurrentUser();
		if (user != null) {
			bpmnTaskOpinion.setApprover(user.getUserId());
			bpmnTaskOpinion.setApproverName(user.getFullname());
			bpmnTaskOpinion.setCreateBy(user.getUserId());
			bpmnTaskOpinion.setUpdateBy(user.getUserId());
		}
		this.create(bpmnTaskOpinion);
	}

	public void createOpinionByTask(TaskActionCmd taskActionModel) {
		BpmTask task = taskActionModel.getBpmTask();
		BpmnInstance bpmInstance = taskActionModel.getBpmInstance();
		List<SystemIdentity> taskIdentitys = taskActionModel.getBpmIdentity(taskActionModel.getNodeId());
		BpmnTaskOpinion bpmnTaskOpinion = new BpmnTaskOpinion();
		bpmnTaskOpinion.setUpdateTime(new Date());
		bpmnTaskOpinion.setDurMs(Long.valueOf(0L));
		bpmnTaskOpinion.setInstId(bpmInstance.getId());
		bpmnTaskOpinion.setSupInstId(bpmInstance.getParentInstId());
		bpmnTaskOpinion.setOpinion(taskActionModel.getOpinion());
		bpmnTaskOpinion.setStatus(taskActionModel.getActionName());
		bpmnTaskOpinion.setTaskId(task.getId());
		bpmnTaskOpinion.setTaskKey(task.getNodeId());
		bpmnTaskOpinion.setTaskName(task.getName());
		bpmnTaskOpinion.setFormId(taskActionModel.getFormId());
		User user = ContextUtil.getCurrentUser();
		if (user != null) {
			bpmnTaskOpinion.setCreateBy(user.getUserId());
			bpmnTaskOpinion.setUpdateBy(user.getUserId());
		}
		StringBuffer assignInfo = new StringBuffer();
		if (BeanUtils.isNotEmpty((Object) taskIdentitys)) {
			for (SystemIdentity identity : taskIdentitys) {
				assignInfo.append(identity.getType()).append("-").append(identity.getName()).append("-")
						.append(identity.getId()).append(",");
			}
		}
		bpmnTaskOpinion.setAssignInfo(assignInfo.toString());
		this.create(bpmnTaskOpinion);
	}

	public List<BpmnTaskOpinion> getByInstAndNode(String instId, String nodeId) {
		return this.k.getByInstAndNode(instId, nodeId);
	}

	public List<BpmnTaskOpinion> getByInstId(String instId) {
		return this.k.getByInstAndNode(instId, null);
	}
}