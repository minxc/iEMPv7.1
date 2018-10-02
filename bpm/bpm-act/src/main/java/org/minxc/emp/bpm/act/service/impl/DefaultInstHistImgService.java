package org.minxc.emp.bpm.act.service.impl;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.query.Query;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.IOUtils;
import org.minxc.emp.bpm.act.service.BpmImageService;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultInstHistImgService implements BpmImageService {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ManagementService managementService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private ProcessEngineConfiguration processEngineConfiguration;
	@Autowired
	private ProcessEngineFactoryBean i;
	@Autowired
	private HistoryService historyService;

	public InputStream draw(String actDefId, String actInstId) throws Exception {
		InputStream imageStream = null;
		if (StringUtil.isEmpty(actDefId)) {
			throw new BusinessException("流程定义actDefId不能缺失", BpmStatusCode.PARAM_ILLEGAL);
		}
		if (StringUtil.isEmpty(actInstId)) {
			return this.b(actDefId);
		}
		List<String> activeActivityIds = new ArrayList<String>();
		List<String> highLightedFlows = new ArrayList<>();
		if (this.a(actInstId)) {
			activeActivityIds.add(((HistoricActivityInstance) this.historyService.createHistoricActivityInstanceQuery()
					.executionId(actInstId).activityType("endEvent").singleResult()).getActivityId());
		} else {
			activeActivityIds = this.runtimeService.getActiveActivityIds(actInstId);
		}
		List historicActivityInstances = ((HistoricActivityInstanceQuery) this.historyService
				.createHistoricActivityInstanceQuery().executionId(actInstId).orderByHistoricActivityInstanceStartTime()
				.asc()).list();
		highLightedFlows = this.a((ProcessDefinitionEntity) ((RepositoryServiceImpl) this.repositoryService).getDeployedProcessDefinition(actDefId), historicActivityInstances);
		
		if (null != activeActivityIds) {
			try {
				ProcessEngineConfigurationImpl processEngineConfiguration = this.i.getProcessEngineConfiguration();
				BpmnModel bpmnModel = this.repositoryService.getBpmnModel(actDefId);
				DefaultProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();
				imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds, highLightedFlows,
						processEngineConfiguration.getActivityFontName(), processEngineConfiguration.getLabelFontName(),
						processEngineConfiguration.getAnnotationFontName(), processEngineConfiguration.getClassLoader(),1.0);
			} catch (Throwable throwable) {
				IOUtils.closeQuietly(imageStream);
				throw throwable;
			}
			IOUtils.closeQuietly((InputStream) imageStream);
		}
		return imageStream;
	}

	private List<String> a(ProcessDefinitionEntity processDefinitionEntity,
			List<HistoricActivityInstance> historicActivityInstances) {
		ArrayList<String> highFlows = new ArrayList<String>();
		for (int i = 0; i < historicActivityInstances.size() - 1; ++i) {
			ActivityImpl activityImpl = processDefinitionEntity
					.findActivity(historicActivityInstances.get(i).getActivityId());
			ArrayList<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();
			ActivityImpl sameActivityImpl1 = processDefinitionEntity
					.findActivity(historicActivityInstances.get(i + 1).getActivityId());
			sameStartTimeNodes.add(sameActivityImpl1);
			for (int j = i + 1; j < historicActivityInstances.size() - 1; ++j) {
				HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);
				HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);
				if (!activityImpl1.getStartTime().equals(activityImpl2.getStartTime()))
					break;
				ActivityImpl sameActivityImpl2 = processDefinitionEntity.findActivity(activityImpl2.getActivityId());
				sameStartTimeNodes.add(sameActivityImpl2);
			}
			List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
			for (PvmTransition pvmTransition : pvmTransitions) {
				ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
				if (!sameStartTimeNodes.contains((Object) pvmActivityImpl))
					continue;
				highFlows.add(pvmTransition.getId());
			}
		}
		return highFlows;
	}

	public boolean a(String processInstanceId) {
		return this.historyService.createHistoricProcessInstanceQuery().finished().processInstanceId(processInstanceId)
				.count() > 0L;
	}

	private InputStream b(String actDefId) {
		BpmnModel bpmnModel = this.repositoryService.getBpmnModel(actDefId);
		return this.processEngineConfiguration.getProcessDiagramGenerator().generateDiagram(bpmnModel, "png",
				this.processEngineConfiguration.getActivityFontName(),
				this.processEngineConfiguration.getLabelFontName(),
				this.processEngineConfiguration.getAnnotationFontName(),
				this.processEngineConfiguration.getClassLoader());
	}
}