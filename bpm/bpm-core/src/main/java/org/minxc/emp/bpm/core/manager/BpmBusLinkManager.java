package org.minxc.emp.bpm.core.manager;


import java.util.List;

import org.minxc.emp.bpm.core.model.BpmBusLink;
import org.minxc.emp.common.manager.Manager;

public interface BpmBusLinkManager extends Manager<String, BpmBusLink> {
	public List<BpmBusLink> getByInstanceId(String instanceId);
}