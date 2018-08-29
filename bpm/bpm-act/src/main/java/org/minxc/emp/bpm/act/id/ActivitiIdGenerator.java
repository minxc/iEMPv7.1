package org.minxc.emp.bpm.act.id;

import com.dstz.base.db.api.IdGenerator;

public class ActivitiIdGenerator implements org.activiti.engine.impl.cfg.IdGenerator {
	private IdGenerator f = null;

	public void setIdGenerator(IdGenerator idGenerator) {
		this.f = idGenerator;
	}

	public String getNextId() {
		return this.f.getSuid();
	}
}