package org.minxc.emp.bpm.engine.parser.flow;

import org.minxc.emp.bpm.api.model.form.BpmForm;
import org.minxc.emp.bpm.api.model.form.DefaultForm;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.springframework.stereotype.Component;

@Component
public class GlobalMobileFormParse extends AbsFlowParse<DefaultForm> {
	public String getKey() {
		return "globalMobileForm";
	}

	public void setDefParam(DefaultBpmnProcessDefinition bpmProcessDef, Object object) {
		DefaultForm form = (DefaultForm) object;
		bpmProcessDef.setGlobalMobileForm((BpmForm) form);
	}

}