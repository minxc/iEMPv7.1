package org.minxc.emp.bpm.engine.plugin.plugindef;

import org.minxc.emp.bpm.api.constant.ExtractType;
import org.minxc.emp.bpm.api.engine.constant.LogicType;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;

public abstract class AbstractUserCalcPluginDef implements BpmUserCalcPluginDef {
	private ExtractType bJ = ExtractType.EXACT_NOEXACT;
	private LogicType bK = LogicType.OR;

	public ExtractType getExtract() {
		return this.bJ;
	}

	public void setExtract(ExtractType type) {
		this.bJ = type;
	}

	public LogicType getLogicCal() {
		return this.bK;
	}

	public void setLogicCal(LogicType logicType) {
		this.bK = logicType;
	}
}