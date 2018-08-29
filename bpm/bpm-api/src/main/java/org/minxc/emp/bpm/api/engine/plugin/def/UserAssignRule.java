package org.minxc.emp.bpm.api.engine.plugin.def;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.bpm.api.engine.plugin.context.UserCalcPluginContext;

/**
 * 用户分配规则定义。
 */
public class UserAssignRule implements Comparable<UserAssignRule>, Serializable {

    private static final long serialVersionUID = 1L;
    //规则名称
    private String name = "";
    //规则描述
    private String description = "";
    //规则条件
    private String condition = "";
    /*	//条件模式(设计器，直接填写脚本)
        private String conditionMode="";*/
    //分组编号。
    private int groupNo = 1;
    //人员计算规则
    private List<UserCalcPluginContext> calcPluginContextList = new ArrayList<UserCalcPluginContext>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        if (StringUtils.isEmpty(description)) {
            String desc = "";
            for (UserCalcPluginContext ctx : calcPluginContextList)
                desc = desc + "　　　【" + ctx.getTitle() + "】" + ctx.getDescription() + ";";

            return desc;
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
	
/*	
	public String getConditionMode() {
		return conditionMode;
	}

	public void setConditionMode(String conditionMode) {
		this.conditionMode = conditionMode;
	}*/

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public List<UserCalcPluginContext> getCalcPluginContextList() {
        return calcPluginContextList;
    }

    public void setCalcPluginContextList(
            List<UserCalcPluginContext> calcPluginContextList) {
        this.calcPluginContextList = calcPluginContextList;
    }

    @Override
    public int compareTo(UserAssignRule userRule) {
        if (this.groupNo > userRule.groupNo) return 1;
        if (this.groupNo < userRule.groupNo) return -1;
        return 0;
    }
}
