package org.minxc.emp.basis.impl.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.system.ISubsystem;
import org.minxc.emp.basis.api.model.system.ISysResource;
import org.minxc.emp.basis.api.service.SysResourceService;
import org.minxc.emp.basis.impl.core.manager.ResRoleManager;
import org.minxc.emp.basis.impl.core.manager.SubsystemManager;
import org.minxc.emp.basis.impl.core.manager.SysResourceManager;
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
