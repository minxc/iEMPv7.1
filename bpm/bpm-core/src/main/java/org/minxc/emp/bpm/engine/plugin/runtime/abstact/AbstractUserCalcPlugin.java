package org.minxc.emp.bpm.engine.plugin.runtime.abstact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SysIdentity;
import org.minxc.emp.bpm.api.constant.ExtractType;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import org.minxc.emp.bpm.engine.model.BpmIdentity;
import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmUserCalcPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.service.UserService;

import com.minxc.emp.core.util.BeanUtils;

public abstract class AbstractUserCalcPlugin<M extends BpmTaskPluginDef> implements BpmUserCalcPlugin<M> {
	@Resource
	protected UserService bL;

	protected abstract List<SysIdentity> queryByPluginDef(BpmUserCalcPluginSession var1, M var2);

	public List<SysIdentity> execute(BpmUserCalcPluginSession pluginSession, M pluginDef) {
		if (pluginSession.isPreVrewModel().booleanValue() && !this.supportPreView()) {
			return Collections.emptyList();
		}
		List<SysIdentity> list = this.queryByPluginDef(pluginSession, pluginDef);
		if (BeanUtils.isEmpty(list)) {
			return list;
		}
		ExtractType extractType = ((AbstractUserCalcPluginDef) pluginDef).getExtract();
		LinkedHashSet<SysIdentity> set = new LinkedHashSet<SysIdentity>();
		ArrayList<SysIdentity> rtnList = new ArrayList<SysIdentity>();
		list = this.extract(list, extractType);
		set.addAll(list);
		rtnList.addAll(set);
		return rtnList;
	}

	protected List<SysIdentity> extract(List<SysIdentity> bpmIdentities, ExtractType extractType) {
		if (BeanUtils.isEmpty(bpmIdentities)) {
			return Collections.EMPTY_LIST;
		}
		if (extractType == ExtractType.EXACT_NOEXACT) {
			return bpmIdentities;
		}
		return this.extractBpmIdentity(bpmIdentities);
	}

	protected List<SysIdentity> extractBpmIdentity(List<SysIdentity> bpmIdentities) {
		ArrayList<SysIdentity> results = new ArrayList<SysIdentity>();
		for (SysIdentity bpmIdentity : bpmIdentities) {
			if ("user".equals(bpmIdentity.getType())) {
				results.add(bpmIdentity);
				continue;
			}
			List<User> users = this.bL.getUserListByGroup(bpmIdentity.getType(), bpmIdentity.getId());
			for (User user : users) {
				results.add((SysIdentity) new BpmIdentity(user));
			}
		}
		return results;
	}

	public boolean supportPreView() {
		return true;
	}
}