package org.minxc.emp.bpm.plugin.usercalc.approver.runtime;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SysIdentity;
import org.minxc.emp.bpm.core.manager.BpmTaskOpinionManager;
import org.minxc.emp.bpm.core.model.BpmTaskOpinion;
import org.minxc.emp.bpm.engine.model.BpmIdentity;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.usercalc.approver.def.ApproverPluginDef;
import org.springframework.stereotype.Component;

import com.minxc.emp.core.util.StringUtil;

@Component
public class ApproverPlugin extends AbstractUserCalcPlugin<ApproverPluginDef> {
	@Resource
	private BpmTaskOpinionManager aa;

	public List<SysIdentity> queryByPluginDef(BpmUserCalcPluginSession pluginSession, ApproverPluginDef pluginDef) {
		ArrayList<SysIdentity> bpmIdentities = new ArrayList<SysIdentity>();
		List<BpmTaskOpinion> taskOpinionList = this.aa.getByInstId(pluginSession.getBpmTask().getInstId());
		for (BpmTaskOpinion taskOpinion : taskOpinionList) {
			if (StringUtil.isEmpty((String) taskOpinion.getApprover()))
				continue;
			BpmIdentity bpmIdentity = new BpmIdentity(taskOpinion.getApprover(), taskOpinion.getApproverName(), "user");
			bpmIdentities.add((SysIdentity) bpmIdentity);
		}
		return bpmIdentities;
	}

//	public List queryByPluginDef(BpmUserCalcPluginSession bpmUserCalcPluginSession, BpmTaskPluginDef bpmTaskPluginDef) {
//		return this.a(bpmUserCalcPluginSession, (ApproverPluginDef) bpmTaskPluginDef);
//	}
}