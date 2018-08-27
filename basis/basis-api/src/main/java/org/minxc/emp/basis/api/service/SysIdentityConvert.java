package org.minxc.emp.basis.api.service;

import java.util.List;

import org.minxc.emp.basis.api.model.SysIdentity;

import com.dstz.org.api.model.IUser;

public interface SysIdentityConvert {
	
	/**
	 *  identity type 必须为 user
	 * @param identity
	 * @return
	 */
	public IUser convert2User(SysIdentity identity);
	
	public List<IUser> convert2Users(SysIdentity identity);
	
	public List<IUser> convert2Users(List<SysIdentity> identity);
	
}
