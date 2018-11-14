package org.minxc.emp.biz.core.model;

import javax.validation.constraints.NotEmpty;

import org.minxc.emp.biz.api.constant.RightsType;
import org.minxc.emp.biz.api.model.BusinessColumn;
import org.minxc.emp.biz.api.model.BusinessObject;
import org.minxc.emp.biz.api.model.permission.BusinessObjectPermission;
import org.minxc.emp.biz.core.model.permission.BusinessColumnPermissionImpl;
import org.minxc.emp.biz.core.model.permission.BusinessObjectPermissionImpl;
import org.minxc.emp.biz.core.model.permission.BusinessTablePermissionImpl;
import org.minxc.emp.core.impl.model.AbstractCommonModel;
import org.minxc.emp.core.util.JsonUtil;

public class BusinessObjectImpl extends AbstractCommonModel implements BusinessObject {
	
	
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
	private BusinessTableRelationImpl relation;
	private BusinessObjectPermission permission;

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
		this.relation = (BusinessTableRelationImpl) JsonUtil.parseObject(relationJson, BusinessTableRelationImpl.class);
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

	public BusinessTableRelationImpl getRelation() {
		return this.relation;
	}

	public void setRelation(BusinessTableRelationImpl relation) {
		this.relation = relation;
		this.relationJson = JsonUtil.toJSONString(relation);
	}

	public BusinessObjectPermission getPermission() {
		return this.permission;
	}

	public void setPermission(BusinessObjectPermission permission) {
		this.permission = (BusinessObjectPermission) permission;
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
		BusinessColumnPermissionImpl columnPermission;
		BusinessTablePermissionImpl tablePermission;
		RightsType rightsType = null;
		if (this.permission != null && (tablePermission = (BusinessTablePermissionImpl) this.permission.getTableMap().get(tableKey)) != null
				&& (columnPermission = (BusinessColumnPermissionImpl) tablePermission.getColumnMap().get(columnKey)) != null) {
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
		BusinessTablePermissionImpl tablePermission;
		if (this.permission != null && (tablePermission = (BusinessTablePermissionImpl) this.permission.getTableMap().get(tableKey)) != null) {
			for (BusinessColumn column : this.relation.find(tableKey).getTable().getColumnsWithoutPk()) {
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