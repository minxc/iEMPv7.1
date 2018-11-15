package org.minxc.emp.bpm.plugin.usercalc.samenode.runtime;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.model.task.IBpmTaskOpinion;
import org.minxc.emp.bpm.core.manager.BpmnTaskOpinionManager;
import org.minxc.emp.bpm.engine.model.BpmnIdentity;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalculatePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.usercalc.samenode.def.SameNodePluginDefinition;
import org.minxc.emp.core.util.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SameNodePlugin extends AbstractUserCalculatePlugin<SameNodePluginDefinition> {
	@Resource
	private BpmnTaskOpinionManager aa;

	public List<SystemIdentity> queryByPluginDef(BpmnUserCalcPluginSession pluginSession, SameNodePluginDefinition sameNodeDef) {
		ArrayList<SystemIdentity> bpmIdentities = new ArrayList<SystemIdentity>();
		List taskOpinionList = this.aa.getByInstAndNode(pluginSession.getBpmTask().getInstId(),
				sameNodeDef.getNodeId());
		if (BeanUtils.isEmpty((Object) taskOpinionList)) {
			return bpmIdentities;
		}
		IBpmTaskOpinion taskOpinion = (IBpmTaskOpinion) taskOpinionList.get(taskOpinionList.size() - 1);
		BpmnIdentity bpmnIdentity = new BpmnIdentity(taskOpinion.getApprover(), taskOpinion.getApproverName(), "user");
		bpmIdentities.add((SystemIdentity) bpmnIdentity);
		return bpmIdentities;
	}

	public boolean supportPreView() {
		return false;
	}

//	public List queryByPluginDef(BpmnUserCalcPluginSession bpmUserCalcPluginSession, BpmnTaskPluginDef bpmTaskPluginDef) {
//		return this.a(bpmUserCalcPluginSession, (SameNodePluginDefinition) bpmTaskPluginDef);
//	}
}