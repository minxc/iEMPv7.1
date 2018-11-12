package org.minxc.emp.biz.core.model;

import javax.validation.constraints.NotEmpty;

import org.minxc.emp.biz.api.constant.RightsType;
import org.minxc.emp.biz.api.model.IBusinessColumn;
import org.minxc.emp.biz.api.model.IBusinessObject;
import org.minxc.emp.biz.api.model.permission.IBusObjPermission;
import org.minxc.emp.biz.core.model.BusTableRel;
import org.minxc.emp.biz.core.model.permission.BusColumnPermission;
import org.minxc.emp.biz.core.model.permission.BusObjPermission;
import org.minxc.emp.biz.core.model.permission.BusTablePermission;
import org.minxc.emp.core.impl.model.AbstractCommonModel;
import org.minxc.emp.core.util.JsonUtil;

public class BusinessObject extends AbstractCommonModel implements IBusinessObject {
	
	
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 1472903804869951422L;
	
	
	@NotEmpty
	private String key;
	@NotEmpty
	private String name;
	private String desc;
	@NotEmpty
	private String relationJson;
	private String groupId;
	private String groupName;
	@NotEmpty
	private String persistenceType;
	private BusTableRel relation;
	private BusObjPermission permission;

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRelationJson() {
		return this.relationJson;
	}

	public void setRelationJson(String relationJson) {
		this.relationJson = relationJson;
		this.relation = (BusTableRel) JsonUtil.parseObject(relationJson, BusTableRel.class);
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPersistenceType() {
		return this.persistenceType;
	}

	public void setPersistenceType(String persistenceType) {
		this.persistenceType = persistenceType;
	}

	public BusTableRel getRelation() {
		return this.relation;
	}

	public void setRelation(BusTableRel relation) {
		this.relation = relation;
		this.relationJson = JsonUtil.toJSONString(relation);
	}

	public BusObjPermission getPermission() {
		return this.permission;
	}

	public void setPermission(IBusObjPermission permission) {
		this.permission = (BusObjPermission) permission;
	}

	public boolean haveTableDbEditRights(String tableKey) {
		return this.a(true, tableKey);
	}

	public boolean haveTableDbReadRights(String tableKey) {
		return this.a(false, tableKey);
	}

	public boolean haveColumnDbEditRights(String tableKey, String columnKey) {
		return this.a(true, tableKey, columnKey);
	}

	public boolean haveColumnDbReadRights(String tableKey, String columnKey) {
		return this.a(false, tableKey, columnKey);
	}

	private boolean a(boolean isEdit, String tableKey, String columnKey) {
		BusColumnPermission columnPermission;
		BusTablePermission tablePermission;
		RightsType rightsType = null;
		if (this.permission != null && (tablePermission = (BusTablePermission) this.permission.getTableMap().get(tableKey)) != null
				&& (columnPermission = (BusColumnPermission) tablePermission.getColumnMap().get(columnKey)) != null) {
			rightsType = RightsType.getByKey((String) columnPermission.getResult());
		}
		if (rightsType == null) {
			rightsType = RightsType.getDefault();
		}
		if (isEdit) {
			return rightsType.isDbEditable();
		}
		return rightsType.isDbReadable();
	}

	private boolean a(boolean isEdit, String tableKey) {
		BusTablePermission tablePermission;
		if (this.permission != null && (tablePermission = (BusTablePermission) this.permission.getTableMap().get(tableKey)) != null) {
			for (IBusinessColumn column : this.relation.find(tableKey).getTable().getColumnsWithoutPk()) {
				if (isEdit && this.haveColumnDbEditRights(tableKey, column.getKey())) {
					return true;
				}
				if (isEdit || !this.haveColumnDbReadRights(tableKey, column.getKey()))
					continue;
				return true;
			}
			return false;
		}
		if (isEdit) {
			return RightsType.getDefault().isDbEditable();
		}
		return RightsType.getDefault().isDbReadable();
	}
}