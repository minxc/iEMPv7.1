package org.minxc.emp.basis.impl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.model.SysIdentity;
import org.minxc.emp.basis.api.service.SysIdentityConvert;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.service.GroupService;
import org.minxc.emp.idm.api.service.UserService;
import org.springframework.stereotype.Component;

import com.minxc.emp.core.util.BeanUtils;


@Component
public class SysIdentityConvertServiceImpl implements SysIdentityConvert {
	
	@Resource
	GroupService groupService;
	
	@Resource
	UserService userService;
	
	@Override
	public User convert2User(SysIdentity identity) {
		List<User> users = convert2Users(identity);
		
		if(BeanUtils.isNotEmpty(users)) {
			return users.get(0);
		}
		
		return null;
		
	}

	@Override
	public List<User> convert2Users(SysIdentity identity) {
		//如果为用户
		if(SysIdentity.TYPE_USER.equals(identity.getType())) {
			List<User> users = new ArrayList<>();
			
			users.add(userService.getUserById(identity.getId()));
			return users;
		}
		//目前其他均为组类型
		List<User> userList = userService.getUserListByGroup(identity.getType(), identity.getId());
		
		return userList;
	}

	@Override
	public List<User> convert2Users(List<SysIdentity> identitys) {
		List<User> users = new ArrayList<>();
		
		for(SysIdentity identity : identitys) {
			users.addAll(convert2Users(identity));
		}
		
		return users;
	}

}
