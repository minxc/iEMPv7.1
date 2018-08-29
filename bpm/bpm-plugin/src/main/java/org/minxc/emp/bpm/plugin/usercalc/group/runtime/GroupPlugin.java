package org.minxc.emp.bpm.plugin.usercalc.group.runtime;

import com.dstz.base.core.util.StringUtil;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import com.dstz.bpm.engine.model.BpmIdentity;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.org.api.model.IGroup;
import com.dstz.org.api.service.GroupService;
import com.dstz.sys.api.model.SysIdentity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.bpm.plugin.usercalc.group.def.GroupPluginDef;
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
			IGroup group;
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