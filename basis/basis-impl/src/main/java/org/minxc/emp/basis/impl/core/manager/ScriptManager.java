package org.minxc.emp.basis.impl.core.manager;

import java.util.List;

import org.minxc.emp.basis.impl.core.model.Script;
import org.minxc.emp.common.manager.Manager;

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
