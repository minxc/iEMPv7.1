package org.minxc.emp.basis.impl.groovy;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.IScript;
import org.minxc.emp.basis.api.service.SerialNoService;
import org.minxc.emp.basis.impl.util.ContextUtil;
import org.minxc.emp.idm.api.model.User;
import org.springframework.stereotype.Component;


/**
 * 系统脚本
 * 常用的系统功能的脚本
 */
@Component
public class SysScript implements IScript {
	@Resource
	SerialNoService serialNoService;
	
	/**
	 * 获取系统流水号
	 * @param alias
	 * @return
	 */
	public String getNextSerialNo(String alias) {
		return serialNoService.genNextNo(alias);
	}
	
	
	public User getCuurentUser() {
		return ContextUtil.getCurrentUser();
	}
}
