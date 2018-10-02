package org.minxc.emp.bpm.core.manager.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.core.dao.BpmInstanceDao;
import org.minxc.emp.bpm.core.manager.BpmInstanceManager;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.core.model.BpmTaskApprove;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Service;

@Service(value = "bpmInstanceManager")
public class BpmInstanceManagerImpl extends CommonManager<String, BpmInstance> implements BpmInstanceManager {
	@Resource
	BpmInstanceDao g;

	public boolean isSuspendByInstId(String instId) {
		return false;
	}

	public List<BpmInstance> getApplyList(String userId, QueryFilter queryFilter) {
		queryFilter.addParamsFilter("userId", (Object) userId);
		return this.g.getApplyList(queryFilter);
	}

	public List<BpmTaskApprove> getApproveHistoryList(String userId, QueryFilter queryFilter) {
		queryFilter.addParamsFilter("approver", (Object) userId);
		return this.g.getApproveHistoryList(queryFilter);
	}

	public BpmInstance getTopInstance(BpmInstance instance) {
		if (instance == null || StringUtil.isZeroEmpty((String) instance.getParentInstId())) {
			return null;
		}
		BpmInstance parentInstance = (BpmInstance) this.get(instance.getParentInstId());
		BpmInstance topInstance = this.getTopInstance(parentInstance);
		if (topInstance != null) {
			return topInstance;
		}
		return parentInstance;
	}

	public BpmInstance genInstanceByDefinition(IBpmDefinition bpmDefinition) {
		BpmInstance instance = new BpmInstance();
		instance.setId(UniqueIdUtil.getSuid());
		instance.setSubject(bpmDefinition.getName());
		instance.setDefId(bpmDefinition.getId());
		instance.setTypeId(bpmDefinition.getTypeId());
		instance.setDefKey(bpmDefinition.getKey());
		instance.setActDefId(bpmDefinition.getActDefId());
		instance.setDefName(bpmDefinition.getName());
		User user = ContextUtil.getCurrentUser();
		instance.setCreateBy(user.getUserId());
		instance.setCreator(user.getFullname());
		instance.setCreateTime(new Date());
		instance.setSupportMobile(bpmDefinition.getSupportMobile());
		instance.setParentInstId("0");
		if (bpmDefinition.getStatus().equals("deploy")) {
			instance.setIsFormmal("Y");
		} else if (bpmDefinition.getStatus().equals("draft")) {
			instance.setIsFormmal("N");
		}
		instance.setHasCreate(Boolean.valueOf(false));
		return instance;
	}
}