package org.minxc.emp.bpm.engine.plugin.runtime.abstact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.constant.ExtractType;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnTaskPluginDef;
import org.minxc.emp.bpm.engine.model.BpmnIdentity;
import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractUserCalculatePluginDefinition;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmnUserCalculatePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.service.UserService;

public abstract class AbstractUserCalculatePlugin<M extends BpmnTaskPluginDef> implements BpmnUserCalculatePlugin<M> {
	@Resource
	protected UserService bL;

	protected abstract List<SystemIdentity> queryByPluginDef(BpmnUserCalcPluginSession var1, M var2);

	public List<SystemIdentity> execute(BpmnUserCalcPluginSession pluginSession, M pluginDef) {
		if (pluginSession.isPreVrewModel().booleanValue() && !this.supportPreView()) {
			return Collections.emptyList();
		}
		List<SystemIdentity> list = this.queryByPluginDef(pluginSession, pluginDef);
		if (BeanUtils.isEmpty(list)) {
			return list;
		}
		ExtractType extractType = ((AbstractUserCalculatePluginDefinition) pluginDef).getExtract();
		LinkedHashSet<SystemIdentity> set = new LinkedHashSet<SystemIdentity>();
		ArrayList<SystemIdentity> rtnList = new ArrayList<SystemIdentity>();
		list = this.extract(list, extractType);
		set.addAll(list);
		rtnList.addAll(set);
		return rtnList;
	}

	protected List<SystemIdentity> extract(List<SystemIdentity> bpmIdentities, ExtractType extractType) {
		if (BeanUtils.isEmpty(bpmIdentities)) {
			return Collections.EMPTY_LIST;
		}
		if (extractType == ExtractType.EXACT_NOEXACT) {
			return bpmIdentities;
		}
		return this.extractBpmIdentity(bpmIdentities);
	}

	protected List<SystemIdentity> extractBpmIdentity(List<SystemIdentity> bpmIdentities) {
		ArrayList<SystemIdentity> results = new ArrayList<SystemIdentity>();
		for (SystemIdentity bpmIdentity : bpmIdentities) {
			if ("user".equals(bpmIdentity.getType())) {
				results.add(bpmIdentity);
				continue;
			}
			List<User> users = this.bL.getUserListByGroup(bpmIdentity.getType(), bpmIdentity.getId());
			for (User user : users) {
				results.add((SystemIdentity) new BpmnIdentity(user));
			}
		}
		return results;
	}

	public boolean supportPreView() {
		return true;
	}
}