package org.minxc.emp.bpm.engine.action.handler.task;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.exception.WorkFlowException;
import org.minxc.emp.bpm.api.model.def.NodeProperties;
import org.minxc.emp.bpm.api.model.task.BpmTask;
import org.minxc.emp.bpm.core.manager.BpmnTaskOpinionManager;
import org.minxc.emp.bpm.core.manager.BpmnTaskStackManager;
import org.minxc.emp.bpm.core.model.BpmnTaskImpl;
import org.minxc.emp.bpm.core.model.BpmnTaskOpinion;
import org.minxc.emp.bpm.core.model.BpmnTaskStack;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.bpm.engine.model.BpmnIdentity;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskRejectActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd> {
	private static Logger log = LoggerFactory.getLogger(TaskRejectActionHandler.class);
	@Resource
	BpmnTaskStackManager aA;
	@Resource
    BpmnTaskOpinionManager aB;

	public void e(DefualtTaskActionCmd actionModel) {
		BpmTask task;
		NodeProperties nodeProperties = this.a.getBpmNodeDef(actionModel.getDefId(), actionModel.getNodeId())
				.getNodeProperties();
		String destinationNode = this.a(actionModel, nodeProperties);
		if ("history".equals(nodeProperties.getBackUserMode())) {
			this.a(actionModel, destinationNode);
		}
		if ((task = actionModel.getBpmTask()).getNodeId().equals(destinationNode)) {
			throw new BusinessException("目标节点不能为当前任务节点", BpmnStatusCode.CANNOT_BACK_NODE);
		}
		actionModel.setDestination(destinationNode);
		log.info("任务【{}-{}】将驳回至节点{}", new Object[]{task.getName(), task.getId(), destinationNode});
	}

	private void a(DefualtTaskActionCmd actionModel, String destinationNode) {
		BpmnIdentity identitys = null;
		List<BpmnTaskOpinion> taskOpinions = this.aB.getByInstAndNode(actionModel.getInstanceId(), actionModel.getBpmTask().getNodeId());
		for (BpmnTaskOpinion opinion : taskOpinions) {
			if (!StringUtil.isNotEmpty((String) opinion.getApprover()))
				continue;
			identitys = new BpmnIdentity(opinion.getApprover(), opinion.getApproverName(), "user");
		}
		if (identitys != null) {
			ArrayList<SystemIdentity> list = new ArrayList<SystemIdentity>();
			list.add(identitys);
			actionModel.setBpmIdentity(destinationNode, list);
		}
	}

	protected String a(DefualtTaskActionCmd actionModel, NodeProperties nodeProperties) {
		if (StringUtil.isNotEmpty((String) actionModel.getDestination())) {
			return actionModel.getDestination();
		}
		if (nodeProperties != null && StringUtil.isNotEmpty((String) nodeProperties.getBackNode())) {
			return nodeProperties.getBackNode();
		}
		BpmnTaskStack stack = this.aA.getByTaskId(actionModel.getTaskId());
		if (StringUtil.isZeroEmpty((String) stack.getParentId())) {
			throw new WorkFlowException(BpmnStatusCode.NO_BACK_TARGET);
		}
		BpmnTaskStack preStack = (BpmnTaskStack) this.aA.get(stack.getParentId());
		if (preStack == null) {
			throw new WorkFlowException("上一步任务执行堆栈信息查找失败！", BpmnStatusCode.NO_BACK_TARGET);
		}
		return preStack.getNodeId();
	}

	public void d(DefualtTaskActionCmd actionModel) {
		NodeProperties nodeProperties = this.a.getBpmNodeDef(actionModel.getDefId(), actionModel.getNodeId())
				.getNodeProperties();
		if ("back".equals(nodeProperties.getBackMode())) {
			List<BpmnTaskImpl> tasks = this.ay.getByInstIdNodeId(actionModel.getInstanceId(), actionModel.getNodeId());
			if (BeanUtils.isEmpty((Object) tasks)) {
				throw new WorkFlowException("任务返回节点标记失败，待标记任务查找不到", BpmnStatusCode.NO_BACK_TARGET);
			}
			boolean hasUpdated = false;
			for (BpmnTaskImpl task : tasks) {
				if (!StringUtil.isNotEmpty((String) task.getActInstId())
						|| !StringUtil.isNotEmpty((String) task.getTaskId()))
					continue;
				if (hasUpdated) {
					throw new WorkFlowException("任务返回节点标记失败，期望查找一条，但是出现多条", BpmnStatusCode.NO_BACK_TARGET);
				}
				task.setBackNode(actionModel.getNodeId());
				this.ay.update(task);
				hasUpdated = true;
			}
			if (!hasUpdated) {
				throw new WorkFlowException("任务返回节点标记失败，待标记任务查找不到", BpmnStatusCode.NO_BACK_TARGET);
			}
		}
	}

	public ActionType getActionType() {
		return ActionType.REJECT;
	}

	public int getSn() {
		return 3;
	}

	public String getConfigPage() {
		return "/bpm/task/taskOpinionDialog.html";
	}
	@Override
	public void i(DefualtTaskActionCmd baseActionCmd) {
		this.d((DefualtTaskActionCmd) baseActionCmd);
	}
	@Override
	public void h(DefualtTaskActionCmd baseActionCmd) {
		this.e((DefualtTaskActionCmd) baseActionCmd);
	}

}