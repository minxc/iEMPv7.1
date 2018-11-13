package org.minxc.emp.bpm.plugin.execution.script.plugin;

import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.GroovyScriptEngine;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmExecutionPluginSession;
import org.minxc.emp.bpm.plugin.execution.script.def.NodeScriptPluginDef;
import org.minxc.emp.core.util.StringUtil;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NodeScriptPlugin extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, NodeScriptPluginDef> {
	@Resource
	private GroovyScriptEngine groovyScriptEngine;

	public Void execute(BpmExecutionPluginSession pluginSession, NodeScriptPluginDef pluginDef) {
		String script = pluginDef.a(pluginSession.getEventType());
		if (StringUtil.isEmpty((String) script)) {
			return null;
		}
		this.groovyScriptEngine.execute(script, (Map) pluginSession);
		this.log.info("节点{}执行了{}事件脚本", (Object) pluginDef.getNodeId(),
				(Object) pluginSession.getEventType().getValue());
		return null;
	}

//	public Object execute(Object object, Object object2) {
//		return this.a((BpmExecutionPluginSession) object, (NodeScriptPluginDef) object2);
//	}
}