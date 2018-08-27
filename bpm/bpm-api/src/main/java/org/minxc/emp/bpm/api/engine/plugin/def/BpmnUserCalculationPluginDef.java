package org.minxc.emp.bpm.api.engine.plugin.def;

import org.minxc.emp.bpm.api.constant.ExtractType;
import org.minxc.emp.bpm.api.engine.constant.LogicType;

public interface BpmnUserCalculationPluginDef extends BpmnTaskPluginDef {

    /**
     * 获取抽取类型。
     *
     * @return ExtractType
     */
    ExtractType getExtract();


    /**
     * 设置抽取类型。
     *
     * @param type void
     */
    void setExtract(ExtractType type);

    /**
     * 逻辑类型。
     *
     * @return LogicType
     */
    LogicType getLogicCal();

    /**
     * 设置逻辑类型
     *
     * @param logicType void
     */
    void setLogicCal(LogicType logicType);


}
