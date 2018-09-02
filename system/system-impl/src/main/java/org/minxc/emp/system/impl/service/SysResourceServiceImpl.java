package org.minxc.emp.system.impl.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.minxc.emp.system.api.model.ISubsystem;
import org.minxc.emp.system.api.model.ISysResource;
import org.minxc.emp.system.api.service.SysResourceService;
import org.minxc.emp.system.impl.manager.ResRoleManager;
import org.minxc.emp.system.impl.manager.SubsystemManager;
import org.minxc.emp.system.impl.manager.SysResourceManager;
import org.springframework.stereotype.Service;

/**
 * 用户系统资源服务接口
 */
@Service
public class SysResourceServiceImpl implements SysResourceService{
	@Resource
	SysResourceManager sysResourceManager;
	@Resource
	SubsystemManager sybSystemManager;
	@Resource
	ResRoleManager resRoleManager;
	
	
	@Override
	public List<ISubsystem> getCuurentUserSystem() {
		return (List)sybSystemManager.getCuurentUserSystem();
	}

	@Override
	public ISubsystem getDefaultSystem(String currentUserId) {
		return sybSystemManager.getDefaultSystem(currentUserId);
	}

	@Override
	public List<ISysResource> getBySystemId(String systemId) {
		return (List)sysResourceManager.getBySystemId(systemId);
	}

	@Override
	public List<ISysResource> getBySystemAndUser(String systemId, String userId) {
		return (List)sysResourceManager.getBySystemAndUser(systemId, userId);
	}

	@Override
	public Map<String, Set<String>> getUrlRoleBySystem(String systemId) {
		return resRoleManager.getUrlRoleBySystem(systemId);
	}

}
