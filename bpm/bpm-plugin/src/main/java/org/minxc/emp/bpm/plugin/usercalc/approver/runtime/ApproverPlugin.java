package org.minxc.emp.bpm.plugin.usercalc.approver.runtime;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.core.manager.BpmnTaskOpinionManager;
import org.minxc.emp.bpm.core.model.BpmnTaskOpinion;
import org.minxc.emp.bpm.engine.model.BpmnIdentity;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalculatePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.usercalc.approver.def.ApproverPluginDefinition;
import org.minxc.emp.core.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class ApproverPlugin extends AbstractUserCalculatePlugin<ApproverPluginDefinition> {
	@Resource
	private BpmnTaskOpinionManager aa;

	public List<SystemIdentity> queryByPluginDef(BpmnUserCalcPluginSession pluginSession, ApproverPluginDefinition pluginDef) {
		ArrayList<SystemIdentity> bpmIdentities = new ArrayList<SystemIdentity>();
		List<BpmnTaskOpinion> taskOpinionList = this.aa.getByInstId(pluginSession.getBpmTask().getInstId());
		for (BpmnTaskOpinion taskOpinion : taskOpinionList) {
			if (StringUtil.isEmpty((String) taskOpinion.getApprover()))
				continue;
			BpmnIdentity bpmnIdentity = new BpmnIdentity(taskOpinion.getApprover(), taskOpinion.getApproverName(), "user");
			bpmIdentities.add((SystemIdentity) bpmnIdentity);
		}
		return bpmIdentities;
	}

//	public List queryByPluginDef(BpmnUserCalcPluginSession bpmUserCalcPluginSession, BpmnTaskPluginDef bpmTaskPluginDef) {
//		return this.a(bpmUserCalcPluginSession, (ApproverPluginDefinition) bpmTaskPluginDef);
//	}
}