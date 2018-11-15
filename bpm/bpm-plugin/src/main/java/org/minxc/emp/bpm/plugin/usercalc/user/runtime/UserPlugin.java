package org.minxc.emp.bpm.plugin.usercalc.user.runtime;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.core.manager.BpmnTaskOpinionManager;
import org.minxc.emp.bpm.core.model.BpmnTaskOpinion;
import org.minxc.emp.bpm.engine.model.BpmnIdentity;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalculatePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.usercalc.user.def.UserPluginDefinition;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.service.UserService;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Component;

@Component
public class UserPlugin extends AbstractUserCalculatePlugin<UserPluginDefinition> {
	@Resource
    BpmnTaskOpinionManager aa;
	@Resource
	UserService ak;

	public List<SystemIdentity> queryByPluginDef(BpmnUserCalcPluginSession pluginSession, UserPluginDefinition def) {
		List<SystemIdentity> list = new ArrayList();
		String source = def.getSource();
		if ("start".equals(source)) {
			List<BpmnTaskOpinion> opinions = this.aa.getByInstAndNode(pluginSession.getBpmTask().getInstId(), "start");
			BpmnTaskOpinion firstNode = (BpmnTaskOpinion) opinions.get(0);
			SystemIdentity bpmIdentity = new BpmnIdentity(firstNode.getApprover(), firstNode.getApproverName(), "user");
			list.add(bpmIdentity);
		}

		if ("currentUser".equals(source)) {
			SystemIdentity bpmIdentity = new BpmnIdentity(ContextUtil.getCurrentUser());
			list.add(bpmIdentity);
		} else if ("spec".equals(source)) {
			String userKeys = def.getAccount();
			String[] aryAccount = userKeys.split(",");
			String[] var16 = aryAccount;
			int var8 = aryAccount.length;

			for (int var9 = 0; var9 < var8; ++var9) {
				String account = var16[var9];
				User user = this.ak.getUserByAccount(account);
				SystemIdentity bpmIdentity = new BpmnIdentity(user);
				list.add(bpmIdentity);
			}
		}

		return list;
	}

//	public List queryByPluginDef(BpmnUserCalcPluginSession bpmUserCalcPluginSession, BpmnTaskPluginDef bpmTaskPluginDef) {
//		return this.a(bpmUserCalcPluginSession, (UserPluginDefinition) bpmTaskPluginDef);
//	}
}