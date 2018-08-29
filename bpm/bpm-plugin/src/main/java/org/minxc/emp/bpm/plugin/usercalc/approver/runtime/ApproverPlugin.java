package org.minxc.emp.bpm.plugin.usercalc.approver.runtime;

import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.api.model.task.IBpmTaskOpinion;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import com.dstz.bpm.engine.model.BpmIdentity;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.sys.api.model.SysIdentity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.bpm.plugin.usercalc.approver.def.ApproverPluginDef;
import org.springframework.stereotype.Component;

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