package org.minxc.emp.system.impl.model;

import org.minxc.emp.core.impl.model.AbstractCommonModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
public class DataDictEntity extends AbstractCommonModel {
	
	private static final long serialVersionUID = 4603574478432363313L;
	
	public static final String TYPE_DICT = "dict";
	public static final String TYPE_NODE = "node";
	
	/**
	 * 上级id
	 */
	protected String parentId;
	/**
	 * key
	 */
	protected String key;
	/**
	 * name
	 */
	protected String name;
	/**
	 * 字典key
	 */
	protected String dictKey;
	/**
	 * 分组id
	 */
	protected String typeId;
	/**
	 * 排序
	 */
	protected Long seq = 0L;
	/**
	 * dict/node字典项
	 */
	protected String dictType;

	

}