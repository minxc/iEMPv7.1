package org.minxc.emp.bpm.api.model.nodedef.impl;

import java.util.List;

import org.minxc.emp.bpm.api.model.def.BpmnVariableDef;


/**
 * 用户任务节扩展定义。
 */
public class UserTaskNodeDef extends BaseBpmnNodeDef {

    private List<BpmnVariableDef> variableList;

    public List<BpmnVariableDef> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<BpmnVariableDef> variableList) {
        this.variableList = variableList;
    }
}
