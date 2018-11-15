package org.minxc.emp.bpm.core.manager.impl;


import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.bpm.core.dao.BpmnBusinessLinkDao;
import org.minxc.emp.bpm.core.manager.BpmnBusinessLinkManager;
import org.minxc.emp.bpm.core.model.BpmnBusinessLink;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

@Service(value = "bpmBusLinkManager")
public class BpmnBusinessLinkManagerImpl extends CommonManager<String, BpmnBusinessLink> implements BpmnBusinessLinkManager {
	@Resource
	private BpmnBusinessLinkDao bpmnBusinessLinkDao;

	public List<BpmnBusinessLink> getByInstanceId(String instanceId) {
		return this.bpmnBusinessLinkDao.getByInstanceId(instanceId);
	}
}