package org.minxc.emp.bpm.api.model.nodedef.impl;

import java.util.List;

import org.minxc.emp.bpm.api.model.def.BpmVariableDef;


/**
 * 用户任务节扩展定义。
 */
public class UserTaskNodeDefinition extends BaseBpmnNodeDefinition {

    private List<BpmVariableDef> variableList;

    public List<BpmVariableDef> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<BpmVariableDef> variableList) {
        this.variableList = variableList;
    }
}
