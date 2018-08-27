package org.minxc.emp.bpm.api.model.nodedef.impl;

import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.engine.action.button.ButtonFactory;
import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmnProcessDef;
import org.minxc.emp.bpm.api.model.def.NodeInit;
import org.minxc.emp.bpm.api.model.def.NodeProperties;
import org.minxc.emp.bpm.api.model.form.BpmnForm;
import org.minxc.emp.bpm.api.model.nodedef.BpmnNodeDef;
import org.minxc.emp.bpm.api.model.nodedef.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseBpmnNodeDef implements BpmnNodeDef {
    private static final long serialVersionUID = -2044846605763777657L;

    private String nodeId;
    private String name;
    private NodeType type;
    private BpmnNodeDef parentBpmNodeDef;
    private List<BpmnNodeDef> incomeNodes = new ArrayList<BpmnNodeDef>();
    private List<BpmnNodeDef> outcomeNodes = new ArrayList<BpmnNodeDef>();

    private List<BpmnPluginContext> bpmPluginContexts = new ArrayList<BpmnPluginContext>();
    private Map<String, String> attrMap = new HashMap<String, String>();

    private BpmnProcessDef bpmProcessDef;

    /**
     * 节点配置表单。
     */
    private BpmnForm mobileForm;

    private BpmnForm form;

    /**
     * 表单初始化项。
     */
    private List<NodeInit> nodeInits = new ArrayList<NodeInit>();

    /**
     * 节点属性。
     */
    private NodeProperties nodeProperties = new NodeProperties();

    private List<Button> buttons = null;

    private List<Button> buttonList = null;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public List<BpmnNodeDef> getIncomeNodes() {
        return incomeNodes;
    }

    public void setIncomeNodes(List<BpmnNodeDef> incomeNodes) {
        this.incomeNodes = incomeNodes;
    }

    public List<BpmnNodeDef> getOutcomeNodes() {
        return outcomeNodes;
    }

    public void setOutcomeNodes(List<BpmnNodeDef> outcomeNodes) {
        this.outcomeNodes = outcomeNodes;
    }

    public List<BpmnPluginContext> getBpmPluginContexts() {
        return bpmPluginContexts;
    }

    public void setBpmPluginContexts(List<BpmnPluginContext> bpmPluginContexts) {
    	Collections.sort(bpmPluginContexts);
        this.bpmPluginContexts = bpmPluginContexts;
    }

    public void setAttribute(String name, String value) {
        name = name.toLowerCase().trim();
        attrMap.put(name.toLowerCase(), value);

    }

    @Override
    public String getAttribute(String name) {
        name = name.toLowerCase().trim();
        return attrMap.get(name);

    }

    @Override
    public void addIncomeNode(BpmnNodeDef bpmNodeDef) {
        this.incomeNodes.add(bpmNodeDef);
    }

    @Override
    public void addOutcomeNode(BpmnNodeDef bpmNodeDef) {
        this.outcomeNodes.add(bpmNodeDef);

    }

    @Override
    public BpmnNodeDef getParentBpmNodeDef() {
        return this.parentBpmNodeDef;
    }

    public void setParentBpmNodeDef(BpmnNodeDef parentBpmNodeDef) {
        this.parentBpmNodeDef = parentBpmNodeDef;
    }

    @Override
    public String getRealPath() {
        String id = this.getNodeId();
        BpmnNodeDef parent = getParentBpmNodeDef();
        while (parent != null) {
            id = parent.getNodeId() + "/" + id;
            parent = parent.getParentBpmNodeDef();
        }
        return id;
    }

    public BpmnProcessDef getBpmProcessDef() {
        return bpmProcessDef;
    }

    public void setBpmProcessDef(BpmnProcessDef bpmProcessDef) {
        this.bpmProcessDef = bpmProcessDef;
    }

    public BpmnProcessDef getRootProcessDef() {
        BpmnProcessDef processDef = this.bpmProcessDef;
        BpmnProcessDef parent = processDef.getParentProcessDef();
        while (parent != null) {
            processDef = parent;
            parent = parent.getParentProcessDef();
        }
        return processDef;
    }


    @Override
    public List<BpmnNodeDef> getOutcomeTaskNodes() {
        return getNodeDefList(outcomeNodes);
    }

    private List<BpmnNodeDef> getNodeDefList(List<BpmnNodeDef> bpmNodeDefs) {
        List<BpmnNodeDef> bpmNodeList = new ArrayList<BpmnNodeDef>();
        for (BpmnNodeDef def : bpmNodeDefs) {
            if (NodeType.USERTASK.equals(def.getType()) || NodeType.SIGNTASK.equals(def.getType())) {
                bpmNodeList.add(def);
            } else if (NodeType.SUBPROCESS.equals(def.getType())) {
                SubProcessNodeDef subProcessNodeDef = (SubProcessNodeDef) def;
                // 取得子流程中的开始节点
                BpmnNodeDef startNodeDef = subProcessNodeDef.getChildBpmProcessDef().getStartEvent();
                bpmNodeList.addAll(getNodeDefList(startNodeDef.getOutcomeNodes()));
            } else if (NodeType.END.equals(def.getType()) && def.getParentBpmNodeDef() != null && NodeType.SUBPROCESS.equals(def.getParentBpmNodeDef().getType())) {
                SubProcessNodeDef subProcessNodeDef = (SubProcessNodeDef) def.getParentBpmNodeDef();
                bpmNodeList.addAll(getNodeDefList(subProcessNodeDef.getOutcomeNodes()));
            } else {
                bpmNodeList.addAll(getNodeDefList(def.getOutcomeNodes()));
            }
        }
        return bpmNodeList;
    }

    @Override
    public List<BpmnNodeDef> getInnerOutcomeTaskNodes(boolean includeSignTask) {
        List<BpmnNodeDef> bpmNodeList = getInnerOutcomeTaskNodes(outcomeNodes, includeSignTask);
        return bpmNodeList;
    }

    private List<BpmnNodeDef> getInnerOutcomeTaskNodes(List<BpmnNodeDef> bpmNodeDefs, boolean includeSignTask) {
        List<BpmnNodeDef> bpmNodeList = new ArrayList<BpmnNodeDef>();
        for (BpmnNodeDef def : bpmNodeDefs) {
            if (NodeType.USERTASK.equals(def.getType()) || (includeSignTask && NodeType.SIGNTASK.equals(def.getType()))) {
                bpmNodeList.add(def);
            } else if (NodeType.SUBPROCESS.equals(def.getType()) || NodeType.CALLACTIVITY.equals(def.getType()) || NodeType.END.equals(def.getType())) {
                continue;
            } else {
                bpmNodeList.addAll(getInnerOutcomeTaskNodes(def.getOutcomeNodes(), includeSignTask));
            }
        }
        return bpmNodeList;
    }

    @Override
    public <T> T getPluginContext(Class<T> cls) {
        BpmnPluginContext ctx = null;
        List<BpmnPluginContext> list = this.bpmPluginContexts;
        for (BpmnPluginContext context : list) {
            if (context.getClass().isAssignableFrom(cls)) {
                ctx = context;
                break;
            }
        }
        return (T) ctx;
    }


    public NodeProperties getNodeProperties() {
        return nodeProperties;
    }

    public void setNodeProperties(NodeProperties nodeProperties) {
        this.nodeProperties = nodeProperties;
    }


    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    @Override
    public List<Button> getButtons() {
        if (this.buttons != null) return this.buttons;
        if (buttonList != null) return buttonList;

        try {
            buttonList = ButtonFactory.generateButtons(this, true);
        } catch (Exception e) {
            throw new BusinessException(BpmnStatusCode.TASK_ACTION_BTN_ERROR, e);
        }
        return buttonList;
    }

    /**
     * 是否存在自定义按钮
     *
     * @return
     */
    public boolean isDefaultBtn() {
        if (this.buttons != null)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getName()).append(":").append(this.getNodeId())
                .append(super.toString());
        return sb.toString();
    }

    public BpmnForm getMobileForm() {
        return mobileForm;
    }

    public void setMobileForm(BpmnForm mobileForm) {
        this.mobileForm = mobileForm;
    }

    public BpmnForm getForm() {
        return form;
    }

    public void setForm(BpmnForm form) {
        this.form = form;
    }
}
