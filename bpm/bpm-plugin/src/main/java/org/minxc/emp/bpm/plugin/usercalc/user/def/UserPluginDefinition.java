package org.minxc.emp.bpm.plugin.usercalc.user.def;

import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractUserCalculatePluginDefinition;

public class UserPluginDefinition extends AbstractUserCalculatePluginDefinition {
	private String source = "";
	private String account = "";
	private String userName = "";

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}