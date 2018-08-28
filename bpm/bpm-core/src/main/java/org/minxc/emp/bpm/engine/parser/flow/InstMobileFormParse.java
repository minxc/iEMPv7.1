package org.minxc.emp.bpm.engine.parser.flow;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmDef;
import org.minxc.emp.bpm.api.model.form.BpmForm;
import org.minxc.emp.bpm.api.model.form.DefaultForm;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.minxc.emp.bpm.engine.parser.flow.AbsFlowParse;
import org.springframework.stereotype.Component;

@Component
public class InstMobileFormParse extends AbsFlowParse<DefaultForm> {
	public String getKey() {
		return "instMobileForm";
	}

	public void setDefParam(DefaultBpmProcessDef bpmProcessDef, Object object) {
		DefaultForm form = (DefaultForm) object;
		bpmProcessDef.setInstMobileForm((BpmForm) form);
	}

}