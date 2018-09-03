package org.minxc.emp.system.api.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.minxc.emp.system.api.model.Application;
import org.minxc.emp.system.api.model.SystemResource;


public interface SysResourceService {

	List<Application> getCurrentUserSystem();

	Application getDefaultSystem(String currentUserId);

	List<SystemResource> getBySystemId(String systemId);

	List<SystemResource> getBySystemAndUser(String systemId, String userId);

	Map<String, Set<String>> getUrlRoleBySystem(String systemId);

}
