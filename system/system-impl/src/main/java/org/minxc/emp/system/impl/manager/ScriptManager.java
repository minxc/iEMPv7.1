package org.minxc.emp.system.impl.manager;

import java.util.List;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.Script;

public interface ScriptManager extends Manager<String, Script> {

	/**
	 * 返回所有脚本的分类
	 *
	 * @return
	 */
	public List<String> getDistinctCategory();

	/**
	 * 判断脚本名称是否存在
	 *
	 * @param name
	 * @return
	 */
	public Integer isNameExists(String name);
}
