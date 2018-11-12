package org.minxc.emp.form.core.service;

import org.minxc.emp.form.api.model.FormDefinition;
import org.minxc.emp.form.api.service.FormDefinitionService;
import org.minxc.emp.form.core.manager.FormDefinitionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormDefinitionServiceImpl implements FormDefinitionService {
	@Autowired
	FormDefinitionManager formDefManager;

	@Override
	public FormDefinition getByKey(String key) {
		return formDefManager.getByKey(key);
	}
}
