package org.minxc.emp.bpm.plugin.usercalc.group.runtime;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.engine.model.BpmnIdentity;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalculatePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.usercalc.group.def.GroupPluginDefinition;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.service.GroupService;
import org.springframework.stereotype.Component;

@Component
public class GroupPlugin extends AbstractUserCalculatePlugin<GroupPluginDefinition> {
	@Resource
	GroupService ac;

	public List<SystemIdentity> queryByPluginDef(BpmnUserCalcPluginSession pluginSession, GroupPluginDefinition def) {
		if (StringUtil.isEmpty((String) def.getGroupKey())) {
			return null;
		}
		String groupType = def.getType();
		ArrayList<SystemIdentity> identityList = new ArrayList<SystemIdentity>();
		for (String key : def.getGroupKey().split(",")) {
			Group group;
			if (StringUtil.isEmpty((String) key) || (group = this.ac.getByCode(groupType, key)) == null)
				continue;
			identityList.add((SystemIdentity) new BpmnIdentity(group.getGroupId(), group.getName(), group.getGroupType()));
		}
		return identityList;
	}

//	public List queryByPluginDef(BpmnUserCalcPluginSession bpmUserCalcPluginSession, BpmnTaskPluginDef bpmTaskPluginDef) {
//		return this.a(bpmUserCalcPluginSession, (GroupPluginDefinition) bpmTaskPluginDef);
//	}
}