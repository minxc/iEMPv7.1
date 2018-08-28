package com.dstz.bus.model;

import com.dstz.base.api.model.IBaseModel;
import com.dstz.base.db.model.table.Column;
import com.dstz.base.db.model.table.Table;
import com.dstz.bus.api.constant.BusColumnCtrlType;
import com.dstz.bus.api.model.IBusinessColumn;
import com.dstz.bus.api.model.IBusinessTable;
import com.dstz.bus.model.BusColumnCtrl;
import com.dstz.bus.model.BusinessColumn;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.validator.constraints.NotEmpty;

public class BusinessTable extends Table<BusinessColumn> implements IBaseModel, IBusinessTable {
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 2712532453545121605L;
	
	@NotEmpty
	private String id;
	@NotEmpty
	private String key;
	@NotEmpty
	private String dsKey;
	@NotEmpty
	private String dsName;
	private String groupId;
	private String groupName;
	private boolean external;
	private boolean createdTable;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	/**
	 * <pre>
	 * 业务表的别名
	 * </pre>
	 * 
	 * @return
	 */
	@Override
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
	/**
	 * <pre>
	 * 数据源别名
	 * </pre>
	 * 
	 * @return
	 */
	
	@Override
	public String getDsKey() {
		return this.dsKey;
	}

	public void setDsKey(String dsKey) {
		this.dsKey = dsKey;
	}
	
	
	/**
	 * <pre>
	 * 数据源名称
	 * </pre>
	 * @return
	 */
	@Override
	public String getDsName() {
		return this.dsName;
	}

	public void setDsName(String dsName) {
		this.dsName = dsName;
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

	public boolean isExternal() {
		return this.external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

	public String getPkName() {
		if (this.getPkColumn() == null) {
			return "";
		}
		return this.getPkColumn().getName();
	}

	public String getPkKey() {
		if (this.getPkColumn() == null) {
			return "";
		}
		return  this.getPkColumn().getKey();
	}

	public void setColumns(List<BusinessColumn> columns) {
		super.setColumns(columns);
	}
	
	
	/**
	 * <pre>
	 * 字段
	 * </pre>
	 * 
	 * @return
	 */
	@Override
	public List<BusinessColumn> getColumns() {
		return super.getColumns();
	}

	public boolean getCreatedTable() {
		return this.createdTable;
	}

	public void setCreatedTable(boolean createdTable) {
		this.createdTable = createdTable;
	}

	/**
	 * <pre>
	 * 获取不包含主键的字段
	 * </pre>
	 * @return
	 */
	@Override
	public List<BusinessColumn> getColumnsWithoutPk() {
		if (this.columns == null) {
			return null;
		}
		List<BusinessColumn> columnList = new ArrayList<BusinessColumn>();
		for (BusinessColumn column : this.columns) {
			if (column.isPrimary())
				continue;
			columnList.add(column);
		}
		return columnList;
	}

	public List<BusinessColumn> getColumnsWithOutHidden() {
		if (this.columns == null) {
			return null;
		}
		ArrayList<BusinessColumn> columnList = new ArrayList<BusinessColumn>();
		for (BusinessColumn column : this.columns) {
			if (column.isPrimary() || column.getCtrl() == null
					|| BusColumnCtrlType.HIDDEN.getKey().equals(column.getCtrl().getType()))
				continue;
			columnList.add(column);
		}
		return columnList;
	}

	
	/**
	 * <pre>
	 * 获取表的初始化数据库的数据
	 * 不包含主键
	 * 字段取的是name
	 * 其实就是获取字段的默认值
	 * </pre>	
	 * @return
	 */
	@Override
	public Map<String, Object> initDbData() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (IBusinessColumn column : this.getColumnsWithoutPk()) {
			map.put(column.getName(), column.initValue());
		}
		return map;
	}
	
	/**
	 * <pre>
	 * 获取表的初始化数据
	 * 不包含主键
	 * 字段取的是key
	 * 其实就是获取字段的默认值
	 * </pre>	
	 * @return
	 */
	
	@Override
	public Map<String, Object> initData() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (IBusinessColumn column : this.getColumnsWithoutPk()) {
			map.put(column.getKey(), column.initValue());
		}
		return map;
	}


	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date createTime) {
	}

	public String getCreateBy() {
		return null;
	}

	public void setCreateBy(String createBy) {
	}

	public Date getUpdateTime() {
		return null;
	}

	public void setUpdateTime(Date updateTime) {
	}

	public String getUpdateBy() {
		return null;
	}

	public void setUpdateBy(String updateBy) {
	}

	/**
	 * <pre>
	 * 根据字段key获取字段
	 * </pre>	
	 * @param key
	 * @return
	 */
	@Override
	public IBusinessColumn getColumnByKey(String key) {
		for (IBusinessColumn column : this.columns) {
			if (!key.equals(column.getKey()))
				continue;
			return column;
		}
		return null;
	}
}