package org.minxc.emp.bpm.plugin.usercalc.user.runtime;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SysIdentity;
import org.minxc.emp.bpm.core.manager.BpmTaskOpinionManager;
import org.minxc.emp.bpm.core.model.BpmTaskOpinion;
import org.minxc.emp.bpm.engine.model.BpmIdentity;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.usercalc.user.def.UserPluginDef;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.service.UserService;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Component;

@Component
public class UserPlugin extends AbstractUserCalcPlugin<UserPluginDef> {
	@Resource
	BpmTaskOpinionManager aa;
	@Resource
	UserService ak;

	public List<SysIdentity> queryByPluginDef(BpmUserCalcPluginSession pluginSession, UserPluginDef def) {
		List<SysIdentity> list = new ArrayList();
		String source = def.getSource();
		if ("start".equals(source)) {
			List<BpmTaskOpinion> opinions = this.aa.getByInstAndNode(pluginSession.getBpmTask().getInstId(), "start");
			BpmTaskOpinion firstNode = (BpmTaskOpinion) opinions.get(0);
			SysIdentity bpmIdentity = new BpmIdentity(firstNode.getApprover(), firstNode.getApproverName(), "user");
			list.add(bpmIdentity);
		}

		if ("currentUser".equals(source)) {
			SysIdentity bpmIdentity = new BpmIdentity(ContextUtil.getCurrentUser());
			list.add(bpmIdentity);
		} else if ("spec".equals(source)) {
			String userKeys = def.getAccount();
			String[] aryAccount = userKeys.split(",");
			String[] var16 = aryAccount;
			int var8 = aryAccount.length;

			for (int var9 = 0; var9 < var8; ++var9) {
				String account = var16[var9];
				User user = this.ak.getUserByAccount(account);
				SysIdentity bpmIdentity = new BpmIdentity(user);
				list.add(bpmIdentity);
			}
		}

		return list;
	}

//	public List queryByPluginDef(BpmUserCalcPluginSession bpmUserCalcPluginSession, BpmTaskPluginDef bpmTaskPluginDef) {
//		return this.a(bpmUserCalcPluginSession, (UserPluginDef) bpmTaskPluginDef);
//	}
}