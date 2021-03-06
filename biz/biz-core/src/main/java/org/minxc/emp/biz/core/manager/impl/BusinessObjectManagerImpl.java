package org.minxc.emp.biz.core.manager.impl;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.biz.api.constant.BusinessTableRelationType;
import org.minxc.emp.biz.api.model.BusinessTableRelation;
import org.minxc.emp.biz.core.dao.BusinessObjectDao;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.manager.BusinessTableManager;
import org.minxc.emp.biz.core.model.BusinessTableRelationImpl;
import org.minxc.emp.biz.core.model.BusinessColumnImpl;
import org.minxc.emp.biz.core.model.BusinessObjectImpl;
import org.minxc.emp.biz.core.model.BusinessTableImpl;
import org.minxc.emp.biz.core.service.BusinessPermissionServiceImpl;
import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.util.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 
*    
* 项目名称：bus-core   
* 类名称：BusinessObjectManagerImpl   
* 类   TODO:BusinessObjectManagerImpl内部方法需要去猜测一下
* 创建人：Xianchang.min   
* 创建时间：2018年8月11日 下午5:11:05   
* 修改人：Xianchang.min   
* 修改时间：2018年8月11日 下午5:11:05   
* 修改备注：   
* @version  1.0  
*
 */
@Service
public class BusinessObjectManagerImpl extends CommonManager<String, BusinessObjectImpl> implements BusinessObjectManager {

	@Resource
	private BusinessObjectDao businessObjectDao;
	@Resource
	private BusinessTableManager businessTableManager;
	@Resource
	private BusinessPermissionServiceImpl businessPermissionService;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public BusinessObjectImpl getByKey(String key) {
		DefaultQueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("key_", key, QueryOperator.EQUAL);
		return (BusinessObjectImpl) this.queryOne(filter);
	}

	@Override
	public BusinessObjectImpl getFilledByKey(String key) {
		BusinessObjectImpl businessObject = this.getByKey(key);
		this.a(businessObject);
		return businessObject;
	}

	/**
	 * TODO: 函数的意思需要去猜想出来
	 * 
	 */
	private void a(BusinessObjectImpl businessObject) {
		if (businessObject == null) {
			return;
		}
		for (BusinessTableRelation rel : businessObject.getRelation().list()) {
			BusinessTableRelationImpl busTableRel = (BusinessTableRelationImpl) rel;
			busTableRel.setTable(this.businessTableManager.getFilledByKey(rel.getTableKey()));
			busTableRel.setBusObj(businessObject);
		}
		this.a(businessObject.getRelation());
	}

	private void a(BusinessTableRelation rel) {
		for (BusinessTableRelation r : rel.getChildren()) {
			BusinessTableRelationImpl busTableRel = (BusinessTableRelationImpl) r;
			busTableRel.setParent(rel);
			this.a(r);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * <p>Title: boTreeData</p>
	 * 
	 * <p>Description: TODO:业务对象的树形展示?</p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see com.dstz.bus.manager.BusinessObjectManager#boTreeData(java.lang.String)
	 */
	@Override
	public List<JSONObject> boTreeData(String key) {
		BusinessObjectImpl businessObject = this.getByKey(key);
		BusinessTableRelationImpl busTableRel = businessObject.getRelation();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		this.a(busTableRel, "0", list);
		if (BeanUtils.isNotEmpty(list)) {
			list.get(0).put("alias", key);
		}
		return list;
	}

	private void a(BusinessTableRelation busTableRel, String parentId, List<JSONObject> list) {

		BusinessTableImpl businessTable = this.businessTableManager.getFilledByKey(busTableRel.getTableKey());
		JSONObject root = new JSONObject();
		root.put("id", businessTable.getId());
		root.put("key", businessTable.getKey());
		root.put("name",
				businessTable.getName() + "(" + BusinessTableRelationType.getByKey(busTableRel.getType()).getDesc() + ")");
		root.put("parentId", parentId);
		root.put("nodeType", "table");
		list.add(root);
		for (BusinessColumnImpl businessColumn : businessTable.getColumns()) {
			JSONObject columnJson = new JSONObject();
			columnJson.put("id", businessColumn.getId());
			columnJson.put("key", businessColumn.getName());
			columnJson.put("name", businessColumn.getComment());
			columnJson.put("tableKey", businessTable.getKey());
			columnJson.put("parentId", businessTable.getId());
			columnJson.put("nodeType", "column");
			list.add(columnJson);
		}
		for (BusinessTableRelation rel : busTableRel.getChildren()) {
			this.a(rel, businessTable.getId(), list);
		}
	}

	public void remove(String entityId) {
		BusinessObjectImpl businessObject = (BusinessObjectImpl) this.get(entityId);
		if (businessObject == null) {
			return;
		}
		List<String> names = this.jdbcTemplate.queryForList(
				" select name_ from form_def where bo_key_ = '" + businessObject.getKey() + "'", String.class);
		if (BeanUtils.isNotEmpty(names)) {
			throw new BusinessException("表单:" + names.toString() + "还在使用业务对象， 删除业务对象失败！");
		}
		super.remove(entityId);
	}
}