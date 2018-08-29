package org.minxc.emp.bpm.plugin.usercalc.script.runtime;

import com.minxc.emp.core.util.BeanUtils;
import com.dstz.base.core.util.StringUtil;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.sys.api.groovy.IGroovyScriptEngine;
import com.dstz.sys.api.model.SysIdentity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;

import org.minxc.emp.bpm.plugin.usercalc.script.def.ScriptPluginDef;
import org.springframework.stereotype.Component;

@Component
public class ScriptPlugin extends AbstractUserCalcPlugin<ScriptPluginDef> {
	@Resource
	IGroovyScriptEngine groovyScriptEngine;

	public List<SysIdentity> queryByPluginDef(BpmUserCalcPluginSession pluginSession, ScriptPluginDef def) {
		String script = def.getScript();
		if (StringUtil.isEmpty((String) script)) {
			return Collections.EMPTY_LIST;
		}
		Set set = (Set) this.groovyScriptEngine.executeObject(script, (Map) pluginSession);
		ArrayList<SysIdentity> list = new ArrayList<SysIdentity>();
		if (BeanUtils.isEmpty((Object) set)) {
			return list;
		}
		list.addAll(set);
		return list;
	}

	public boolean supportPreView() {
		return false;
	}

//	public List queryByPluginDef(BpmUserCalcPluginSession bpmUserCalcPluginSession, BpmTaskPluginDef bpmTaskPluginDef) {
//		return this.a(bpmUserCalcPluginSession, (ScriptPluginDef) bpmTaskPluginDef);
//	}
}