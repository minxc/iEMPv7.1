package org.minxc.emp.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.minxc.emp.form.api.model.FormDef;
import org.minxc.emp.form.api.service.FormDefService;
import org.minxc.emp.form.manager.FormDefManager;

@Service
public class FormDefServiceImpl implements FormDefService {
	@Autowired
	FormDefManager formDefManager;

	@Override
	public FormDef getByKey(String key) {
		return formDefManager.getByKey(key);
	}
}
