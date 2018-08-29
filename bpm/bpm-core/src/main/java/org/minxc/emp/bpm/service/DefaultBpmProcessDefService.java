package org.minxc.emp.bpm.service;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.constant.IStatusCode;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.exception.SerializeException;
import com.dstz.base.core.cache.ICache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.RepositoryService;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;
import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.nodedef.impl.SubProcessNodeDef;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.core.manager.BpmDefinitionManager;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.minxc.emp.bpm.engine.parser.BpmDefNodeHandler;
import org.minxc.emp.bpm.engine.parser.BpmProcessDefParser;
import org.springframework.stereotype.Component;

@Component
public class DefaultBpmProcessDefService implements BpmProcessDefService {
	@Resource
	ICache<DefaultBpmProcessDef> bP;
	@Resource
	private BpmDefinitionManager c;
	@Resource
	private BpmDefNodeHandler bQ;
	@Resource
	RepositoryService repositoryService;

	public BpmProcessDef getBpmProcessDef(String processDefId) {
		return this.i(processDefId);
	}

	public List<BpmNodeDef> getNodeDefs(String processDefinitionId) {
		DefaultBpmProcessDef processDef = this.i(processDefinitionId);
		return processDef.getBpmnNodeDefs();
	}

	public BpmNodeDef getBpmNodeDef(String processDefinitionId, String nodeId) {
		List<BpmNodeDef> list = this.getNodeDefs(processDefinitionId);
		ArrayList<SubProcessNodeDef> listSub = new ArrayList<SubProcessNodeDef>();
		for (BpmNodeDef nodeDef : list) {
			if (nodeDef.getNodeId().equals(nodeId)) {
				return nodeDef;
			}
			if (!(nodeDef instanceof SubProcessNodeDef))
				continue;
			listSub.add((SubProcessNodeDef) nodeDef);
		}
		if (listSub.size() > 0) {
			return this.a(listSub, nodeId);
		}
		return null;
	}

	private BpmNodeDef a(List<SubProcessNodeDef> subList, String nodeId) {
		for (SubProcessNodeDef nodeDef : subList) {
			List<BpmNodeDef> nodeList = nodeDef.getChildBpmProcessDef().getBpmnNodeDefs();
			ArrayList<SubProcessNodeDef> nestSub = new ArrayList<SubProcessNodeDef>();
			for (BpmNodeDef tmpDef : nodeList) {
				if (tmpDef.getNodeId().equals(nodeId)) {
					return tmpDef;
				}
				if (!(tmpDef instanceof SubProcessNodeDef))
					continue;
				nestSub.add((SubProcessNodeDef) tmpDef);
			}
			if (nestSub.size() <= 0)
				continue;
			return this.a(nestSub, nodeId);
		}
		return null;
	}

	public BpmNodeDef getStartEvent(String processDefId) {
		DefaultBpmProcessDef processDef = this.i(processDefId);
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
		DefaultBpmProcessDef processDef = this.i(processDefId);
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
		BpmContext.cleanTread();
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
		DefaultBpmProcessDef processDef = this.i(defId);
		List<BpmNodeDef> list = processDef.getBpmnNodeDefs();
		for (BpmNodeDef nodeDef : list) {
			if (!nodeDef.getType().equals((Object) NodeType.CALLACTIVITY))
				continue;
			return true;
		}
		return false;
	}

	private DefaultBpmProcessDef i(String processDefinitionId) {
		DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) BpmContext.getProcessDef((String) processDefinitionId);
		if (processDef != null) {
			return processDef;
		}
		processDef = this.a(processDefinitionId, true);
		if (processDef == null) {
			return null;
		}
		BpmContext.addProcessDef((String) processDefinitionId, (BpmProcessDef) processDef);
		return processDef;
	}

	private synchronized DefaultBpmProcessDef a(String processDefinitionId, Boolean isCache) {
		DefaultBpmProcessDef bpmProcessDef = null;
		if (isCache.booleanValue()) {
			try {
				bpmProcessDef = (DefaultBpmProcessDef) this.bP.getByKey("procdef_" + processDefinitionId);
			} catch (SerializeException e) {
				this.bP.delByKey("procdef_" + processDefinitionId);
				bpmProcessDef = null;
			}
		}
		if (bpmProcessDef != null) {
			return bpmProcessDef;
		}
		BpmDefinition bpmDef = (BpmDefinition) this.c.get(processDefinitionId);
		bpmProcessDef = this.e(bpmDef);
		if (isCache.booleanValue()) {
			this.bP.add("procdef_" + processDefinitionId, bpmProcessDef);
		}
		return bpmProcessDef;
	}

	public DefaultBpmProcessDef e(BpmDefinition bpmDef) {
		if (bpmDef == null) {
			return null;
		}
		JSONObject bpmDefSetting = JSONObject.parseObject((String) bpmDef.getDefSetting());
		DefaultBpmProcessDef bpmProcessDef = new DefaultBpmProcessDef();
		bpmProcessDef.setProcessDefinitionId(bpmDef.getId());
		bpmProcessDef.setName(bpmDef.getName());
		bpmProcessDef.setDefKey(bpmDef.getKey());
		BpmnModel bpmnModel = this.repositoryService.getBpmnModel(bpmDef.getActDefId());
		Process process = (Process) bpmnModel.getProcesses().get(0);
		this.bQ.a(bpmProcessDef, process.getFlowElements());
		BpmProcessDefParser.a((DefaultBpmProcessDef) bpmProcessDef, (JSONObject) bpmDefSetting);
		bpmProcessDef.setJson(bpmDefSetting);
		return bpmProcessDef;
	}

	public List<BpmNodeDef> getNodesByType(String processDefinitionId, NodeType nodeType) {
		DefaultBpmProcessDef processDef = this.i(processDefinitionId);
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
			SubProcessNodeDef subProcessNodeDef;
			BpmProcessDef processDef;
			rtnList.add(def);
			if (!NodeType.SUBPROCESS.equals((Object) def.getType())
					|| (processDef = (subProcessNodeDef = (SubProcessNodeDef) def).getChildBpmProcessDef()) == null)
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

	public IBpmDefinition getDefinitionById(String defId) {
		return (IBpmDefinition) this.c.get(defId);
	}

	public BpmProcessDef initBpmProcessDef(IBpmDefinition bpmDef) {
		try {
			DefaultBpmProcessDef def = this.e((BpmDefinition) bpmDef);
			BpmContext.cleanTread();
			this.bP.delByKey("procdef_" + bpmDef.getId());
			return def;
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), (IStatusCode) BpmStatusCode.PARSER_FLOW_ERROR, (Throwable) e);
		}
	}

	public IBpmDefinition getDefinitionByActDefId(String actDefId) {
		return this.c.getDefinitionByActDefId(actDefId);
	}
}