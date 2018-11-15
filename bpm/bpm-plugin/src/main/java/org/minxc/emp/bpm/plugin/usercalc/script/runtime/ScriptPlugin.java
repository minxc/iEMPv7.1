package org.minxc.emp.bpm.plugin.usercalc.script.runtime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.GroovyScriptEngine;
import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalculatePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.usercalc.script.def.ScriptPluginDefinition;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class ScriptPlugin extends AbstractUserCalculatePlugin<ScriptPluginDefinition> {
	@Resource
	GroovyScriptEngine groovyScriptEngine;

	public List<SystemIdentity> queryByPluginDef(BpmnUserCalcPluginSession pluginSession, ScriptPluginDefinition def) {
		String script = def.getScript();
		if (StringUtil.isEmpty((String) script)) {
			return Collections.EMPTY_LIST;
		}
		Set set = (Set) this.groovyScriptEngine.executeObject(script, (Map) pluginSession);
		ArrayList<SystemIdentity> list = new ArrayList<SystemIdentity>();
		if (BeanUtils.isEmpty((Object) set)) {
			return list;
		}
		list.addAll(set);
		return list;
	}

	public boolean supportPreView() {
		return false;
	}

//	public List queryByPluginDef(BpmnUserCalcPluginSession bpmUserCalcPluginSession, BpmnTaskPluginDef bpmTaskPluginDef) {
//		return this.a(bpmUserCalcPluginSession, (ScriptPluginDefinition) bpmTaskPluginDef);
//	}
}