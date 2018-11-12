package org.minxc.emp.form.core.service;

import org.minxc.emp.form.api.model.FormDefinition;
import org.minxc.emp.form.api.service.IFormDefService;
import org.minxc.emp.form.core.manager.FormDefManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormDefService implements IFormDefService {
	@Autowired
	FormDefManager formDefManager;

	@Override
	public FormDefinition getByKey(String key) {
		return formDefManager.getByKey(key);
	}
}
