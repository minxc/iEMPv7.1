package com.dstz.sys2.permission.impl.group;

import org.springframework.stereotype.Service;

import com.dstz.sys2.permission.impl.GroupPermissionCalculator;
/**
 * <pre>
 * 描述：角色
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018年7月12日
 * 版权:summer
 * </pre>
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
