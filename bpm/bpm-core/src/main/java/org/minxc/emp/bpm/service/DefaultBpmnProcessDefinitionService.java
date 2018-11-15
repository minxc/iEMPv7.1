package org.minxc.emp.bpm.service;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.RepositoryService;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;
import org.minxc.emp.bpm.api.model.def.BpmnDefinition;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.nodedef.impl.SubProcessNodeDefinition;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.core.manager.BpmnDefinitionManager;
import org.minxc.emp.bpm.core.model.BpmnDefinitionImpl;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.minxc.emp.bpm.engine.parser.BpmnDefinitionNodeHandler;
import org.minxc.emp.bpm.engine.parser.BpmnProcessDefinitionParser;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.exception.SerializationException;
import org.minxc.emp.core.cache.Cache;
import org.springframework.stereotype.Component;

@Component
public class DefaultBpmnProcessDefinitionService implements BpmnProcessDefinitionService {
	@Resource
	Cache<DefaultBpmnProcessDefinition> bP;
	@Resource
	private BpmnDefinitionManager c;
	@Resource
	private BpmnDefinitionNodeHandler bQ;
	@Resource
	RepositoryService repositoryService;

	public BpmProcessDef getBpmProcessDef(String processDefId) {
		return this.i(processDefId);
	}

	public List<BpmNodeDef> getNodeDefs(String processDefinitionId) {
		DefaultBpmnProcessDefinition processDef = this.i(processDefinitionId);
		return processDef.getBpmnNodeDefs();
	}

	public BpmNodeDef getBpmNodeDef(String processDefinitionId, String nodeId) {
		List<BpmNodeDef> list = this.getNodeDefs(processDefinitionId);
		ArrayList<SubProcessNodeDefinition> listSub = new ArrayList<SubProcessNodeDefinition>();
		for (BpmNodeDef nodeDef : list) {
			if (nodeDef.getNodeId().equals(nodeId)) {
				return nodeDef;
			}
			if (!(nodeDef instanceof SubProcessNodeDefinition))
				continue;
			listSub.add((SubProcessNodeDefinition) nodeDef);
		}
		if (listSub.size() > 0) {
			return this.a(listSub, nodeId);
		}
		return null;
	}

	private BpmNodeDef a(List<SubProcessNodeDefinition> subList, String nodeId) {
		for (SubProcessNodeDefinition nodeDef : subList) {
			List<BpmNodeDef> nodeList = nodeDef.getChildBpmProcessDef().getBpmnNodeDefs();
			ArrayList<SubProcessNodeDefinition> nestSub = new ArrayList<SubProcessNodeDefinition>();
			for (BpmNodeDef tmpDef : nodeList) {
				if (tmpDef.getNodeId().equals(nodeId)) {
					return tmpDef;
				}
				if (!(tmpDef instanceof SubProcessNodeDefinition))
					continue;
				nestSub.add((SubProcessNodeDefinition) tmpDef);
			}
			if (nestSub.size() <= 0)
				continue;
			return this.a(nestSub, nodeId);
		}
		return null;
	}

	public BpmNodeDef getStartEvent(String processDefId) {
		DefaultBpmnProcessDefinition processDef = this.i(processDefId);
		List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
		for (BpmNodeDef nodeDef : list) {
			if (!nodeDef.getType().equals((Object) NodeType.START))
				continue;
			return nodeDef;
		}
		return null;
	}

	public List<BpmNodeDef> getEndEvents(String processDefId) {
		ArrayList<BpmNodeDef> nodeList = new ArrayList<BpmNodeDef>();
		DefaultBpmnProcessDefinition processDef = this.i(processDefId);
		List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
		for (BpmNodeDef nodeDef : list) {
			if (!nodeDef.getType().equals((Object) NodeType.END))
				continue;
			nodeList.add(nodeDef);
		}
		return nodeList;
	}

	public void clean(String processDefId) {
		this.bP.delByKey("procdef_" + processDefId);
		BpmnContext.cleanTread();
	}

	public List<BpmNodeDef> getStartNodes(String processDefId) {
		BpmNodeDef nodeDef = this.getStartEvent(processDefId);
		return nodeDef.getOutcomeNodes();
	}

	public boolean isStartNode(String defId, String nodeId) {
		List<BpmNodeDef> nodes = this.getStartNodes(defId);
		for (BpmNodeDef node : nodes) {
			if (!node.getNodeId().equals(nodeId))
				continue;
			return true;
		}
		return false;
	}

	public boolean validNodeDefType(String defId, String nodeId, NodeType nodeDefType) {
		BpmNodeDef nodeDef = this.getBpmNodeDef(defId, nodeId);
		return nodeDef.getType().equals((Object) nodeDefType);
	}

	public boolean isContainCallActivity(String defId) {
		DefaultBpmnProcessDefinition processDef = this.i(defId);
		List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
		for (BpmNodeDef nodeDef : list) {
			if (!nodeDef.getType().equals((Object) NodeType.CALLACTIVITY))
				continue;
			return true;
		}
		return false;
	}

	private DefaultBpmnProcessDefinition i(String processDefinitionId) {
		DefaultBpmnProcessDefinition processDef = (DefaultBpmnProcessDefinition) BpmnContext.getProcessDef((String) processDefinitionId);
		if (processDef != null) {
			return processDef;
		}
		processDef = this.a(processDefinitionId, true);
		if (processDef == null) {
			return null;
		}
		BpmnContext.addProcessDef((String) processDefinitionId, (BpmProcessDef) processDef);
		return processDef;
	}

	private synchronized DefaultBpmnProcessDefinition a(String processDefinitionId, Boolean isCache) {
		DefaultBpmnProcessDefinition bpmProcessDef = null;
		if (isCache.booleanValue()) {
			try {
				bpmProcessDef = (DefaultBpmnProcessDefinition) this.bP.getByKey("procdef_" + processDefinitionId);
			} catch (SerializationException e) {
				this.bP.delByKey("procdef_" + processDefinitionId);
				bpmProcessDef = null;
			}
		}
		if (bpmProcessDef != null) {
			return bpmProcessDef;
		}
		BpmnDefinitionImpl bpmDef = (BpmnDefinitionImpl) this.c.get(processDefinitionId);
		bpmProcessDef = this.e(bpmDef);
		if (isCache.booleanValue()) {
			this.bP.add("procdef_" + processDefinitionId, bpmProcessDef);
		}
		return bpmProcessDef;
	}

	public DefaultBpmnProcessDefinition e(BpmnDefinitionImpl bpmDef) {
		if (bpmDef == null) {
			return null;
		}
		JSONObject bpmDefSetting = JSONObject.parseObject((String) bpmDef.getDefSetting());
		DefaultBpmnProcessDefinition bpmProcessDef = new DefaultBpmnProcessDefinition();
		bpmProcessDef.setProcessDefinitionId(bpmDef.getId());
		bpmProcessDef.setName(bpmDef.getName());
		bpmProcessDef.setDefKey(bpmDef.getKey());
		BpmnModel bpmnModel = this.repositoryService.getBpmnModel(bpmDef.getActDefId());
		Process process = (Process) bpmnModel.getProcesses().get(0);
		this.bQ.a(bpmProcessDef, process.getFlowElements());
		BpmnProcessDefinitionParser.a((DefaultBpmnProcessDefinition) bpmProcessDef, (JSONObject) bpmDefSetting);
		bpmProcessDef.setJson(bpmDefSetting);
		return bpmProcessDef;
	}

	public List<BpmNodeDef> getNodesByType(String processDefinitionId, NodeType nodeType) {
		DefaultBpmnProcessDefinition processDef = this.i(processDefinitionId);
		List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
		ArrayList<BpmNodeDef> rtnList = new ArrayList<BpmNodeDef>();
		for (BpmNodeDef nodeDef : list) {
			if (!nodeDef.getType().equals((Object) nodeType))
				continue;
			rtnList.add(nodeDef);
		}
		return rtnList;
	}

	public List<BpmNodeDef> getAllNodeDef(String processDefinitionId) {
		List<BpmNodeDef> bpmNodeDefs = this.getNodeDefs(processDefinitionId);
		ArrayList<BpmNodeDef> rtnList = new ArrayList<BpmNodeDef>();
		this.a(bpmNodeDefs, rtnList);
		return rtnList;
	}

	private void a(List<BpmNodeDef> bpmNodeDefs, List<BpmNodeDef> rtnList) {
		for (BpmNodeDef def : bpmNodeDefs) {
			SubProcessNodeDefinition subProcessNodeDef;
			BpmProcessDef processDef;
			rtnList.add(def);
			if (!NodeType.SUBPROCESS.equals((Object) def.getType())
					|| (processDef = (subProcessNodeDef = (SubProcessNodeDefinition) def).getChildBpmProcessDef()) == null)
				continue;
			List subBpmNodeDefs = processDef.getBpmnNodeDefs();
			this.a(subBpmNodeDefs, rtnList);
		}
	}

	public List<BpmNodeDef> getSignUserNode(String processDefinitionId) {
		List<BpmNodeDef> bpmNodeDefs = this.getAllNodeDef(processDefinitionId);
		ArrayList<BpmNodeDef> rtnList = new ArrayList<BpmNodeDef>();
		for (BpmNodeDef bnd : bpmNodeDefs) {
			if (!bnd.getType().equals((Object) NodeType.START) && !bnd.getType().equals((Object) NodeType.SIGNTASK)
					&& !bnd.getType().equals((Object) NodeType.USERTASK))
				continue;
			rtnList.add(bnd);
		}
		return rtnList;
	}

	public BpmnDefinition getDefinitionById(String defId) {
		return (BpmnDefinition) this.c.get(defId);
	}

	public BpmProcessDef initBpmProcessDef(BpmnDefinition bpmDef) {
		try {
			DefaultBpmnProcessDefinition def = this.e((BpmnDefinitionImpl) bpmDef);
			BpmnContext.cleanTread();
			this.bP.delByKey("procdef_" + bpmDef.getId());
			return def;
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), BpmnStatusCode.PARSER_FLOW_ERROR, (Throwable) e);
		}
	}

	public BpmnDefinition getDefinitionByActDefId(String actDefId) {
		return this.c.getDefinitionByActDefId(actDefId);
	}
}