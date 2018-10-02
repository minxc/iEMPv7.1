package org.minxc.emp.bpm.core.manager.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.bpmn.deployer.BpmnDeployer;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.BpmnParser;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.deploy.DeploymentCache;
import org.activiti.engine.impl.persistence.deploy.DeploymentManager;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.minxc.emp.basis.api.constant.EnvironmentConstant;
import org.minxc.emp.basis.api.constant.RightsObjectConstants;
import org.minxc.emp.bpm.api.engine.event.BpmDefinitionUpdateEvent;
import org.minxc.emp.bpm.api.model.def.BpmDefProperties;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;
import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.core.dao.BpmDefinitionDao;
import org.minxc.emp.bpm.core.manager.BpmDefinitionManager;
import org.minxc.emp.bpm.core.manager.BpmInstanceManager;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.system.api.service.SysAuthorizationService;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

/**
 * 
*    
* 项目名称：wf-core   
* 类名称：BpmDefinitionManagerImpl   
* 类描述：  TODO: BpmDefinitionManagerImpl  几个内部方法的推测
* 创建人：Xianchang.min   
* 创建时间：2018年8月11日 下午7:22:07   
* 修改人：Xianchang.min   
* 修改时间：2018年8月11日 下午7:22:07   
* 修改备注：   
* @version  1.0  
*
 */
@Service(value = "bpmDefinitionManager")
public class BpmDefinitionManagerImpl extends CommonManager<String, BpmDefinition> implements BpmDefinitionManager {
	@Resource
	private BpmDefinitionDao bpmDefinitionDao;
	@Resource
	private BpmProcessDefService bpmProcessDefService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private ProcessEngineConfiguration processEngineConfiguration;
	@Resource
	private SysAuthorizationService sysAuthorizationService;
	@Resource
	private BpmInstanceManager bpmInstanceManager;

	public void create(BpmDefinition bpmDefinition) {
		if (StringUtil.isNotEmpty(bpmDefinition.getId())) {
			this.update(bpmDefinition);
			return;
		}
		List defList = this.bpmDefinitionDao.getByKey(bpmDefinition.getKey());
		if (BeanUtils.isNotEmpty((Object) defList)) {
			throw new BusinessException("流程定义Key重复：" + bpmDefinition.getKey());
		}
		bpmDefinition.setIsMain("Y");
		bpmDefinition.setStatus("draft");
		bpmDefinition.setVersion(Integer.valueOf(1));
		String defId = UniqueIdUtil.getSuid();
		bpmDefinition.setId(defId);
		bpmDefinition.setMainDefId(defId);
		String modelId = this.b(bpmDefinition);
		bpmDefinition.setActModelId(modelId);
		this.bpmDefinitionDao.create(bpmDefinition);
	}

	private String b(BpmDefinition bpmDefinition) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.set("stencilset", (JsonNode) stencilSetNode);
			Model modelData = this.repositoryService.newModel();
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put("name", bpmDefinition.getName());
			modelObjectNode.put("revision", 1);
			modelObjectNode.put("key", bpmDefinition.getKey());
			modelObjectNode.put("description", bpmDefinition.getDesc());
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(bpmDefinition.getName());
			modelData.setKey(bpmDefinition.getKey());
			this.repositoryService.saveModel(modelData);
			this.repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
			return modelData.getId();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("创建activiti流程定义失败！", e);
		}
	}

	public void updateBpmnModel(Model model, MultiValueMap<String, String> values) throws Exception {
		ByteArrayInputStream svgStream = new ByteArrayInputStream(values.getFirst("svg_xml").getBytes("utf-8"));
		TranscoderInput input = new TranscoderInput(svgStream);
		String bpmDefSettingJSON = (String) values.getFirst( "bpmDefSetting");
		BpmDefinition bpmDef = this.bpmDefinitionDao.getMainByDefKey(model.getKey());
		bpmDef.setName((String) values.getFirst("name"));
		bpmDef.setDesc((String) values.getFirst("description"));
		bpmDef.setDefSetting(bpmDefSettingJSON);
		PNGTranscoder transcoder = new PNGTranscoder();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		TranscoderOutput output = new TranscoderOutput((OutputStream) outStream);
		transcoder.transcode(input, output);
		byte[] result = outStream.toByteArray();
		byte[] b = ((String) values.getFirst("json_xml")).getBytes("utf-8");
		this.repositoryService.addModelEditorSource(model.getId(), b);
		boolean publish = Boolean.parseBoolean((String) values.getFirst("publish"));
		ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(b);
		BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel((JsonNode) modelNode);
		byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
		if (StringUtil.isEmpty(bpmDef.getActDefId()) || publish) {
			this.b(bpmDef, model, bpmnBytes);
		} else {
			this.a(bpmDef, model, bpmnBytes);
		}
		this.repositoryService.saveModel(model);
		this.repositoryService.addModelEditorSourceExtra(model.getId(), result);
		DefaultBpmProcessDef def = (DefaultBpmProcessDef) this.bpmProcessDefService.initBpmProcessDef((IBpmDefinition) bpmDef);
		if ("deploy".equals(bpmDef.getStatus()) && "deploy".equals(def.getExtProperties().getStatus())
				&& !AppContextUtil.getCtxEnvironment().contains(EnvironmentConstant.PROD.key())) {
			throw new BusinessException("除了生产环境外，已发布状态的流程禁止修改！");
		}
		if (StringUtil.isEmpty((String) bpmDef.getStatus())
				|| !bpmDef.getStatus().equals(def.getExtProperties().getStatus())
				|| !bpmDef.getSupportMobile().equals(def.getExtProperties().getSupportMobile())) {
			bpmDef.setStatus(def.getExtProperties().getStatus());
			bpmDef.setSupportMobile(def.getExtProperties().getSupportMobile());
			this.bpmDefinitionDao.update(bpmDef);
		}
		this.c(bpmDef);
		outStream.close();
	}

	private void a(BpmDefinition definition, Model model, byte[] bpmnBytes)
			throws JsonProcessingException, IOException {
		ProcessDefinition bpmnProcessDef = this.repositoryService.getProcessDefinition(definition.getActDefId());
		ProcessEngineConfigurationImpl conf = (ProcessEngineConfigurationImpl) this.processEngineConfiguration;
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) conf);
		DeploymentManager deploymentManager = conf.getDeploymentManager();
		BpmnDeployer deployer = (BpmnDeployer) deploymentManager.getDeployers().get(0);
		DeploymentEntity deploy = (DeploymentEntity) this.repositoryService.createDeploymentQuery()
				.deploymentId(definition.getActDeployId()).list().get(0);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bpmnBytes);
		BpmnParse bpmnParse = deployer.getBpmnParser().createParse().sourceInputStream((InputStream) inputStream)
				.setSourceSystemId(model.getKey() + ".bpmn20.xml").deployment(deploy)
				.name(model.getKey() + ".bpmn20.xml");
		bpmnParse.execute();
		BpmnModel bpmnModel = bpmnParse.getBpmnModel();
		deploymentManager.getBpmnModelCache().add(bpmnProcessDef.getId(), bpmnModel);
		byte[] diagramBytes = IoUtil.readInputStream(
				(InputStream) this.processEngineConfiguration.getProcessDiagramGenerator().generateDiagram(bpmnModel,
						"png", this.processEngineConfiguration.getActivityFontName(),
						this.processEngineConfiguration.getLabelFontName(),
						this.processEngineConfiguration.getAnnotationFontName(),
						this.processEngineConfiguration.getClassLoader()),
				null);
		this.bpmDefinitionDao.updateActResourceEntity(bpmnProcessDef.getDeploymentId(), model.getKey() + ".bpmn20.xml", bpmnBytes);
		this.bpmDefinitionDao.updateActResourceEntity(bpmnProcessDef.getDeploymentId(),
				model.getKey() + "." + bpmnProcessDef.getKey() + ".png", diagramBytes);
		this.update(definition);
	}

	private void b(BpmDefinition definition, Model model, byte[] bpmnBytes) {
		try {
			String processName = model.getKey() + ".bpmn20.xml";
			Deployment deployment = this.repositoryService.createDeployment().name(model.getKey())
					.addString(processName, new String(bpmnBytes)).deploy();
			ProcessDefinition proDefinition = (ProcessDefinition) this.repositoryService.createProcessDefinitionQuery()
					.deploymentId(deployment.getId()).singleResult();
			if (proDefinition == null) {
				throw new RuntimeException("error   ");
			}
			model.setDeploymentId(deployment.getId());
			if (StringUtil.isEmpty((String) definition.getActDefId())) {
				definition.setActDefId(proDefinition.getId());
				definition.setActDeployId(deployment.getId());
				this.update(definition);
				return;
			}
			String newDefId = UniqueIdUtil.getSuid();
			definition.setIsMain("N");
			definition.setMainDefId(newDefId);
			this.update(definition);
			definition.setId(newDefId);
			definition.setIsMain("Y");
			definition.setRev(Integer.valueOf(0));
			definition.setVersion(Integer.valueOf(definition.getVersion() + 1));
			definition.setCreateBy(ContextUtil.getCurrentUser().getUserId());
			definition.setCreateTime(new Date());
			definition.setActDefId(proDefinition.getId());
			definition.setActDeployId(deployment.getId());
			definition.setActModelId(model.getId());
			this.bpmDefinitionDao.create(definition);
		} catch (Exception e) {
			throw new RuntimeException("Invoke natProDefinitionService.deploy method error = " + e.getMessage());
		}
	}

	public BpmDefinition getMainDefByActModelId(String actModelId) {
		return this.bpmDefinitionDao.getMainDefByActModelId(actModelId);
	}

	private void c(BpmDefinition def) {
		List<BpmDefinition> defList = this.bpmDefinitionDao.getByKey(def.getKey());
		for (BpmDefinition defEntity : defList) {
			AppContextUtil.publishEvent((ApplicationEvent) new BpmDefinitionUpdateEvent((IBpmDefinition) defEntity));
		}
		AppContextUtil.publishEvent((ApplicationEvent) new BpmDefinitionUpdateEvent((IBpmDefinition) def));
	}

	public BpmDefinition getDefinitionByActDefId(String actDefId) {
		return this.bpmDefinitionDao.getByActDefId(actDefId);
	}

	public BpmDefinition getByKey(String flowKey) {
		return this.bpmDefinitionDao.getMainByDefKey(flowKey);
	}

	public List<BpmDefinition> getMyDefinitionList(String userId, QueryFilter queryFilter) {
		Map<String, Object> map = this.sysAuthorizationService.getUserRightsSql(RightsObjectConstants.FLOW, userId, null);
		queryFilter.addParams(map);
		return this.bpmDefinitionDao.getMyDefinitionList(queryFilter);
	}

	public void remove(String entityId) {
		BpmDefinition definition = (BpmDefinition) this.bpmDefinitionDao.get(entityId);
		if (this.hasProcessInstance(definition.getId())) {
			throw new BusinessException("该流程定义下存在流程实例，请勿删除！<br> 请清除数据后再来删除");
		}
		List<BpmDefinition> definitionList = this.bpmDefinitionDao.getByKey(definition.getKey());
		for (BpmDefinition def : definitionList) {
			AppContextUtil.publishEvent((ApplicationEvent) new BpmDefinitionUpdateEvent((IBpmDefinition) def));
			this.bpmDefinitionDao.remove(def.getId());
			if (!StringUtil.isNotEmpty((String) def.getActDeployId()))
				continue;
			this.repositoryService.deleteDeployment(def.getActDeployId());
		}
		if (StringUtil.isNotEmpty((String) definition.getActModelId())) {
			this.repositoryService.deleteModel(definition.getActModelId());
		}
	}

	private boolean hasProcessInstance(String defId) {
		DefaultQueryFilter query = new DefaultQueryFilter();
		query.addFilter("def_id_", defId, QueryOperator.EQUAL);
		List<BpmInstance> list = this.bpmInstanceManager.query(query);
		return BeanUtils.isNotEmpty((Object) list);
	}

	public void update(BpmDefinition entity) {
		entity.setUpdateTime(new Date());
		int updateRows = this.bpmDefinitionDao.update(entity);
		if (updateRows == 0) {
			AppContextUtil.publishEvent((ApplicationEvent) new BpmDefinitionUpdateEvent((IBpmDefinition) entity));
			throw new RuntimeException("流程定义更新失败，当前版本并非最新版本！已经刷新当前服务器缓存，请刷新页面重新修改提交。id:" + entity.getId() + "reversion:"
					+ entity.getRev());
		}
	}
	
}