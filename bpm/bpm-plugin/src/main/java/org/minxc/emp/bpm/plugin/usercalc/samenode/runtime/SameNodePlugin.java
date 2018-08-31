package org.minxc.emp.bpm.plugin.usercalc.samenode.runtime;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SysIdentity;
import org.minxc.emp.bpm.api.model.task.IBpmTaskOpinion;
import org.minxc.emp.bpm.core.manager.BpmTaskOpinionManager;
import org.minxc.emp.bpm.engine.model.BpmIdentity;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.usercalc.samenode.def.SameNodePluginDef;
import org.springframework.stereotype.Component;

import com.minxc.emp.core.util.BeanUtils;

@Component
public class SameNodePlugin extends AbstractUserCalcPlugin<SameNodePluginDef> {
	@Resource
	private BpmTaskOpinionManager aa;

	public List<SysIdentity> queryByPluginDef(BpmUserCalcPluginSession pluginSession, SameNodePluginDef sameNodeDef) {
		ArrayList<SysIdentity> bpmIdentities = new ArrayList<SysIdentity>();
		List taskOpinionList = this.aa.getByInstAndNode(pluginSession.getBpmTask().getInstId(),
				sameNodeDef.getNodeId());
		if (BeanUtils.isEmpty((Object) taskOpinionList)) {
			return bpmIdentities;
		}
		IBpmTaskOpinion taskOpinion = (IBpmTaskOpinion) taskOpinionList.get(taskOpinionList.size() - 1);
		BpmIdentity bpmIdentity = new BpmIdentity(taskOpinion.getApprover(), taskOpinion.getApproverName(), "user");
		bpmIdentities.add((SysIdentity) bpmIdentity);
		return bpmIdentities;
	}

	public boolean supportPreView() {
		return false;
	}

//	public List queryByPluginDef(BpmUserCalcPluginSession bpmUserCalcPluginSession, BpmTaskPluginDef bpmTaskPluginDef) {
//		return this.a(bpmUserCalcPluginSession, (SameNodePluginDef) bpmTaskPluginDef);
//	}
}