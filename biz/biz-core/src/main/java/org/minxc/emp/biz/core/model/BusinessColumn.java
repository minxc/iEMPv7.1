package org.minxc.emp.biz.core.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.biz.api.model.IBusinessColumn;
import org.minxc.emp.biz.core.model.BusColumnCtrl;
import org.minxc.emp.biz.core.model.BusinessTable;
import org.minxc.emp.common.db.model.table.ColumnEntity;
import org.minxc.emp.core.api.constant.ColumnType;
import org.minxc.emp.core.api.model.CommonModel;
import org.minxc.emp.core.util.time.DateFormatUtil;

public class BusinessColumn extends ColumnEntity implements CommonModel, IBusinessColumn {
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -2911889790695856709L;
	
	
	@NotEmpty
	private String id;
	@NotEmpty
	private String key;
	@NotEmpty
	private String tableId;
	@Valid
	private BusColumnCtrl ctrl;
	private BusinessTable table;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public BusColumnCtrl getCtrl() {
		return this.ctrl;
	}

	public void setCtrl(BusColumnCtrl ctrl) {
		this.ctrl = ctrl;
	}

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public BusinessTable getTable() {
		return this.table;
	}

	public void setTable(BusinessTable table) {
		this.table = table;
	}

	public Object initValue() {
		return this.value(this.defaultValue);
	}

	public Object value(String str) {
		if (StringUtils.isEmpty((String) str)) {
			return null;
		}
		Object value = null;
		if (ColumnType.VARCHAR.equalsWithKey(this.type) || ColumnType.CLOB.equalsWithKey(this.type)) {
			value = str;
		} else if (ColumnType.NUMBER.equalsWithKey(this.type)) {
			BigDecimal bigDecimal = new BigDecimal(str);
			value = bigDecimal.setScale(this.getDecimal(), RoundingMode.HALF_UP);
		} else if (ColumnType.DATE.equalsWithKey(this.type)) {
			JSONObject config = JSON.parseObject((String) this.getCtrl().getConfig());
			value = DateFormatUtil.parse((String) str, (String) config.getString("format"));
		}
		return value;
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
}