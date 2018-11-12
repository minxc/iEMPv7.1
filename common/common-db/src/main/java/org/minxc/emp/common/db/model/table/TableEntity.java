package org.minxc.emp.common.db.model.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 *  对应数据库的表对象
 *
 *         字段类型
 */
public class TableEntity<C extends ColumnEntity> implements Serializable {
	/**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 * 
	 */
	private static final long serialVersionUID = -6709530989653019184L;

	/**
	 * 表名
	 */
	@NotEmpty
	protected String name;
	/**
	 * 描述
	 */
	protected String comment;
	/**
	 * 字段
	 */
	@Valid
	protected List<C> columns;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<C> getColumns() {
		return columns;
	}

	public void setColumns(List<C> columns) {
		this.columns = columns;
	}

	/**
	 * 
	 * 系统只支持唯一主键的场景
	 * 
	 *
	 * @return
	 */
	public C getPkColumn() {
		if (this.columns == null) {
			return null;
		}
		List<C> list = new ArrayList<>();
		for (C c : this.getColumns()) {
			if (c.isPrimary()) {
				list.add(c);
			}
		}
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 
	 * 根据name获取字段
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public C getColumn(String name) {
		if (this.columns == null) {
			return null;
		}
		for (C c : columns) {
			if (name.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}
}
