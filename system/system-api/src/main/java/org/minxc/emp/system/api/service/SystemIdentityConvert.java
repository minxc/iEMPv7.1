package org.minxc.emp.system.api.service;

import java.util.List;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.idm.api.model.User;


public interface SystemIdentityConvert {
	
	
	/**
	 *  identity type 必须为 user
	 */
	public User convert2User(SystemIdentity identity);
	
	public List<User> convert2Users(SystemIdentity identity);
	
	public List<User> convert2Users(List<SystemIdentity> identity);
	
}
