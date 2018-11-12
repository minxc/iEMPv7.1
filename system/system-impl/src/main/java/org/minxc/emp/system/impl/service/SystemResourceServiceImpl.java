package org.minxc.emp.system.impl.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.minxc.emp.system.api.model.Application;
import org.minxc.emp.system.api.model.SystemResource;
import org.minxc.emp.system.api.service.SystemResourceService;
import org.minxc.emp.system.impl.manager.ResRoleManager;
import org.minxc.emp.system.impl.manager.SubsystemManager;
import org.minxc.emp.system.impl.manager.SystemResourceManager;
import org.springframework.stereotype.Service;

/**
 * 用户系统资源服务接口
 */
@Service
public class SystemResourceServiceImpl implements SystemResourceService{
	@Resource
	private SystemResourceManager sysResourceManager;
	@Resource
	private SubsystemManager sybSystemManager;
	@Resource
	private ResRoleManager resRoleManager;
	
	
	@Override
	public List<Application> getCurrentUserSystem() {
		return (List)sybSystemManager.getCurrentUserSystem();
	}

	@Override
	public Application getDefaultSystem(String currentUserId) {
		return sybSystemManager.getDefaultSystem(currentUserId);
	}

	@Override
	public List<SystemResource> getBySystemId(String systemId) {
		return (List)sysResourceManager.getBySystemId(systemId);
	}

	@Override
	public List<SystemResource> getBySystemAndUser(String systemId, String userId) {
		return (List)sysResourceManager.getBySystemAndUser(systemId, userId);
	}

	@Override
	public Map<String, Set<String>> getUrlRoleBySystem(String systemId) {
		return resRoleManager.getUrlRoleBySystem(systemId);
	}

}
