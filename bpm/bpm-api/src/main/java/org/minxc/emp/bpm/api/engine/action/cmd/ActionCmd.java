package org.minxc.emp.bpm.api.engine.action.cmd;

import java.util.List;
import java.util.Map;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;

/**
 * 任务操作处理基础接口
 */
public interface ActionCmd {

    /**
     * 数据模型类型
     */
    public static final String DATA_MODE_PK = "pk";
    public static final String DATA_MODE_BO = "bo";


    /**
     * 获取变量。
     * 在启动/或者任务完成时设置进去，
     * 会将流程变量更新进activiti流程变量中
     * 当任务完成动作执行时，之前设置的流程变量会覆盖activiti流程变量。
     *
     * @return
     */

    Map<String, Object> getActionVariables();


    Map<String, Object> getVariables();

    /**
     * 添加变量。
     *
     * @param name
     * @param value void
     */
    void addVariable(String name, Object value);

    Object getVariable(String variableName);

    boolean hasVariable(String variableName);

    void removeVariable(String variableName);

    /**
     * 获取下一步节点执行人。
     * 	键为节点ID
     *  值为接收人员。
     *
     * @return Map&lt;String,List&lt;BpmIdentity>>
     */
    Map<String, List<SystemIdentity>> getBpmIdentities();

    List<SystemIdentity> getBpmIdentity(String nodeId);

    void setBpmIdentity(String nodeId, List<SystemIdentity> bpmIdentityList);

    /**
     * 获取业务数据模式。
     *
     * @return String
     */
    String getDataMode();

    /**
     * 设置业务数据模式。
     * void
     */
    void setDataMode(String mode);

    /**
     * bo的JSON数据。
     *
     * @param json void
     */
    void setBusData(String json);

    /**
     * 获取BO的JSON数据。
     *
     * @return
     */
    String getBusData();


    /**
     * 获取业务主键
     *
     * @return String
     */
    String getBusinessKey();

    /**
     * 设置主键。
     *
     * @param businessKey void
     */
    void setBusinessKey(String businessKey);

    /**
     * 获取审批的处理状态或动作。
     * 
     * 启动流程:
     * startFlow
     * draft
     *
     * 审批流程：
     * 1.agree 审批。
     * 3.oppose 反对。
     * 7.reject 驳回。
     * 
     *
     * @return String
     */
    String getActionName();

    /**
     * 设置动作名称。
     *
     * @param actionName
     * @return
     */
    void setActionName(String actionName);

    String getDefId();

    public BpmnInstance getBpmInstance();

    String getDestination();

    String getFormId();

    /**
     * Map<boCode,bizData>
     *
     * @return
     */
    public Map<String, BusinessData> getBizDataMap();

    public String executeCmd();
}
