package org.minxc.emp.system.impl.permission.impl.group;

import org.minxc.emp.system.impl.permission.impl.GroupPermissionCalculator;
import org.springframework.stereotype.Service;

/**
 * 描述：岗位
 */
@Service
public class PostPermissionCalculator extends GroupPermissionCalculator {
	
	@Override
	public String getType() {
		return "post";
	}

	@Override
	public String getTitle() {
		return "岗位";
	}
}
