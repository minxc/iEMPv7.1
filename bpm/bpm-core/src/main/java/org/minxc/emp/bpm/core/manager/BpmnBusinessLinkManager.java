package org.minxc.emp.bpm.core.manager;


import java.util.List;

import org.minxc.emp.bpm.core.model.BpmnBusinessLink;
import org.minxc.emp.common.manager.Manager;

public interface BpmnBusinessLinkManager extends Manager<String, BpmnBusinessLink> {
	public List<BpmnBusinessLink> getByInstanceId(String instanceId);
}