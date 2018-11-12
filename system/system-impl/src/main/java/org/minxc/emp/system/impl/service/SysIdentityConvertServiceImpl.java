package org.minxc.emp.system.impl.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.service.GroupService;
import org.minxc.emp.idm.api.service.UserService;
import org.minxc.emp.system.api.service.SystemIdentityConvert;
import org.springframework.stereotype.Component;


@Component
public class SysIdentityConvertServiceImpl implements SystemIdentityConvert {
	
	@Resource
	GroupService groupService;
	
	@Resource
	UserService userService;
	
	@Override
	public User convert2User(SystemIdentity identity) {
		List<User> users = convert2Users(identity);
		
		if(BeanUtils.isNotEmpty(users)) {
			return users.get(0);
		}
		
		return null;
		
	}

	@Override
	public List<User> convert2Users(SystemIdentity identity) {
		//如果为用户
		if(SystemIdentity.TYPE_USER.equals(identity.getType())) {
			List<User> users = new ArrayList<>();
			
			users.add(userService.getUserById(identity.getId()));
			return users;
		}
		//目前其他均为组类型
		List<User> userList = userService.getUserListByGroup(identity.getType(), identity.getId());
		
		return userList;
	}

	@Override
	public List<User> convert2Users(List<SystemIdentity> identitys) {
		List<User> users = new ArrayList<>();
		
		for(SystemIdentity identity : identitys) {
			users.addAll(convert2Users(identity));
		}
		
		return users;
	}

}
