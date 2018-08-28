package org.minxc.emp.bpm.core.manager.impl;

import com.dstz.base.manager.impl.BaseManager;

import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.bpm.core.dao.BpmBusLinkDao;
import org.minxc.emp.bpm.core.manager.BpmBusLinkManager;
import org.minxc.emp.bpm.core.model.BpmBusLink;
import org.springframework.stereotype.Service;

@Service(value = "bpmBusLinkManager")
public class BpmBusLinkManagerImpl extends BaseManager<String, BpmBusLink> implements BpmBusLinkManager {
	@Resource
	private BpmBusLinkDao bpmBusLinkDao;

	public List<BpmBusLink> getByInstanceId(String instanceId) {
		return this.bpmBusLinkDao.getByInstanceId(instanceId);
	}
}