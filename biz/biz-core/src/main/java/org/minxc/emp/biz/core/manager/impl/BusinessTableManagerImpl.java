package org.minxc.emp.biz.core.manager.impl;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.biz.core.dao.BusinessTableDao;
import org.minxc.emp.biz.core.manager.BusColumnCtrlManager;
import org.minxc.emp.biz.core.manager.BusinessColumnManager;
import org.minxc.emp.biz.core.manager.BusinessTableManager;
import org.minxc.emp.biz.core.model.BusinessColumnControlImpl;
import org.minxc.emp.biz.core.model.BusinessColumnImpl;
import org.minxc.emp.biz.core.model.BusinessTableImpl;
import org.minxc.emp.biz.core.util.BusinessTableCacheUtil;
import org.minxc.emp.common.db.datasource.DbContextHolder;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.db.tableoper.TableOperator;
import org.minxc.emp.common.db.tableoper.TableOperatorFactory;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.system.api.service.SystemDataSourceService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BusinessTableManagerImpl extends CommonManager<String, BusinessTableImpl> implements BusinessTableManager {
	
	
	@Resource
	private BusinessTableDao businessTableDao;
	@Resource
	private BusinessColumnManager businessColumnManager;
	@Resource
	private BusColumnCtrlManager busColumnCtrlManager;
	@Resource
	private SystemDataSourceService sysDataSourceService;
	@Resource
	private JdbcTemplate jdbcTemplate;

	public void save(BusinessTableImpl businessTable) {
		if (StringUtils.isEmpty((String) businessTable.getId())) {
			businessTable.setId(UniqueIdUtil.getSuid());
			this.create(businessTable);
		} else {
			this.update(businessTable);
			this.busColumnCtrlManager.removeByTableId(businessTable.getId());
			this.businessColumnManager.removeByTableId(businessTable.getId());
		}
		for (BusinessColumnImpl businessColumn : businessTable.getColumns()) {
			if (StringUtils.isEmpty((String) businessColumn.getId())) {
				businessColumn.setId(UniqueIdUtil.getSuid());
			}
			businessColumn.setTable(businessTable);
			businessColumn.setTableId(businessTable.getId());
			this.businessColumnManager.create(businessColumn);
			BusinessColumnControlImpl ctrl = businessColumn.getCtrl();
			if (businessColumn.isPrimary())
				continue;
			if (StringUtils.isEmpty((String) ctrl.getId())) {
				ctrl.setId(UniqueIdUtil.getSuid());
			}
			ctrl.setColumnId(businessColumn.getId());
			this.busColumnCtrlManager.create(businessColumn.getCtrl());
		}
		this.newTableOperator(businessTable).syncColumn();
		BusinessTableCacheUtil.put((BusinessTableImpl) businessTable);
	}

	public BusinessTableImpl getByKey(String key) {
		DefaultQueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("key_", (Object) key, QueryOperator.EQUAL);
		return (BusinessTableImpl) this.queryOne((QueryFilter) filter);
	}

	

	public TableOperator newTableOperator(BusinessTableImpl businessTable) {
		JdbcTemplate dataSourceJdbcTemplate = this.sysDataSourceService.getJdbcTemplateByKey(businessTable.getDsKey());
		return TableOperatorFactory.newOperator( DbContextHolder.getDataSourceDbType(businessTable.getDsKey()), businessTable,
				 dataSourceJdbcTemplate);
	}

	public BusinessTableImpl getFilledByKey(String key) {
		BusinessTableImpl businessTable = BusinessTableCacheUtil.get(key);
		if (businessTable != null) {
			return businessTable;
		}
		businessTable = this.getByKey(key);
		this.a(businessTable);
		BusinessTableCacheUtil.put(businessTable);
		return businessTable;
	}

	
	private void a(BusinessTableImpl businessTable) {
		if (businessTable == null) {
			return;
		}
		List<BusinessColumnImpl> columns = this.businessColumnManager.getByTableId(businessTable.getId());
		for (BusinessColumnImpl column : columns) {
			column.setCtrl(this.busColumnCtrlManager.getByColumnId(column.getId()));
			column.setTable(businessTable);
		}
		businessTable.setColumns(columns);
		TableOperator tableOperator = this.newTableOperator(businessTable);
		businessTable.setCreatedTable(tableOperator.isTableCreated());
	}
	
	public void remove(String entityId) {
		BusinessTableImpl table = (BusinessTableImpl) this.get(entityId);
		if (table == null) {
			return;
		}
		List<String> boNames = this.jdbcTemplate.queryForList(
				"select name_ from bus_object where relation_json_ like  '%\"tableKey\":\"" + table.getKey() + "\"%'",
				String.class);
		if (BeanUtils.isNotEmpty((Object) boNames)) {
			throw new BusinessException("业务对象:" + boNames.toString() + "还在使用实体， 删除实体失败！");
		}
		super.remove(entityId);
	}
}