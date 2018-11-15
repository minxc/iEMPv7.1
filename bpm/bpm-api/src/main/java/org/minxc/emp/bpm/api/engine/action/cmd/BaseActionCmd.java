package org.minxc.emp.bpm.api.engine.action.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.engine.action.handler.ActionHandler;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmnDefinition;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.JsonUtil;
import org.minxc.emp.form.api.model.FormCategory;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.service.UserService;
import org.minxc.emp.system.util.ContextUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 实现ActionCmd基础实现接口。
 */
public abstract class BaseActionCmd implements ActionCmd {
    /**
     * 要设置的流程变量
     */
    protected Map<String, Object> variable_ = new HashMap<String, Object>();
    /**
     * 前端、或者插件过程中设置的人员配置
     */
    protected Map<String, List<SystemIdentity>> identityMap_ = new HashMap<String, List<SystemIdentity>>();

    /**
     * 前端提供业务数据
     */
    protected String busData;

    /**
     * 流程定义
     */
    protected BpmnDefinition bpmDefinition = null;
    /***流程数据未获取时候使用，若已经在执行过程中，使用bpmDefinition**/
    protected String defId;

    /**
     * 流程实例
     */
    protected BpmnInstance bpmInstance = null;
    /***流程数据未获取时候使用，若已经在执行过程中，使用bpmInstance**/
    protected String instanceId;

    /**
     * 数据模型
     */
    protected Map<String, BusinessData> bizDataMap = new HashMap<String, BusinessData>();

    /**
     * 动作名称
     */
    private String actionName;

    /**
     * 业务主键
     */
    private String businessKey;

    /**
     * 数据模式  ：bo业务数据，pk业务主键
     */
    private String dataMode;
    /**
     * 目标节点
     */
    protected String destination;

    protected String formId;

    protected boolean isSource = false;

    public abstract void initSpecialParam(JSONObject flowParam);
    
    public abstract String getNodeId();

    public BaseActionCmd() {
    }

    public BaseActionCmd(String flowParamStr) {
        isSource = true;
        JSONObject flowParam = JSON.parseObject(flowParamStr);
        this.setActionName(flowParam.getString("action"));

        String defId = flowParam.getString("defId");
        if (StringUtils.isNotEmpty(defId)) {
            this.setDefId(defId);
        }
        String instanceId = flowParam.getString("instanceId");
        if (StringUtils.isNotEmpty(instanceId)) {
            this.setInstanceId(instanceId);
        }
        //初始化特殊属性
        initSpecialParam(flowParam);
        //节点人员
        if (flowParam.containsKey("nodeUsers")) {
            handleUserSetting(flowParam.getJSONObject("nodeUsers"));
        }

        //业务数据
        setBusData(JsonUtil.getString(flowParam, "data"));

        String formType = JsonUtil.getString(flowParam, "formType", FormCategory.INNER.value());
        if (FormCategory.INNER.value().equals(formType)) {
            setDataMode(ActionCmd.DATA_MODE_BO);
        } else {
            setDataMode(ActionCmd.DATA_MODE_PK);
        }
    }

    private void handleUserSetting(JSONObject jsonObject) {
        if (jsonObject.isEmpty()) return;

        Map<String, List<SystemIdentity>> map = new HashMap<String, List<SystemIdentity>>();

        Set<String> nodeIds = jsonObject.keySet();
        for (String nodeId : nodeIds) {
            JSONArray users = jsonObject.getJSONArray(nodeId);
            if (users == null || users.isEmpty()) continue;
            List<SystemIdentity> userList = new ArrayList<SystemIdentity>();

            for (Object userObj : users) {
                JSONObject user = (JSONObject) userObj;
                String id = user.getString("id");
                if (StringUtils.isEmpty(id)) continue;

                SystemIdentity bpmInentity = null;//TODO (BpmIdentity) DefaultBpmIdentity.getIdentityByUserId(id, user.getString("name"));
                String type = user.getString("type");
                if (StringUtils.isNotEmpty(type)) {
                    bpmInentity.setType(type);
                }
                userList.add(bpmInentity);
            }
            map.put(nodeId, userList);
        }

        this.setBpmIdentities(map);
    }

    /**
     * 该流程变量会在获取到 execution，或者delegateTask后设置进流程变量中
     *
     * @param variables
     */
    public Map<String, Object> getActionVariables() {
        return variable_;
    }

    /**
     * 该流程变量会在获取到 execution，或者delegateTask后设置进流程变量中
     *
     * @param variables
     */
    public void setActionVariables(Map<String, Object> variables) {
        this.variable_ = variables;
    }


    public void setBpmIdentities(Map<String, List<SystemIdentity>> map) {
        this.identityMap_ = map;
    }

    public void clearBpmIdentities() {
        this.identityMap_.clear();
    }

    public void addBpmIdentity(String key, SystemIdentity bpmIdentity) {
        List<SystemIdentity> list = this.identityMap_.get(key);
        if (BeanUtils.isEmpty(list)) {
            list = new ArrayList<SystemIdentity>();
            list.add(bpmIdentity);
            this.identityMap_.put(key, list);
        } else {
            list.add(bpmIdentity);
        }
    }

    /**
     * 向某个节点添加用户。
     *
     * @param key
     * @param bpmIdentityList void
     */
    public void addBpmIdentity(String key, List<SystemIdentity> bpmIdentityList) {
        List<SystemIdentity> list = this.identityMap_.get(key);
        if (BeanUtils.isEmpty(list)) {
            list = new ArrayList<SystemIdentity>();
            list.addAll(bpmIdentityList);
            this.identityMap_.put(key, list);
        } else {
            list.addAll(bpmIdentityList);
        }
    }

    /**
     * 设置节点的用户数据。
     *
     * @param key
     * @param bpmIdentityList void
     */
    public void setBpmIdentity(String key, List<SystemIdentity> bpmIdentityList) {
        List<SystemIdentity> list = this.identityMap_.get(key);
        if (BeanUtils.isEmpty(list)) {
            list = new ArrayList<SystemIdentity>();
            list.addAll(bpmIdentityList);
            this.identityMap_.put(key, list);
        } else {
            list.clear();
            list.addAll(bpmIdentityList);
        }
    }

    @Override
    public List<SystemIdentity> getBpmIdentity(String nodeId) {
        return this.identityMap_.get(nodeId);
    }

    @Override
    public Map<String, List<SystemIdentity>> getBpmIdentities() {
        return identityMap_;
    }

    public boolean isSource() {
        return isSource;
    }

    public void setSource(boolean isSource) {
        this.isSource = isSource;
    }

    @Override
    public String getBusData() {
        return this.busData;
    }

    public BpmnInstance getBpmInstance() {
        return bpmInstance;
    }

    public void setBpmInstance(BpmnInstance bpmInstance) {
        this.bpmInstance = bpmInstance;
    }

    @Override
    public void setBusData(String json) {
        this.busData = json;
    }

    @Override
    public String getDataMode() {
        return this.dataMode;
    }

    @Override
    public void setDataMode(String mode) {
        this.dataMode = mode;
    }

    @Override
    public String getBusinessKey() {
        return this.businessKey;
    }


    public String getFormId() {
        return formId;
    }

    public String getInstanceId() {
        if (StringUtils.isEmpty(instanceId) && this.bpmInstance != null) {
            return bpmInstance.getId();
        }
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String getDefId() {
        if (StringUtils.isEmpty(defId) && this.bpmInstance != null) {
            return bpmInstance.getDefId();
        }

        return this.defId;
    }

    public void setDefId(String defId) {
        this.defId = defId;
    }

    public Map<String, BusinessData> getBizDataMap() {
        return bizDataMap;
    }

    public void setBizDataMap(Map<String, BusinessData> bizDataMap) {
        this.bizDataMap = bizDataMap;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    @Override
    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    /**
     * 这个一般用于在web service远程调用时设置当前用户。
     *
     * @param curAccount 当前用户帐号。
     *                   void
     */
    public void setCurAccount(String curAccount) {
        UserService userService = AppContextUtil.getBean(UserService.class);
        User user = userService.getUserByAccount(curAccount);
        ContextUtil.setCurrentUser(user);
    }

    @Override
    public String getActionName() {
        return actionName;
    }
	public ActionType getActionType() {
		return ActionType.fromKey(this.getActionName());
	}
    @Override
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public BpmnDefinition getBpmDefinition() {
        return bpmDefinition;
    }

    public void setBpmDefinition(BpmnDefinition bpmDefinition) {
        this.bpmDefinition = bpmDefinition;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    // 同一个cmd只能用一次。第二次必须重新构建
    protected boolean hasExecuted = false;

    @Override
    public synchronized String executeCmd() {
        if (this.hasExecuted) {
            throw new BusinessException("action cmd caonot be invoked twice", BpmnStatusCode.NO_PERMISSION);
        }
        hasExecuted = true;

        ActionType actonType = ActionType.fromKey(this.getActionName());

        ActionHandler handler = (ActionHandler) AppContextUtil.getBean(actonType.getBeanId());
        if (handler == null) {
            throw new BusinessException("action beanId cannot be found :" + actonType.getName(), BpmnStatusCode.NO_TASK_ACTION);
        }
        handler.execute(this);
        //彻底清除线程变量
        BpmnContext.cleanTread();
        return handler.getActionType().getName();
    }

}
