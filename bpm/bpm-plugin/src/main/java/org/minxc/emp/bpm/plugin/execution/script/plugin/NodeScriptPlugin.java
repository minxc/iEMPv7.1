package org.minxc.emp.bpm.plugin.execution.script.plugin;

import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.GroovyScriptEngine;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractBpmnExecutionPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnExecutionPluginSession;
import org.minxc.emp.bpm.plugin.execution.script.def.NodeScriptPluginDefinition;
import org.minxc.emp.core.util.StringUtil;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NodeScriptPlugin extends AbstractBpmnExecutionPlugin<BpmnExecutionPluginSession, NodeScriptPluginDefinition> {
	@Resource
	private GroovyScriptEngine groovyScriptEngine;

	public Void execute(BpmnExecutionPluginSession pluginSession, NodeScriptPluginDefinition pluginDef) {
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
//		return this.a((BpmnExecutionPluginSession) object, (NodeScriptPluginDefinition) object2);
//	}
}