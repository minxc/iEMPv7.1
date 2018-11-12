package org.minxc.emp.system.impl.permission.impl.group;

import org.minxc.emp.system.impl.permission.impl.GroupPermissionCalculator;
import org.springframework.stereotype.Service;

/**
 * 角色
 */
@Service
public class RolePermissionCalculator extends GroupPermissionCalculator {
	
	@Override
	public String getType() {
		return "role";
	}

	@Override
	public String getTitle() {
		return "角色";
	}
}
