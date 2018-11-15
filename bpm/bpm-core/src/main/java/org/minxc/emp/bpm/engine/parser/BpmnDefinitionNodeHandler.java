package org.minxc.emp.bpm.engine.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.activiti.bpmn.model.Activity;
import org.activiti.bpmn.model.CallActivity;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Gateway;
import org.activiti.bpmn.model.InclusiveGateway;
import org.activiti.bpmn.model.MultiInstanceLoopCharacteristics;
import org.activiti.bpmn.model.ParallelGateway;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.SubProcess;
import org.activiti.bpmn.model.UserTask;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.nodedef.impl.*;
import org.minxc.emp.bpm.api.model.nodedef.impl.BaseBpmnNodeDefinition;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.springframework.stereotype.Component;

@Component
public class BpmnDefinitionNodeHandler {
	private Class<FlowElement>[] bh = new Class[]{StartEvent.class, EndEvent.class, ParallelGateway.class,
			InclusiveGateway.class, ExclusiveGateway.class, UserTask.class, ServiceTask.class, CallActivity.class,
			SubProcess.class};

	public void a(DefaultBpmnProcessDefinition bpmProcessDef, Collection<FlowElement> collection) {
		this.a(null, collection, bpmProcessDef);
	}

	private void a(BpmNodeDef parentNodeDef, Collection<FlowElement> flowElementList,
			DefaultBpmnProcessDefinition bpmProcessDef) {
		Map<String, FlowElement> nodeMap = this.a(flowElementList);
		List<SequenceFlow> seqList = this.b(flowElementList);
		Map<String, BpmNodeDef> nodeDefMap = this.a(nodeMap, parentNodeDef, bpmProcessDef);
		this.a(nodeDefMap, seqList);
		ArrayList<BpmNodeDef> nodeDefList = new ArrayList<BpmNodeDef>(nodeDefMap.values());
		bpmProcessDef.setBpmnNodeDefs(nodeDefList);
		for (BpmNodeDef nodeDef : nodeDefList) {
			BaseBpmnNodeDefinition node = (BaseBpmnNodeDefinition) nodeDef;
			node.setBpmProcessDef((BpmProcessDef) bpmProcessDef);
		}
	}

	private Map<String, BpmNodeDef> a(Map<String, FlowElement> nodeMap, BpmNodeDef parentNodeDef,
			DefaultBpmnProcessDefinition bpmProcessDef) {
		HashMap<String, BpmNodeDef> map = new HashMap<String, BpmNodeDef>();
		Set<Map.Entry<String, FlowElement>> set = nodeMap.entrySet();
		for (Map.Entry<String, FlowElement> ent : set) {
			FlowElement flowEl = ent.getValue();
			BaseBpmnNodeDefinition nodeDef = this.a(parentNodeDef, flowEl, bpmProcessDef);
			map.put(ent.getKey(), (BpmNodeDef) nodeDef);
		}
		return map;
	}

	private void a(Map<String, BpmNodeDef> nodeDefMap, List<SequenceFlow> seqList) {
		for (SequenceFlow seq : seqList) {
			BpmNodeDef sourceDef = nodeDefMap.get(seq.getSourceRef());
			BpmNodeDef targetDef = nodeDefMap.get(seq.getTargetRef());
			sourceDef.addOutcomeNode(targetDef);
			targetDef.addIncomeNode(sourceDef);
		}
	}

	private Map<String, FlowElement> a(Collection<FlowElement> flowElementList) {
		HashMap<String, FlowElement> map = new HashMap<String, FlowElement>();
		for (FlowElement flowElement : flowElementList) {
			this.a(flowElement, map, this.bh);
		}
		return map;
	}

	private BaseBpmnNodeDefinition a(BpmNodeDef parentNodeDef, FlowElement flowElement, DefaultBpmnProcessDefinition bpmProcessDef) {
		BaseBpmnNodeDefinition nodeDef = null;
		if (flowElement instanceof StartEvent) {
			nodeDef = new BaseBpmnNodeDefinition();
			nodeDef.setType(NodeType.START);
		} else if (flowElement instanceof EndEvent) {
			nodeDef = new BaseBpmnNodeDefinition();
			nodeDef.setType(NodeType.END);
		} else if (flowElement instanceof Gateway) {
			nodeDef = new GateWayBpmnNodeDefinition();
			if (flowElement instanceof ParallelGateway) {
				nodeDef.setType(NodeType.PARALLELGATEWAY);
			} else if (flowElement instanceof InclusiveGateway) {
				nodeDef.setType(NodeType.INCLUSIVEGATEWAY);
			} else if (flowElement instanceof ExclusiveGateway) {
				nodeDef.setType(NodeType.EXCLUSIVEGATEWAY);
			}
		} else if (flowElement instanceof Activity) {
			String multi = this.a((Activity) flowElement);
			if (flowElement instanceof UserTask) {
				if (multi == null) {
					UserTaskNodeDefinition userTaskDef = new UserTaskNodeDefinition();
					nodeDef = userTaskDef;
					nodeDef.setType(NodeType.USERTASK);
				}
			} else if (!(flowElement instanceof ServiceTask)) {
				if (flowElement instanceof CallActivity) {
					CallActivityNodeDefinition callNodeDef = new CallActivityNodeDefinition();
					CallActivity call = (CallActivity) flowElement;
					String flowKey = call.getCalledElement();
					callNodeDef.setType(NodeType.CALLACTIVITY);
					callNodeDef.setFlowKey(flowKey);
					nodeDef = callNodeDef;
				} else if (flowElement instanceof SubProcess) {
					SubProcessNodeDefinition subProcessDef = new SubProcessNodeDefinition();
					nodeDef = subProcessDef;
					nodeDef.setNodeId(flowElement.getId());
					nodeDef.setName(flowElement.getName());
					nodeDef.setParentBpmNodeDef(parentNodeDef);
					subProcessDef.setBpmProcessDef((BpmProcessDef) bpmProcessDef);
					SubProcess subProcess = (SubProcess) flowElement;
					this.a(nodeDef, subProcess, bpmProcessDef);
				}
			}
		}
		nodeDef.setParentBpmNodeDef(parentNodeDef);
		nodeDef.setNodeId(flowElement.getId());
		nodeDef.setName(flowElement.getName());
		return nodeDef;
	}

	private void a(BaseBpmnNodeDefinition nodeDef, SubProcess subProcess, DefaultBpmnProcessDefinition parentProcessDef) {
		DefaultBpmnProcessDefinition bpmProcessDef = new DefaultBpmnProcessDefinition();
		bpmProcessDef.setProcessDefinitionId(subProcess.getId());
		bpmProcessDef.setName(subProcess.getName());
		bpmProcessDef.setDefKey(subProcess.getId());
		bpmProcessDef.setParentProcessDef(parentProcessDef);
		SubProcessNodeDefinition subNodeDef = (SubProcessNodeDefinition) nodeDef;
		subNodeDef.setBpmProcessDef((BpmProcessDef) parentProcessDef);
		subNodeDef.setChildBpmProcessDef((BpmProcessDef) bpmProcessDef);
		Collection list = subProcess.getFlowElements();
		this.a((BpmNodeDef) nodeDef, list, bpmProcessDef);
	}

	private void a(FlowElement flowElement, Map<String, FlowElement> map, Class<? extends FlowElement>... flowTypes) {
		for (Class<? extends FlowElement> flowType : flowTypes) {
			if (!flowType.isInstance((Object) flowElement))
				continue;
			map.put(flowElement.getId(), flowElement);
			break;
		}
	}

	private String a(Activity flowElement) {
		MultiInstanceLoopCharacteristics jaxbloop = flowElement.getLoopCharacteristics();
		if (jaxbloop == null) {
			return null;
		}
		return jaxbloop.isSequential() ? "sequence" : "parallel";
	}

	private List<SequenceFlow> b(Collection<FlowElement> flowElementList) {
		ArrayList<SequenceFlow> nodeList = new ArrayList<SequenceFlow>();
		for (FlowElement flowElement : flowElementList) {
			if (!(flowElement instanceof SequenceFlow))
				continue;
			nodeList.add((SequenceFlow) flowElement);
		}
		return nodeList;
	}
}