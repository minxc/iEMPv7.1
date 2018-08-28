package org.minxc.emp.bpm.core.manager;

import com.dstz.base.manager.Manager;

import java.util.List;

import org.minxc.emp.bpm.core.model.BpmBusLink;

public interface BpmBusLinkManager extends Manager<String, BpmBusLink> {
	public List<BpmBusLink> getByInstanceId(String instanceId);
}