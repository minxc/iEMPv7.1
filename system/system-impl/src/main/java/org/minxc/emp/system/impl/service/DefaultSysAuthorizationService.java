package org.minxc.emp.system.impl.service;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.constant.RightsObjectConstants;
import org.minxc.emp.system.api.service.SysAuthorizationService;
import org.minxc.emp.system.impl.manager.SystemAuthorizationManager;
import org.springframework.stereotype.Service;

@Service
public class DefaultSysAuthorizationService implements SysAuthorizationService {
	@Resource
	SystemAuthorizationManager sysAuthorizationManager;

	@Override
	public Set<String> getUserRights(String userId) {
		return sysAuthorizationManager.getUserRights(userId);
	}

	@Override
	public Map<String, Object> getUserRightsSql(RightsObjectConstants rightsObject, String userId, String targetKey) {
		return sysAuthorizationManager.getUserRightsSql(rightsObject, userId, targetKey);
	}

}
