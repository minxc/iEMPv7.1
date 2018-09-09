package org.minxc.emp.system.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.constant.SysStatusCode;
import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.system.impl.dao.DataDictDao;
import org.minxc.emp.system.impl.manager.DataDictManager;
import org.minxc.emp.system.impl.manager.SysTreeManager;
import org.minxc.emp.system.impl.manager.SysTreeNodeManager;
import org.minxc.emp.system.impl.model.DataDictEntity;
import org.minxc.emp.system.impl.model.TreeEntity;
import org.minxc.emp.system.impl.model.SystemTreeNodeEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.minxc.emp.core.util.JacksonUtil;

/**
 * 数据字典 Manager处理实现类
 */
@Service("dataDictManager")
public class DataDictManagerImpl extends CommonManager<String, DataDictEntity> implements DataDictManager {

	@Resource
	private DataDictDao dataDictDao;
	@Resource
	private SysTreeNodeManager sysTreeNodeMananger;
	@Resource
	private SysTreeManager sysTreeMananger;

	@Override
	public List<DataDictEntity> getDictNodeList(String dictKey, Boolean hasRoot) {
		return dataDictDao.getDictNodeList(dictKey, hasRoot);
	}

	@Override
	public void create(DataDictEntity dataDict) {
		Integer count = 0;
		if (DataDictEntity.TYPE_DICT.equals(dataDict.getDictType())) {
			dataDict.setDictKey(dataDict.getKey());
			count = dataDictDao.isExistDict(dataDict.getKey(), null);
		} else {
			count = dataDictDao.isExistNode(dataDict.getDictKey(), dataDict.getKey(), null);
		}
		if (count != 0) {
			throw new BusinessException(dataDict.getKey() + "字典已经存在", SysStatusCode.PARAM_ILLEGAL);
		}

		super.create(dataDict);
	}

	@Override
	public void update(DataDictEntity dataDict) {
		int count = 0;
		if (DataDictEntity.TYPE_DICT.equals(dataDict.getDictType())) {
			dataDict.setDictKey(dataDict.getKey());
			count = dataDictDao.isExistDict(dataDict.getKey(), dataDict.getId());
		} else {
			count = dataDictDao.isExistNode(dataDict.getKey(), dataDict.getDictKey(), dataDict.getId());
		}

		if (count != 0) {
			throw new BusinessException(dataDict.getKey() + "字典Key已经存在", SysStatusCode.PARAM_ILLEGAL);
		}

		super.update(dataDict);
	}

	@Override
	public ArrayNode getDictTree() {

		QueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("dict_type_", "dict", QueryOperator.EQUAL);

		List<DataDictEntity> dicts = dataDictDao.query(filter);

		TreeEntity sysTree = sysTreeMananger.getByKey("dict");
		List<SystemTreeNodeEntity> nodeList = sysTreeNodeMananger.getByTreeId(sysTree.getId());
		ArrayNode jsonArray = JacksonUtil.jsonArray();

		for (SystemTreeNodeEntity sysTreeNode : nodeList) {
			ObjectNode object = JacksonUtil.jsonObject();
			object.put("id", sysTreeNode.getId());
			object.put("name", sysTreeNode.getName());
			object.put("parentId", sysTreeNode.getParentId());
			object.put("type", "type");
			object.put("noclick", true);
			jsonArray.add(object);
		}

		for (DataDictEntity dict : dicts) {
			ObjectNode object = JacksonUtil.jsonObject();
			object.put("id", dict.getId());
			object.put("name", dict.getName());
			object.put("key", dict.getDictKey());
			object.put("icon", "fa-check-square-o");
			object.put("parentId", dict.getTypeId());
			object.put("type", "dict");
			jsonArray.add(object);
		}

		return jsonArray;
	}

}
