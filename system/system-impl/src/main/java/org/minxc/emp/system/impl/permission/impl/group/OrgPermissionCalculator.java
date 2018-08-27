package org.minxc.emp.system.impl.permission.impl.group;

import org.minxc.emp.system.impl.permission.impl.GroupPermissionCalculator;
import org.springframework.stereotype.Service;

/**
 * 描述：组织
 */
@Service
public class OrgPermissionCalculator extends GroupPermissionCalculator {
	
	@Override
	public String getType() {
		return "org";
	}

	@Override
	public String getTitle() {
		return "组织";
	}
	
}
