package org.minxc.emp.system.api.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.minxc.emp.system.api.model.ISubsystem;
import org.minxc.emp.system.api.model.ISysResource;


public interface SysResourceService {

	List<ISubsystem> getCuurentUserSystem();

	ISubsystem getDefaultSystem(String currentUserId);

	List<ISysResource> getBySystemId(String systemId);

	List<ISysResource> getBySystemAndUser(String systemId, String userId);

	Map<String, Set<String>> getUrlRoleBySystem(String systemId);

}
