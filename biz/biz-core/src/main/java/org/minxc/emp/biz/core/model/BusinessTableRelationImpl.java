package org.minxc.emp.biz.core.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minxc.emp.biz.api.model.BusinessTableRelation;
import org.minxc.emp.biz.api.model.BusinessObject;
import org.minxc.emp.biz.api.model.BusinessTable;
import org.minxc.emp.core.util.BeanUtils;

public class BusinessTableRelationImpl implements BusinessTableRelation, Serializable {
	
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -9105853169718466190L;
	
	
	private List<BusinessTableRelation> children;
	private String tableKey;
	private String tableComment;
	private String type;
	private List<BusinessTableRelationForeignKeyImpl> fks;
	private BusinessTable table;
	private BusinessTableRelation parent;
	private BusinessObject busObj;

	
	/**
	 * 
	 * 子级
	 * 
	 * 
	 * @return
	 */
	@Override
	public List<BusinessTableRelation> getChildren() {
		if (this.children == null) {
			return Collections.emptyList();
		}
		return this.children;
	}

	public List<BusinessTableRelation> getChildren(String type) {
		List<BusinessTableRelation> list = new ArrayList<BusinessTableRelation>();
		if (BeanUtils.isNotEmpty(this.children)) {
			for (BusinessTableRelation rel : this.children) {
				if (!type.equals(rel.getType()))
					continue;
				list.add(rel);
			}
		}
		return list;
	}

	public void setChildren(List<BusinessTableRelation> children) {
		this.children = children;
	}

	/**
	 * 
	 * 业务表的key
	 * 
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
	 * 
	 * 业务表的描述
	 * 
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
	 * 
	 * 类型 枚举 BusinessTableRelationType
	 * 
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
	 * 
	 * 外键设置
	 * 
	 * 
	 * @return
	 */
	@Override
	public List<BusinessTableRelationForeignKeyImpl> getFks() {
		return this.fks;
	}

	public void setFks(List<BusinessTableRelationForeignKeyImpl> fks) {
		this.fks = fks;
	}

	/**
	 * 
	 * 获取busTableRel的list模式
	 * 包含根节点
	 * 
	 * 
	 * @return
	 */
	@Override
	public List<BusinessTableRelation> list() {
		ArrayList<BusinessTableRelation> rels = new ArrayList<BusinessTableRelation>();
		rels.add(this);
		if (this.children != null) {
			for (BusinessTableRelation rel : this.children) {
				rels.addAll(rel.list());
			}
		}
		return rels;
	}

	@Override
	public BusinessTable getTable() {
		return this.table;
	}

	public void setTable(BusinessTable table) {
		this.table = table;
	}

	public BusinessTableRelation getParent() {
		return this.parent;
	}

	public void setParent(BusinessTableRelation parent) {
		this.parent = parent;
	}

	public BusinessObject getBusObj() {
		return this.busObj;
	}

	public void setBusObj(BusinessObject busObj) {
		this.busObj = busObj;
	}

	
	/**
	 * 
	 * 以当前为根节点，递归获取指定tableKey
	 * 
	 * 
	 * @param tableKey
	 * @return
	 */
	@Override
	public BusinessTableRelation find(String tableKey) {
		if (this.tableKey.equals(tableKey)) {
			return this;
		}
		if (this.children != null) {
			for (BusinessTableRelation rel : this.children) {
				BusinessTableRelation r = rel.find(tableKey);
				if (r == null)
					continue;
				return r;
			}
		}
		return null;
	}
}