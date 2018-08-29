package org.minxc.emp.bpm.core.manager.impl;


import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.bpm.core.dao.BpmBusLinkDao;
import org.minxc.emp.bpm.core.manager.BpmBusLinkManager;
import org.minxc.emp.bpm.core.model.BpmBusLink;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

@Service(value = "bpmBusLinkManager")
public class BpmBusLinkManagerImpl extends CommonManager<String, BpmBusLink> implements BpmBusLinkManager {
	@Resource
	private BpmBusLinkDao bpmBusLinkDao;

	public List<BpmBusLink> getByInstanceId(String instanceId) {
		return this.bpmBusLinkDao.getByInstanceId(instanceId);
	}
}