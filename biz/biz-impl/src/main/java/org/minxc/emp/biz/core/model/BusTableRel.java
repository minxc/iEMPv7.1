package org.minxc.emp.biz.core.model;

import com.dstz.base.core.util.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minxc.emp.biz.core.api.model.IBusTableRel;
import org.minxc.emp.biz.core.api.model.IBusinessObject;
import org.minxc.emp.biz.core.api.model.IBusinessTable;
import org.minxc.emp.biz.core.model.BusTableRelFk;

public class BusTableRel implements IBusTableRel, Serializable {
	
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -9105853169718466190L;
	
	
	private List<IBusTableRel> children;
	private String tableKey;
	private String tableComment;
	private String type;
	private List<BusTableRelFk> fks;
	private IBusinessTable table;
	private IBusTableRel parent;
	private IBusinessObject busObj;

	
	/**
	 * <pre>
	 * 子级
	 * </pre>
	 * 
	 * @return
	 */
	@Override
	public List<IBusTableRel> getChildren() {
		if (this.children == null) {
			return Collections.emptyList();
		}
		return this.children;
	}

	public List<IBusTableRel> getChildren(String type) {
		List<IBusTableRel> list = new ArrayList<IBusTableRel>();
		if (BeanUtils.isNotEmpty(this.children)) {
			for (IBusTableRel rel : this.children) {
				if (!type.equals(rel.getType()))
					continue;
				list.add(rel);
			}
		}
		return list;
	}

	public void setChildren(List<IBusTableRel> children) {
		this.children = children;
	}

	/**
	 * <pre>
	 * 业务表的key
	 * </pre>
	 * 
	 * @return
	 */
	@Override
	public String getTableKey() {
		return this.tableKey;
	}

	public void setTableKey(String tableKey) {
		this.tableKey = tableKey;
	}

	/**
	 * <pre>
	 * 业务表的描述
	 * </pre>
	 * 
	 * @return
	 */
	@Override
	public String getTableComment() {
		return this.tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	/**
	 * <pre>
	 * 类型 枚举 BusTableRelType
	 * </pre>
	 * 
	 * @return
	 */
	@Override
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * <pre>
	 * 外键设置
	 * </pre>
	 * 
	 * @return
	 */
	@Override
	public List<BusTableRelFk> getFks() {
		return this.fks;
	}

	public void setFks(List<BusTableRelFk> fks) {
		this.fks = fks;
	}

	/**
	 * <pre>
	 * 获取busTableRel的list模式
	 * 包含根节点
	 * </pre>
	 * 
	 * @return
	 */
	@Override
	public List<IBusTableRel> list() {
		ArrayList<IBusTableRel> rels = new ArrayList<IBusTableRel>();
		rels.add(this);
		if (this.children != null) {
			for (IBusTableRel rel : this.children) {
				rels.addAll(rel.list());
			}
		}
		return rels;
	}

	@Override
	public IBusinessTable getTable() {
		return this.table;
	}

	public void setTable(IBusinessTable table) {
		this.table = table;
	}

	public IBusTableRel getParent() {
		return this.parent;
	}

	public void setParent(IBusTableRel parent) {
		this.parent = parent;
	}

	public IBusinessObject getBusObj() {
		return this.busObj;
	}

	public void setBusObj(IBusinessObject busObj) {
		this.busObj = busObj;
	}

	
	/**
	 * <pre>
	 * 以当前为根节点，递归获取指定tableKey
	 * </pre>
	 * 
	 * @param tableKey
	 * @return
	 */
	@Override
	public IBusTableRel find(String tableKey) {
		if (this.tableKey.equals(tableKey)) {
			return this;
		}
		if (this.children != null) {
			for (IBusTableRel rel : this.children) {
				IBusTableRel r = rel.find(tableKey);
				if (r == null)
					continue;
				return r;
			}
		}
		return null;
	}
}