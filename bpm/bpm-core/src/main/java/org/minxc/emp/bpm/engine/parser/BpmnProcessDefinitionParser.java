package org.minxc.emp.bpm.engine.parser;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmDef;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.minxc.emp.bpm.engine.parser.flow.AbsFlowParse;
import org.minxc.emp.bpm.engine.parser.node.AbsNodeParse;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.BeanUtils;

public class BpmnProcessDefinitionParser {
	private static List<AbsFlowParse> bi;
	private static List<AbsNodeParse> bj;

	public static void a(DefaultBpmnProcessDefinition bpmProcessDef, JSONObject bpmDefSetting) {
		JSONObject flowConf = bpmDefSetting.getJSONObject("flow");
		for (AbsFlowParse flowParser : BpmnProcessDefinitionParser.getFlowParsers()) {
			flowParser.parse((BpmDef) bpmProcessDef, flowConf);
		}
		JSONObject nodeMap = bpmDefSetting.getJSONObject("nodeMap");
		for (BpmNodeDef nodeDef : bpmProcessDef.getBpmnNodeDefs()) {
			JSONObject nodeConfig = nodeMap.getJSONObject(nodeDef.getNodeId());
			for (AbsNodeParse nodeParser : BpmnProcessDefinitionParser.getNodeParsers()) {
				if (!nodeParser.a(nodeDef))
					continue;
				nodeParser.parse((BpmDef) nodeDef, nodeConfig);
			}
		}
	}

	private static List<AbsFlowParse> getFlowParsers() {
		if (BeanUtils.isNotEmpty(bi)) {
			return bi;
		}
		Map map = AppContextUtil.getImplInstance(AbsFlowParse.class);
		bi = new ArrayList(map.values());
		return bi;
	}

	private static List<AbsNodeParse> getNodeParsers() {
		if (BeanUtils.isNotEmpty(bj)) {
			return bj;
		}
		Map map = AppContextUtil.getImplInstance(AbsNodeParse.class);
		bj = new ArrayList(map.values());
		return bj;
	}
}