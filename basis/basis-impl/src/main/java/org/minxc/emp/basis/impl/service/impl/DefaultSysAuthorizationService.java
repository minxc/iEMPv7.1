package org.minxc.emp.basis.impl.service.impl;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.constant.RightsObjectConstants;
import org.minxc.emp.basis.api.service.SysAuthorizationService;
import org.minxc.emp.basis.impl.core.manager.SysAuthorizationManager;
import org.springframework.stereotype.Service;

@Service
public class DefaultSysAuthorizationService implements SysAuthorizationService {
	@Resource
	SysAuthorizationManager sysAuthorizationManager;

	@Override
	public Set<String> getUserRights(String userId) {
		return sysAuthorizationManager.getUserRights(userId);
	}

	@Override
	public Map<String, Object> getUserRightsSql(RightsObjectConstants rightsObject, String userId, String targetKey) {
		return sysAuthorizationManager.getUserRightsSql(rightsObject, userId, targetKey);
	}

}
