package org.minxc.emp.bpm.act.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;

import com.minxc.emp.core.util.AppContextUtil;
import com.minxc.emp.core.util.FileUtil;

public class ActivitiUtil {
	public static CommandExecutor getCommandExecutor() {
		ProcessEngineImpl engine = (ProcessEngineImpl) AppContextUtil.getBean(ProcessEngine.class);
		CommandExecutor cmdExecutor = engine.getProcessEngineConfiguration().getCommandExecutor();
		return cmdExecutor;
	}

	public static Map<String, Object> a(String actDefId, String nodeId, String[] aryDestination) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		RepositoryService repositoryService = (RepositoryService) AppContextUtil.getBean(RepositoryService.class);
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(actDefId);
		ActivityImpl curAct = processDefinition.findActivity(nodeId);
		List<PvmTransition> outTrans = curAct.getOutgoingTransitions();
		try {
			List<PvmTransition> cloneOutTrans = (List) FileUtil.cloneObject(outTrans);
			map.put("outTrans", cloneOutTrans);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (PvmTransition transition : outTrans) {
			PvmActivity activity = transition.getDestination();
			List<PvmTransition> inTrans = activity.getIncomingTransitions();
			Iterator<PvmTransition> itIn = inTrans.iterator();
			while (itIn.hasNext()) {
				PvmTransition inTransition = itIn.next();
				if (!inTransition.getSource().getId().equals(curAct.getId()))
					continue;
				itIn.remove();
			}
		}
		curAct.getOutgoingTransitions().clear();
		if (aryDestination != null && aryDestination.length > 0) {
			for (String dest : aryDestination) {
				ActivityImpl destAct = processDefinition.findActivity(dest);
				TransitionImpl transitionImpl = curAct.createOutgoingTransition();
				transitionImpl.setDestination(destAct);
			}
		}
		map.put("activity", (Object) curAct);
		return map;
	}

	public static void a(Map<String, Object> map) {
		ActivityImpl curAct = (ActivityImpl) map.get("activity");
		List outTrans = (List) map.get("outTrans");
		curAct.getOutgoingTransitions().clear();
		curAct.getOutgoingTransitions().addAll(outTrans);
	}
}