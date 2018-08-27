package org.minxc.emp.basis.impl.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Update;
import org.minxc.emp.basis.api.constant.SysStatusCode;
import org.minxc.emp.basis.impl.core.dao.DataDictDao;
import org.minxc.emp.basis.impl.core.manager.DataDictManager;
import org.minxc.emp.basis.impl.core.model.DataDict;
import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 数据字典 Manager处理实现类
 */
@Service("dataDictManager")
public class DataDictManagerImpl extends CommonManager<String, DataDict> implements DataDictManager{
	@Resource
	DataDictDao dataDictDao;
	@Resource
	SysTreeNodeManager sysTreeNodeMananger;
	@Resource
	SysTreeManager sysTreeMananger;
	

	@Override
	public List<DataDict> getDictNodeList(String dictKey,Boolean hasRoot) {
		return dataDictDao.getDictNodeList(dictKey,hasRoot);
	}
	
	
	@Override
	public void create(DataDict dataDict) {
		Integer count = 0 ;
		if(DataDict.TYPE_DICT.equals(dataDict.getDictType())) {
			dataDict.setDictKey(dataDict.getKey());
			count = dataDictDao.isExistDict(dataDict.getKey(),null);
		}else {
			count = dataDictDao.isExistNode(dataDict.getDictKey(),dataDict.getKey() , null);
		}
		
		if(count != 0) {
			throw new BusinessException(dataDict.getKey()+"字典已经存在",SysStatusCode.PARAM_ILLEGAL);
		}
		
		super.create(dataDict);
	}
	
	
	@Override
	public void update(DataDict dataDict) {
		int count = 0 ;
		if(DataDict.TYPE_DICT.equals(dataDict.getDictType())) {
			dataDict.setDictKey(dataDict.getKey());
			count = dataDictDao.isExistDict(dataDict.getKey(),dataDict.getId());
		}else {
			count = dataDictDao.isExistNode(dataDict.getKey(), dataDict.getDictKey(), dataDict.getId());
		}
		
		if(count != 0) {
			throw new BusinessException(dataDict.getKey()+"字典Key已经存在",SysStatusCode.PARAM_ILLEGAL);
		}
		
		super.update(dataDict);
	}


	@Override
	public JSONArray getDictTree() {
		QueryFilter filter = new DefaultQueryFilter();
		filter.addFilter("dict_type_", "dict", QueryOperator.EQUAL);
		
		List<DataDict> dicts = dataDictDao.query(filter);
		
		SysTree sysTree = sysTreeMananger.getByKey("dict");
		List<SysTreeNode> nodeList = sysTreeNodeMananger.getByTreeId(sysTree.getId());
		JSONArray jsonArray = new JSONArray();
		
		for(SysTreeNode sysTreeNode : nodeList) {
			JSONObject object = new JSONObject();
			object.put("id", sysTreeNode.getId());
			object.put("name", sysTreeNode.getName());
			object.put("parentId", sysTreeNode.getParentId());
			object.put("type", "type");
			object.put("noclick",true);
			jsonArray.add(object);
		}
		
		for(DataDict dict : dicts) {
			JSONObject object = new JSONObject();
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
