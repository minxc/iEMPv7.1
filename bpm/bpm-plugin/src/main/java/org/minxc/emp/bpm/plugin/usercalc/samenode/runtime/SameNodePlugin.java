package org.minxc.emp.bpm.plugin.usercalc.samenode.runtime;

import com.dstz.base.core.util.BeanUtils;
import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.api.model.task.IBpmTaskOpinion;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.engine.model.BpmIdentity;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.sys.api.model.SysIdentity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.bpm.plugin.usercalc.samenode.def.SameNodePluginDef;
import org.springframework.stereotype.Component;

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