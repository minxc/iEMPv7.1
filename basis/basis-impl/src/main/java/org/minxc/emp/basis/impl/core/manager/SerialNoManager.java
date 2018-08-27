package org.minxc.emp.basis.impl.core.manager;

import java.util.List;

import org.minxc.emp.basis.impl.core.model.SerialNo;
import org.minxc.emp.common.manager.Manager;

public interface SerialNoManager extends Manager<String, SerialNo> {
	/**
	 * 判读流水号别名是否已经存在
	 *
	 * @param id    id为null 表明是新增的流水号，否则为更新流水号
	 * @param alias
	 * @return
	 */
	boolean isAliasExisted(String id, String alias);

	/**
	 * 根据别名获取当前流水号
	 *
	 * @param alias
	 * @return
	 */
	public String getCurIdByAlias(String alias);

	/**
	 * 根据别名获取下一个流水号
	 *
	 * @param alias
	 * @return
	 */
	public String nextId(String alias);

	/**
	 * 根据别名预览前十条流水号
	 *
	 * @param alias
	 * @return
	 */
	public List<SerialNo> getPreviewIden(String alias);

}
