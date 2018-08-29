package org.minxc.emp.bpm.plugin.usercalc.user.runtime;

import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import com.dstz.bpm.engine.model.BpmIdentity;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.org.api.model.IUser;
import com.dstz.org.api.service.UserService;
import com.dstz.sys.api.model.SysIdentity;
import com.dstz.sys.util.ContextUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.bpm.plugin.usercalc.user.def.UserPluginDef;
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
				IUser user = this.ak.getUserByAccount(account);
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