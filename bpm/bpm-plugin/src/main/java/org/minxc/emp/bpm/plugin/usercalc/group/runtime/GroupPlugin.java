package org.minxc.emp.bpm.plugin.usercalc.group.runtime;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SysIdentity;
import org.minxc.emp.bpm.engine.model.BpmIdentity;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.usercalc.group.def.GroupPluginDef;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.service.GroupService;
import org.springframework.stereotype.Component;

@Component
public class GroupPlugin extends AbstractUserCalcPlugin<GroupPluginDef> {
	@Resource
	GroupService ac;

	public List<SysIdentity> queryByPluginDef(BpmUserCalcPluginSession pluginSession, GroupPluginDef def) {
		if (StringUtil.isEmpty((String) def.getGroupKey())) {
			return null;
		}
		String groupType = def.getType();
		ArrayList<SysIdentity> identityList = new ArrayList<SysIdentity>();
		for (String key : def.getGroupKey().split(",")) {
			Group group;
			if (StringUtil.isEmpty((String) key) || (group = this.ac.getByCode(groupType, key)) == null)
				continue;
			identityList.add((SysIdentity) new BpmIdentity(group.getGroupId(), group.getName(), group.getGroupType()));
		}
		return identityList;
	}

//	public List queryByPluginDef(BpmUserCalcPluginSession bpmUserCalcPluginSession, BpmTaskPluginDef bpmTaskPluginDef) {
//		return this.a(bpmUserCalcPluginSession, (GroupPluginDef) bpmTaskPluginDef);
//	}
}