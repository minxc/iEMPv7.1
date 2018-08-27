package org.minxc.emp.basis.api.service;

import java.util.List;

import org.minxc.emp.basis.api.model.SysIdentity;
import org.minxc.emp.idm.api.model.User;


public interface SysIdentityConvert {
	
	
	/**
	 *  identity type 必须为 user
	 */
	public User convert2User(SysIdentity identity);
	
	public List<User> convert2Users(SysIdentity identity);
	
	public List<User> convert2Users(List<SysIdentity> identity);
	
}
