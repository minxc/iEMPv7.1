package org.minxc.emp.bpm.plugin.execution.script.plugin;

import com.dstz.base.core.util.StringUtil;
import org.minxc.emp.bpm.api.constant.EventType;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
import com.dstz.sys.api.groovy.IGroovyScriptEngine;
import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.bpm.plugin.execution.script.def.NodeScriptPluginDef;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class NodeScriptPlugin extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, NodeScriptPluginDef> {
	@Resource
	private IGroovyScriptEngine groovyScriptEngine;

	public Void execute(BpmExecutionPluginSession pluginSession, NodeScriptPluginDef pluginDef) {
		String script = pluginDef.a(pluginSession.getEventType());
		if (StringUtil.isEmpty((String) script)) {
			return null;
		}
		this.groovyScriptEngine.execute(script, (Map) pluginSession);
		this.LOG.info("节点{}执行了{}事件脚本", (Object) pluginDef.getNodeId(),
				(Object) pluginSession.getEventType().getValue());
		return null;
	}

//	public Object execute(Object object, Object object2) {
//		return this.a((BpmExecutionPluginSession) object, (NodeScriptPluginDef) object2);
//	}
}